package com.android.expensetracker.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.realm.kotlin.ext.query
import kotlinx.coroutines.launch
import com.android.expensetracker.components.TableRow
import com.android.expensetracker.db
import com.android.expensetracker.ui.theme.DividerColor
import com.android.expensetracker.models.Category
import com.android.expensetracker.models.Expense
import com.android.expensetracker.ui.theme.SystemGray04
import com.android.expensetracker.ui.theme.TextWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navController: NavController) {
  val coroutineScope = rememberCoroutineScope()
  var deleteConfirmationShowing by remember {
    mutableStateOf(false)
  }

  val eraseAllData: () -> Unit = {
    coroutineScope.launch {
      db.write {
        val expenses = this.query<Expense>().find()
        val categories = this.query<Category>().find()

        delete(expenses)
        delete(categories)

        deleteConfirmationShowing = false
      }
    }
  }

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
            text= "Settings",
            color = TextWhite,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
          containerColor = Color(0xFF66023c))
      )
    },
    content = { innerPadding ->
      Column(
        modifier = Modifier
          .padding(innerPadding)
          .background(Color(0xFFe5e4e2))
          .fillMaxSize(),
        ) {
        Column(
          modifier = Modifier
            .padding(top = 4.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(SystemGray04 )
            .fillMaxWidth()
        ) {
          TableRow(
            label = "Categories",
            hasArrow = true,
            modifier = Modifier
              .padding(20.dp)
              .clickable {
              navController.navigate("settings/categories")
            })
          Divider(
            modifier = Modifier
              .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
          )
          TableRow(
            label = "Erase all data",
            isDestructive = true,
            modifier = Modifier
              .padding(20.dp)
              .clickable {
              deleteConfirmationShowing = true
            })

          if (deleteConfirmationShowing) {
            AlertDialog(
              onDismissRequest = { deleteConfirmationShowing = false },
              title = { Text("Are you sure?") },
              text = { Text("This action cannot be undone.") },
              confirmButton = {
                TextButton(onClick = eraseAllData) {
                  Text("Delete everything")
                }
              },
              dismissButton = {
                TextButton(onClick = { deleteConfirmationShowing = false }) {
                  Text("Cancel")
                }
              }
            )
          }
        }
      }
    }
  )
}