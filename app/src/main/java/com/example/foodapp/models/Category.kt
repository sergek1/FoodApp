package com.example.foodapp.models

import java.io.Serializable

data class Category(
    val id: Int,
    val image_url: String,
    val name: String
) : Serializable