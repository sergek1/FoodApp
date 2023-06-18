package com.example.foodapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.models.Dish
import com.example.foodapp.models.DishesList
import kotlinx.android.synthetic.main.dish_item.view.*

class DishesAdapter() : RecyclerView.Adapter<DishesAdapter.DishViewHolder>() {
    private var dishesList = ArrayList<Dish>()
    var onItemClick: ((Dish) -> Unit)? = null

    inner class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun setDishesList(dishesList: List<Dish>) {
        this.dishesList = dishesList as ArrayList<Dish>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        return DishViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.dish_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dishesList.size
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = dishesList[position]
        holder.itemView.apply {
            Glide.with(this).load(dish.image_url).into(iv_dish)
            tv_dish_name.text = dish.name
            setOnClickListener {
                onItemClick?.let { it(dish) }
            }
        }
    }

    fun onItemClickListener(listener: (Dish) -> Unit) {
        onItemClick = listener
    }
}