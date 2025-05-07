package com.android.expensetracker

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import com.android.expensetracker.models.Category
import com.android.expensetracker.models.Expense

val config = RealmConfiguration.create(schema = setOf(Expense::class, Category::class))
val db: Realm = Realm.open(config)