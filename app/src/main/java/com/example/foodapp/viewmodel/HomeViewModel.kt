package com.example.foodapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.api.RetrofitInstance
import com.example.foodapp.db.DishDatabase
import com.example.foodapp.models.Category
import com.example.foodapp.models.CategoryList
import com.example.foodapp.models.Dish
import com.example.foodapp.models.DishesList
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(
    private val dishDatabase: DishDatabase
) : ViewModel() {
    private var favoritesLiveData = dishDatabase.dishDao().getAllDishes()
    private var categoriesLiveData = MutableLiveData<List<Category>>()
    private var dishesLiveData = MutableLiveData<List<Dish>>()

    fun insertDish(dish: Dish) {
        viewModelScope.launch {
            dishDatabase.dishDao().upsert(dish)
        }
    }

    fun deleteDish(dish: Dish) {
        viewModelScope.launch {
            dishDatabase.dishDao().delete(dish)
        }
    }

    fun getCategories() {
        RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryList> {
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                response.body()?.let {
                    categoriesLiveData.postValue(it.сategories)
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("HomeViewModel", t.message.toString())
            }
        })
    }

    fun getDishes() {
        RetrofitInstance.api.getDishes().enqueue(object : Callback<DishesList> {
            override fun onResponse(call: Call<DishesList>, response: Response<DishesList>) {
                response.body()?.let {
                    dishesLiveData.postValue(it.dishes)
                }
            }

            override fun onFailure(call: Call<DishesList>, t: Throwable) {
                Log.d("HomeViewModel", t.message.toString())
            }
        })
    }

    fun observeCategoriesLiveData(): LiveData<List<Category>> {
        return categoriesLiveData
    }

    fun observeDishesLiveData(): LiveData<List<Dish>> {
        return dishesLiveData
    }

    fun observeFavoritesLiveData(): LiveData<List<Dish>> {
        return favoritesLiveData
    }
}