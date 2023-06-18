package com.example.foodapp.db

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class DishTypeConverter {
    @androidx.room.TypeConverter
    fun fromAnyToString(attribute: Any?): String {
        if (attribute == null)
            return ""
        return attribute.toString()
    }

    @androidx.room.TypeConverter
    fun fromStringToAny(attribute: String?): Any {
        if (attribute == null)
            return ""
        return attribute
    }

    @androidx.room.TypeConverter
    fun fromListToString(list: List<String>): String {
        return Gson().toJson(list)
    }

    @androidx.room.TypeConverter
    fun fromStringToList(json: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, type)
    }
}