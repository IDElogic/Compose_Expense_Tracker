package com.android.expensetracker.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.expensetracker.components.expensesList.ExpensesList
import com.android.expensetracker.models.Recurrence
import com.android.expensetracker.utils.formatDayForRange
import com.android.expensetracker.viewmodels.ReportPageViewModel
import com.android.expensetracker.viewmodels.viewModelFactory
import com.android.expensetracker.ui.theme.LabelSecondary
import java.text.DecimalFormat

@Composable
fun ReportPage(
  innerPadding: PaddingValues,
  page: Int,
  recurrence: Recurrence,
  vm: ReportPageViewModel = viewModel(
    key = "$page-${recurrence.name}",
    factory = viewModelFactory {
      ReportPageViewModel(page, recurrence)
    })
) {
  val uiState = vm.uiState.collectAsState().value

  Column(
    modifier = Modifier
      .padding(innerPadding)
      .padding(horizontal = 16.dp)
      .padding(top = 16.dp)
      .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row(
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier.fillMaxWidth()
    ) {
      Column {
        Text(
          "${
            uiState.dateStart.formatDayForRange()
          } - ${uiState.dateEnd.formatDayForRange()}",

        )
        Row(modifier = Modifier.padding(top = 4.dp)) {
          Text(
            "USD",

            color = LabelSecondary,
            modifier = Modifier.padding(end = 4.dp)
          )
          Text(DecimalFormat("0.#").format(uiState.totalInRange))
        }
      }
      Column(horizontalAlignment = Alignment.End) {
        Text("Avg/day")
        Row(modifier = Modifier
          .padding(top = 4.dp)) {
          Text(
            "USD",
            color = LabelSecondary,
            modifier = Modifier.padding(end = 4.dp)
          )
          Text(DecimalFormat("0.#").format(uiState.avgPerDay))
        }
      }
    }

    ExpensesList(
      expenses = uiState.expenses, modifier = Modifier
        .weight(1f)
        .verticalScroll(
          rememberScrollState()
        )
    )
  }
}