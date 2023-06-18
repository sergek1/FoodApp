package com.example.foodapp.util

import android.content.Context
import android.location.Location
import com.google.android.gms.tasks.Task

interface Location {
    fun getCurrentLocation(context: Context)
}