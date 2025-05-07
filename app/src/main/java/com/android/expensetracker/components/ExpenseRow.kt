package com.android.expensetracker.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.android.expensetracker.models.Expense
import com.android.expensetracker.ui.theme.DividerColor
import com.android.expensetracker.ui.theme.LabelSecondary
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

@Composable
fun ExpenseRow(expense: Expense, modifier: Modifier = Modifier) {
  ElevatedCard(
    colors = CardDefaults.cardColors(
      Color.White
    ),
    shape = RoundedCornerShape(5.dp),
    elevation = CardDefaults.cardElevation(
      defaultElevation = 20.dp
    ),
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 5.dp)

  ) {
  Column(modifier = modifier) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Text(
        expense.note ?: expense.category!!.name,
      )
      Text(
        "USD ${DecimalFormat("0.#").format(expense.amount)}",
      )
    }
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      CategoryBadge(category = expense.category!!)
      Text(
        expense.date.format(DateTimeFormatter.ofPattern("HH:mm")),
        color = LabelSecondary
      )
    }

  }}
}