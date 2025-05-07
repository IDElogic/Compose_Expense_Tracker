package com.android.expensetracker.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.android.expensetracker.R
import com.android.expensetracker.components.TableRow
import com.android.expensetracker.components.UnstyledTextField
import com.android.expensetracker.ui.theme.DividerColor
import com.android.expensetracker.ui.theme.LabelSecondary
import com.android.expensetracker.ui.theme.SystemGray04
import com.android.expensetracker.ui.theme.TextWhite
import com.android.expensetracker.viewmodels.CategoriesViewModel
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(
  ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
)
@Composable
fun Categories(
  navController: NavController, vm: CategoriesViewModel = viewModel()
) {
  val uiState by vm.uiState.collectAsState()

  val colorPickerController = rememberColorPickerController()

  Scaffold(modifier = Modifier
    .background(SystemGray04)
    .padding(horizontal = 10.dp)
    .fillMaxSize(),
    topBar = {
      TopAppBar(
        modifier = Modifier
          .clip(RoundedCornerShape(0.dp)),
        title = {
        Text("Categories",
          color = TextWhite,
          textAlign = TextAlign.End) },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor =  Color(0xFF66023c)
          ),
        navigationIcon = {
          Surface(
            onClick = navController::popBackStack,
            color = Color(0xFF66023c),
          ) {
            Row(modifier = Modifier
              .padding(vertical = 20.dp)) {
              Image(
                painterResource(id = R.drawable.settings_outlined),
                contentDescription = "back"
              )
            }
          }
        }
      )
    },
    content = { innerPadding ->
      Column(
        modifier = Modifier
          .padding(innerPadding)
          .background(SystemGray04)
          .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
      ) {
        Column(
          modifier = Modifier
            .weight(1f)) {
          AnimatedVisibility(visible = true) {
            LazyColumn(
              modifier = Modifier
                .padding(top = 4.dp)
                .clip(RoundedCornerShape(5.dp))
                .fillMaxWidth()
            ) {
              itemsIndexed(
                uiState.categories,
                key = { _, category -> category.name }) { index, category ->
                SwipeableActionsBox(
                  endActions = listOf(
                    SwipeAction(
                      icon = painterResource(R.drawable.delete),
                      background = LabelSecondary,
                      onSwipe = { vm.deleteCategory(category) }
                    ),
                  ),
                  modifier = Modifier.animateItem()
                ) {
                  TableRow(
                    modifier = Modifier
                      .background(Color.White)
                      .padding(20.dp)) {
                    Row(
                      verticalAlignment = Alignment.CenterVertically,
                      modifier = Modifier
                        .padding(horizontal = 10.dp)
                    ) {
                      Surface(
                        color = category.color,
                        shape = RoundedCornerShape(5.dp),
                        border = BorderStroke(
                          width = 4.dp,
                          color = DividerColor
                        ),
                        modifier = Modifier
                          .size(20.dp)
                      ) {}
                      Text(
                        text = category.name,
                        color = Color.White,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(
                          horizontal = 16.dp,
                          vertical = 10.dp
                        ),
                      )
                    }
                  }
                }
                if (index < uiState.categories.size - 1) {
                  Row(modifier = Modifier
                    .background(SystemGray04)
                    .height(1.dp)) {
                    Divider(
                      modifier = Modifier
                        .padding(start = 16.dp),
                      thickness = 1.dp,
                      color = DividerColor
                    )
                  }
                }
              }
            }
          }
        }
        Row(
          modifier = Modifier
            .padding(20.dp)
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween,
        ) {
          if (uiState.colorPickerShowing) {
            Dialog(onDismissRequest = vm::hideColorPicker) {
              Surface(
                color = DividerColor,
                shape = RoundedCornerShape(5.dp),
              ) {
                Column(
                  modifier = Modifier
                    .padding(all = 30.dp)
                ) {
                  Text("Select a color",
                    color = TextWhite
                  )
                  Row(
                    modifier = Modifier
                      .fillMaxWidth()
                      .padding(top = 24.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                  ) {
                    AlphaTile(
                      modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(RoundedCornerShape(6.dp)),
                      controller = colorPickerController
                    )
                  }
                  HsvColorPicker(
                    modifier = Modifier
                      .fillMaxWidth()
                      .height(240.dp)
                      .padding(10.dp),
                    controller = colorPickerController,
                    onColorChanged = { envelope ->
                      vm.setNewCategoryColor(envelope.color)
                    },
                  )
                  TextButton(
                    onClick = vm::hideColorPicker,
                    modifier = Modifier
                      .fillMaxWidth()
                      .padding(top = 24.dp),
                  ) {
                    Text("Done",
                      color = TextWhite)
                  }
                }
              }
            }
          }
          Surface(
            onClick = vm::showColorPicker,
            shape = RoundedCornerShape(5.dp),
            color = uiState.newCategoryColor,
            border = BorderStroke(
              width = 2.dp,
              color = DividerColor
            ),
            modifier = Modifier
              .size(width = 20.dp, height = 20.dp)
          ) {}
          Surface(
            color = SystemGray04,
            modifier = Modifier
              .height(80.dp)
              .weight(1f)
              .padding(start = 16.dp),
            //shape = Shapes.large,
          ) {
            Column(
              verticalArrangement = Arrangement.Center,
              modifier = Modifier.fillMaxHeight()
            ) {
              UnstyledTextField(
                value = uiState.newCategoryName,
                onValueChange = vm::setNewCategoryName,
                placeholder = {
                  Text(
                    modifier = Modifier
                      .padding(20.dp),
                    color = SystemGray04,
                    text = "Choose Category") },
                modifier = Modifier
                  .padding(horizontal = 10.dp)
                  .fillMaxWidth()
                  .height(80.dp),
                maxLines = 1,
                textStyle = LocalTextStyle.current
              )
            }
          }
          IconButton(

            onClick = vm::createNewCategory,
            modifier = Modifier
              .padding(start = 16.dp)
          ) {
            Image(
              painterResource(id = R.drawable.add),
              contentDescription = "Add category"
            )
          }
        }
      }
    })
}
