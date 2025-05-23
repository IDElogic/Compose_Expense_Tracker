package com.android.expensetracker.models

import androidx.compose.ui.graphics.Color
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Category() : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()

    private var _colorValue: String = "0,0,0"
    var name: String = ""
    val color: Color
        get() {
            val colorComponents = _colorValue.split(",")
            val (red, green, blue) = colorComponents
            return Color(red.toFloat(), green.toFloat(), blue.toFloat())
        }
    constructor(
        name: String,
        color: Color
    ) : this() {
        this.name = name
        this._colorValue = "${color.red},${color.green},${color.blue}"
    }
}


