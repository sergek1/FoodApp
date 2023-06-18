package com.example.foodapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishes")
data class Dish(
    val description: String?,
    @PrimaryKey
    val id: Int,
    val image_url: String?,
    val name: String?,
    val price: Int = 0,
    val tegs: List<String?>,
    val weight: Int?,
    var amount: Int = 1
) : java.io.Serializable