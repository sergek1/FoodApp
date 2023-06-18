package com.example.foodapp

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.foodapp.models.Dish
import com.example.foodapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.dialog_item_details.*

class DishDetailsDialog(private val dish: Dish) : DialogFragment() {

    private lateinit var viewModel: HomeViewModel
    override fun getTheme() = R.style.RoundedCornersDialog

    companion object {
        const val TAG = "DishDetailsDialog"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewModel = (requireActivity() as MainActivity).viewModel
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_item_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDishDetails()
        ic_cancel.setOnClickListener {
            dismiss()
        }
        btn_add_to_favorites.setOnClickListener {
            dish.amount = 1
            viewModel.insertDish(dish)
            Toast.makeText(context, "Добавлено в корзину", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setDishDetails() {
        dish.apply {
            context?.let { Glide.with(it).load(image_url).into(iv_dish_dialog) }
            tv_dish_name_dialog.text = name
            val price = "$price ₽ "
            tv_price_dialog.text = price
            val weight = "· ${weight}г"
            tv_weight_dialog.text = weight
            tv_description_dialog.text = description
        }
    }
}
