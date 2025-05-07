package com.android.expensetracker.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.expensetracker.components.PickerTrigger
import com.android.expensetracker.components.expensesList.ExpensesList
import com.android.expensetracker.models.Recurrence
import com.android.expensetracker.ui.theme.LabelSecondary
import com.android.expensetracker.viewmodels.ExpensesViewModel
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Expenses(
  vm: ExpensesViewModel = viewModel()
) {
  val recurrences = listOf(
    Recurrence.Daily,
    Recurrence.Weekly,
    Recurrence.Monthly,
    Recurrence.Yearly
  )

  val state by vm.uiState.collectAsState()
  var recurrenceMenuOpened by remember {
    mutableStateOf(false)
  }

  Scaffold(
    modifier = Modifier
      .background(Color(0xFFe5e4e2))
      .padding(horizontal = 10.dp)
      .fillMaxSize(),
    topBar = {
  TopAppBar(
        modifier = Modifier
          .clip(RoundedCornerShape(5.dp)),
        title = {
          Text("My Expenses Tracker",
            color = Color.White,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center)},
            colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF66023c)
              ),
        navigationIcon = {}
      )
    },
    content = { innerPadding ->
      Column(
        modifier = Modifier
          .padding(top = 2.dp)
          .padding(innerPadding)
          .background(Color(0xFFe5e4e2))
          .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Column(modifier = Modifier
          .fillMaxWidth()
          .background(Color.White))
        {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
          verticalAlignment = Alignment.CenterVertically
        )
        {
          Text(
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            text = "Total for:",
          )
          Spacer(modifier = Modifier.weight(1f))
          PickerTrigger(
            state.recurrence.target,
            onClick = { recurrenceMenuOpened = !recurrenceMenuOpened },
            modifier = Modifier.padding(start = 16.dp)
          )
          DropdownMenu(
            expanded = recurrenceMenuOpened,
            onDismissRequest = { recurrenceMenuOpened = false }) {
            recurrences.forEach { recurrence ->
              DropdownMenuItem(text = { Text(recurrence.target) }, onClick = {
                vm.setRecurrence(recurrence)
                recurrenceMenuOpened = false
              })
            }
          }
        }
        Row(
          modifier = Modifier
            .padding(bottom = 10.dp)
            .align( Alignment.CenterHorizontally)
        ) {
          Text(
            text = "$",
            color = LabelSecondary,
            modifier = Modifier
              .padding(end = 4.dp)
              .size(24.dp)
          )
          Text(
            color = LabelSecondary,
            text = DecimalFormat("0.#").format(state.sumTotal),
          )
        }
      }
        ExpensesList(
          expenses = state.expenses,
          modifier = Modifier
            .weight(1f)
            .padding( horizontal = 20.dp)
            .verticalScroll(
              rememberScrollState()
            )
        )
      }
    }
  )
}
