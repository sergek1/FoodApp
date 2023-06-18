package com.example.foodapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.foodapp.models.Dish

@Dao
interface DishDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(dish: Dish)

    @Delete
    suspend fun delete(dish: Dish)

    @Query("SELECT * FROM dishes")
    fun getAllDishes(): LiveData<List<Dish>>
}