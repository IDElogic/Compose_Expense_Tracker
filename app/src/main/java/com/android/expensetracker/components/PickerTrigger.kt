package com.android.expensetracker.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.expensetracker.R
import com.android.expensetracker.ui.theme.FillTertiary
import com.android.expensetracker.ui.theme.Shapes

@Composable
fun PickerTrigger(
  label: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Surface(
    shape = Shapes.medium,
    color = FillTertiary,
    modifier = modifier,
    onClick = onClick,
  ) {
    Row(
      modifier = Modifier.padding(horizontal = 20.dp, vertical = 3.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Text(label)
      Icon(
        painterResource(R.drawable.ic_unfold_more),
        contentDescription = "Open picker",
        modifier = Modifier.padding(start = 10.dp)
      )
    }
  }
}