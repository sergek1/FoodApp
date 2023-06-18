package com.example.foodapp.util

import android.content.Context
import kotlinx.android.synthetic.main.view_user_geo_date.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateImplementation : Date {

    override fun getDate() : String {
        val formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy")
        return LocalDate.now().format(formatter)
    }

}