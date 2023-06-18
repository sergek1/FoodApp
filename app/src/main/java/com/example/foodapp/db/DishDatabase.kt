package com.example.foodapp.db

import android.content.Context
import androidx.room.*
import com.example.foodapp.models.Dish

@Database(entities = [Dish::class], version = 1)
@TypeConverters(DishTypeConverter::class)
abstract class DishDatabase : RoomDatabase() {
    abstract fun dishDao(): DishDao

    companion object {
        @Volatile
        var INSTANCE: DishDatabase? = null

        @Synchronized
        fun getInstance(context: Context): DishDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    DishDatabase::class.java,
                    "dishes.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as DishDatabase
        }
    }
}