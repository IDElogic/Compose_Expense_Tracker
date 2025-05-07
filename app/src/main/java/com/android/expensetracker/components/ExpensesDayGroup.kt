package com.android.expensetracker.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.android.expensetracker.models.DayExpenses
import com.android.expensetracker.utils.formatDay
import com.android.expensetracker.ui.theme.LabelSecondary
import java.text.DecimalFormat
import java.time.LocalDate

@Composable
fun ExpensesDayGroup(
  date: LocalDate,
  dayExpenses: DayExpenses,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier) {
    Text(
      date.formatDay(),
      color = LabelSecondary,
      fontWeight = FontWeight.Medium
    )
    Divider(modifier = Modifier
      .padding(top = 10.dp, bottom = 4.dp))
    dayExpenses.expenses.forEach { expense ->
      ExpenseRow(
        expense = expense,
        modifier = Modifier.padding(top = 12.dp)
      )
    }
    Divider(modifier = Modifier.padding(top = 16.dp, bottom = 4.dp))
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Text("Total:",
        color = LabelSecondary)
      Text(
        DecimalFormat("USD 0.#").format(dayExpenses.total),
        color = LabelSecondary,
        fontWeight = FontWeight.Medium

      )
    }
  }
}