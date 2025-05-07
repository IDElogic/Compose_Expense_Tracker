package com.android.expensetracker.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.expensetracker.models.Category
import com.android.expensetracker.ui.theme.SystemGray04

@Composable
fun CategoryBadge(category: Category, modifier: Modifier = Modifier) {
  Surface(
    modifier = Modifier.padding(vertical = 12.dp),
    shape = RoundedCornerShape(4.dp),
    color = SystemGray04,
  ) {
    Text(
      category.name,
      color = category.color,
      fontSize = 12.sp,
      modifier = Modifier
        .padding( 8.dp)
    )
  }
}