package com.android.expensetracker.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.expensetracker.R
import com.android.expensetracker.components.ReportPage
import com.android.expensetracker.models.Recurrence
import com.android.expensetracker.ui.theme.TextWhite
import com.android.expensetracker.viewmodels.ReportsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun Reports(vm: ReportsViewModel = viewModel()) {
  val uiState = vm.uiState.collectAsState().value

  val recurrences = listOf(
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
        title = {
          Text(
            text = "Reports",
          color = TextWhite,
          fontWeight = FontWeight.Medium,
          textAlign = TextAlign.Center) },
          colors = TopAppBarDefaults.mediumTopAppBarColors(
          containerColor = Color(0xFF66023c)),
        actions = {
          IconButton(onClick = vm::openRecurrenceMenu) {
            Image(
              painterResource(id = R.drawable.ic_today),
              contentDescription = "Change recurrence"
            )
          }
          DropdownMenu(
            expanded = uiState.recurrenceMenuOpened,
            onDismissRequest = vm::closeRecurrenceMenu
          ) {
            recurrences.forEach { recurrence ->
              DropdownMenuItem(text = { Text(recurrence.name) }, onClick = {
                vm.setRecurrence(recurrence)
                vm.closeRecurrenceMenu()
              })
            }
          }
        }
      )
    },
    content = { innerPadding ->
      val numOfPages = when (uiState.recurrence) {
        Recurrence.Weekly -> 53
        Recurrence.Monthly -> 12
        Recurrence.Yearly -> 1
        else -> 53
      }
      HorizontalPager(
        modifier = Modifier
          .background(Color(0xFFe5e4e2)),
        count = numOfPages, reverseLayout = true) { page ->
        ReportPage(
          innerPadding, page, uiState.recurrence)
      }
    }
  )
}