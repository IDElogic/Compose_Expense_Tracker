package com.android.expensetracker

import io.sentry.compose.withSentryObservableEffect
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.expensetracker.pages.Add
import com.android.expensetracker.pages.Categories
import com.android.expensetracker.pages.Expenses
import com.android.expensetracker.pages.Reports
import com.android.expensetracker.pages.Settings
import com.android.expensetracker.ui.theme.SystemGray04
import com.android.expensetracker.ui.theme.TextWhite

@Composable
fun BudgetMainScreen() {

    var showBottomBar by rememberSaveable { mutableStateOf(true) }
    val navController = rememberNavController().withSentryObservableEffect()
    val backStackEntry by navController.currentBackStackEntryAsState()

    showBottomBar = when (backStackEntry?.destination?.route) {
        "settings/categories" -> false
        else -> true
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(modifier = Modifier
                    .fillMaxWidth(),
                    containerColor = SystemGray04 ) {
                    NavigationBarItem(
                        selected = backStackEntry?.destination?.route == "expenses",
                        onClick = {
                            navController.navigate("expenses")
                                  },
                        label = {
                            Text("Expenses",
                                color = TextWhite)
                        },
                        icon = {
                            Icon(
                                painterResource(id = R.drawable.upload),
                                contentDescription = "Upload"
                            )
                        }
                    )
                    NavigationBarItem(
                        selected = backStackEntry?.destination?.route == "reports",
                        onClick = {
                            navController.navigate("reports")
                                  },
                        label = {
                            Text("Reports",
                                color = TextWhite)
                        },
                        icon = {
                            Icon(
                                painterResource(id = R.drawable.bar_chart),
                                contentDescription = "Reports"
                            )
                        }
                    )
                    NavigationBarItem(
                        selected = backStackEntry?.destination?.route == "add",
                        onClick = { navController.navigate("add") },
                        label = {
                            Text("Add",
                                color = TextWhite)
                        },
                        icon = {
                            Icon(
                                painterResource(id = R.drawable.add),
                                contentDescription = "Add"
                            )
                        }
                    )
                    NavigationBarItem(
                        selected = backStackEntry?.destination?.route?.startsWith("settings")
                            ?: false,
                        onClick = { navController.navigate("settings") },
                        label = {
                            Text("Settings",
                                color = TextWhite)
                        },
                        icon = {
                            Icon(
                                painterResource(id = R.drawable.settings_outlined),
                                contentDescription = "Settings"
                            )
                        }
                    )
                }
            }
        },
        content = { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "expenses"
            ) {
                composable("expenses") {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    ) {
                        Expenses()
                    }
                }
                composable("reports") {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    ) {
                        Reports()
                    }
                }
                composable("add") {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    ) {
                        Add()
                    }
                }
                composable("settings") {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    ) {
                        Settings(navController)
                    }
                }
                composable("settings/categories") {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    ) {
                        Categories(navController)
                    }
                }
            }
        }
    )
}
