package com.example.foodapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.viewmodel.HomeViewModel
import com.example.foodapp.R
import com.example.foodapp.models.Dish
import kotlinx.android.synthetic.main.cart_item.view.*

class CartAdapter(
    private val homeViewModel: HomeViewModel
) : RecyclerView.Adapter<CartAdapter.CartItemViewHolder>() {
    private var cartItemsList = ArrayList<Dish>()

    inner class CartItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        return CartItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cart_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return cartItemsList.size
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val dish = cartItemsList[position]
        holder.itemView.apply {
            Glide.with(this).load(dish.image_url).into(iv_dish_cart)
            tv_dish_name_cart.text = dish.name
            val weightString = "· ${dish.weight}г"
            tv_weight_cart.text = weightString
            val dishPrice = "${dish.price} ₽ "
            tv_dish_price_cart.text = dishPrice
            tv_dish_amount.text = dish.amount.toString()
            iv_minus.setOnClickListener {
                if (dish.amount > 1) {
                    dish.amount--
                    homeViewModel.insertDish(dish)
                } else {
                    homeViewModel.deleteDish(dish)
                }
            }
            iv_plus.setOnClickListener {
                dish.amount++
                homeViewModel.insertDish(dish)
            }
        }
    }

    fun setFavorites(dishes: List<Dish>) {
        cartItemsList = dishes as ArrayList<Dish>
        notifyDataSetChanged()
    }
}