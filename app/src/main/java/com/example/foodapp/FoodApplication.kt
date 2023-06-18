package com.example.foodapp

import android.app.Application
import com.example.foodapp.db.DishDatabase
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class FoodApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@FoodApplication))

//        bind() from singleton { DishDatabase(instance()) }
    }
}