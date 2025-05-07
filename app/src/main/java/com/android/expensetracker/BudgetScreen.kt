package com.android.expensetracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun BudgetScreen(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .padding(top = 100.dp)
                .padding(20.dp)
                .align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 20.dp, vertical = 40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_add_task_24),
                    contentDescription = "Budget",
                    modifier = Modifier
                        .padding(bottom = 40.dp)
                        .size(120.dp)
                )
                Text(
                    text = stringResource(R.string.budget_quote1),
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color(0xFFe5e4e2),
                    )
                )

            }
        }

        Button(
            onClick = {
                navController.navigate("login")
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(60.dp)
                .align(Alignment.BottomCenter)
                .height(60.dp),
            shape = RoundedCornerShape(100.dp),
        ) {
            Text(
                text = stringResource(R.string.get_started),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                )
            )
        }
    }
}

