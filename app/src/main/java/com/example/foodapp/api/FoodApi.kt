package com.example.foodapp.api

import com.example.foodapp.models.CategoryList
import com.example.foodapp.models.DishesList
import retrofit2.Call
import retrofit2.http.GET

interface FoodApi {
    @GET("058729bd-1402-4578-88de-265481fd7d54")
    fun getCategories(): Call<CategoryList>

    @GET("aba7ecaa-0a70-453b-b62d-0e326c859b3b")
    fun getDishes(): Call<DishesList>
}