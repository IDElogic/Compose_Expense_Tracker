package com.android.expensetracker.mock

import androidx.compose.ui.graphics.Color
import io.github.serpro69.kfaker.Faker
import com.android.expensetracker.models.Category
import com.android.expensetracker.models.Expense
import com.android.expensetracker.models.Recurrence

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

val faker = Faker()

val mockCategories = listOf(
  Category(
    "Bills",
    Color(
      faker.random.nextInt(0, 255),
      faker.random.nextInt(0, 255),
      faker.random.nextInt(0, 255)
    )
  ),
  Category(
    "Car", Color(
      faker.random.nextInt(0, 255),
      faker.random.nextInt(0, 255),
      faker.random.nextInt(0, 255)
    )
  ),
  Category(
    "Movie", Color(
      faker.random.nextInt(0, 255),
      faker.random.nextInt(0, 255),
      faker.random.nextInt(0, 255)
    )
  ),
  Category(
    "Other", Color(
      faker.random.nextInt(0, 255),
      faker.random.nextInt(0, 255),
      faker.random.nextInt(0, 255)
    )
  ),
)

val mockExpenses: List<Expense> = List(30) {
  Expense(
    amount = faker.random.nextInt(min = 1, max = 999)
      .toDouble() + faker.random.nextDouble(),
    date = LocalDateTime.now().minus(
      faker.random.nextInt(min = 300, max = 345600).toLong(),
      ChronoUnit.SECONDS
    ),
    recurrence = faker.random.randomValue(
      listOf(
        Recurrence.None,
        Recurrence.Daily,
        Recurrence.Monthly,
        Recurrence.Weekly,
        Recurrence.Yearly
      )
    ),
    note = faker.australia.animals(),
    category = faker.random.randomValue(mockCategories)
  )
}