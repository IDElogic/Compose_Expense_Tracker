package com.android.expensetracker.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.expensetracker.components.TableRow
import com.android.expensetracker.components.UnstyledTextField
import com.android.expensetracker.models.Recurrence
import com.android.expensetracker.ui.theme.DividerColor
import com.android.expensetracker.ui.theme.SystemGray04
import com.android.expensetracker.viewmodels.AddViewModel
import com.marosseleng.compose.material3.datetimepickers.date.ui.dialog.DatePickerDialog

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Add(
  vm: AddViewModel = viewModel()) {

  val state by vm.uiState.collectAsState()

  val recurrences = listOf(
    Recurrence.None,
    Recurrence.Daily,
    Recurrence.Weekly,
    Recurrence.Monthly,
    Recurrence.Yearly
  )

  Scaffold(modifier = Modifier
    .background(Color(0xFFe5e4e2))
    .padding(horizontal = 10.dp)
    .fillMaxSize(),
    topBar = {
    TopAppBar(
      modifier = Modifier
        .clip(RoundedCornerShape(5.dp)),
      title = { Text("Add",
        color = Color.White,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center)},
        colors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color(0xFF66023c)
      )
    )
  }, content = { innerPadding ->
    Column(
      modifier = Modifier
        .padding(innerPadding)
        .background(Color(0xFFe5e4e2))
        .fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Column(
        modifier = Modifier
          .padding(top = 4.dp)
          .clip(RoundedCornerShape(5.dp))
          .background(SystemGray04 )
          .fillMaxWidth()
      ) {
        TableRow(
          modifier = Modifier
            .height(80.dp)
            .padding(end = 8.dp),
          label = "Amount",
          detailContent =
            {
          UnstyledTextField(
            value = state.amount,
            onValueChange = vm::setAmount,
            modifier = Modifier
              .fillMaxWidth(),
            placeholder = {
              Text(
                modifier = Modifier
                  .padding(vertical = 14.dp),
                text = "0") },
            arrangement = Arrangement.End,
            maxLines = 2,
            textStyle = TextStyle(
              textAlign = TextAlign.Right,
            ),
            keyboardOptions = KeyboardOptions(
              keyboardType = KeyboardType.Number,
            )
          )
        })
        Divider(
          modifier = Modifier.padding(start = 16.dp),
          thickness = 2.dp,
          color = DividerColor
        )
        TableRow(
          modifier = Modifier
            .height(80.dp),
          label = "Recurrence",
          detailContent = {
          var recurrenceMenuOpened by remember {
            mutableStateOf(false)
          }
          TextButton(
            onClick = { recurrenceMenuOpened = true },

          ) {
            Text(
              color = Color(0xFFe5e4e2),
              text = state.recurrence.name
            )
            DropdownMenu(expanded = recurrenceMenuOpened,
              onDismissRequest = { recurrenceMenuOpened = false }) {
              recurrences.forEach { recurrence ->
                DropdownMenuItem(text = { Text(recurrence.name) }, onClick = {
                  vm.setRecurrence(recurrence)
                  recurrenceMenuOpened = false
                })
              }
            }
          }
        })
        Divider(
          modifier = Modifier.padding(start = 16.dp),
          thickness = 1.dp,
          color = DividerColor
        )
        var datePickerShowing by remember {
          mutableStateOf(false)
        }
        TableRow(
          modifier = Modifier
            .height(80.dp),
          label = "Date", detailContent = {
          TextButton(onClick = { datePickerShowing = true }) {
            Text(
              color = Color(0xFFe5e4e2),
              text = state.date.toString())
          }
          if (datePickerShowing) {
            DatePickerDialog(onDismissRequest = { datePickerShowing = false },
              onDateChange = {
                vm.setDate(it)
                datePickerShowing = false
              },
              initialDate = state.date,
              title = {
                Text(
                  color = Color(0xFFe5e4e2),
                  text = "Select date",
              ) })
          }
        })
        Divider(
          modifier = Modifier.padding(start = 16.dp),
          thickness = 1.dp,
          color = DividerColor
        )
        TableRow(
          modifier = Modifier
            .height(80.dp)
            .padding(end = 8.dp),
          label = "Note",
          detailContent = {
          UnstyledTextField(
            modifier = Modifier
              .fillMaxWidth(),
            value = state.note,
            placeholder = {
              Text(
                modifier = Modifier
                  .padding(vertical = 14.dp),
                text = "Leave some notes")},
            arrangement = Arrangement.End,
            onValueChange = vm::setNote,
            textStyle = TextStyle(
              textAlign = TextAlign.Right
            ),
          )
        })
        Divider(
          modifier = Modifier.padding(start = 16.dp),
          thickness = 1.dp,
          color = DividerColor
        )
        TableRow(
          modifier = Modifier
            .height(80.dp),
          label = "Category", detailContent = {
          var categoriesMenuOpened by remember {
            mutableStateOf(false)
          }
          TextButton(
            onClick = { categoriesMenuOpened = true },
          //  shape = Shapes.large
          ) {
            Text(
              state.category?.name ?: "Select a category first",
              color = state.category?.color ?: Color.White
            )
            DropdownMenu(expanded = categoriesMenuOpened,
              onDismissRequest = { categoriesMenuOpened = false }) {
              state.categories?.forEach { category ->
                DropdownMenuItem(text = {
                  Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                      modifier = Modifier.size(10.dp),
                      shape = CircleShape,
                      color = category.color
                    ) {}
                    Text(
                      category.name, modifier = Modifier.padding(start = 8.dp)
                    )
                  }
                }, onClick = {
                  vm.setCategory(category)
                  categoriesMenuOpened = false
                })
              }
            }
          }
        })
      }
      Button(

        onClick = vm::submitExpense,
        modifier = Modifier
          .padding(20.dp),
        border = BorderStroke(
          width = 1.dp,
          color = Color.White
        ),
        enabled = state.category != null
      ) {
        Text("Submit expense",
          color = Color.White)
      }
    }
  })
}






