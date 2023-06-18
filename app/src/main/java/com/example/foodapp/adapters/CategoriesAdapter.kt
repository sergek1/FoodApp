package com.example.foodapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.models.Category
import kotlinx.android.synthetic.main.category_item.view.*

class CategoriesAdapter() : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {
    private var categoriesList = ArrayList<Category>()
    var onItemClick: ((Category) -> Unit)? = null

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun setCategoryList(categoryList: List<Category>) {
        this.categoriesList = categoryList as ArrayList<Category>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.category_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoriesList[position]
        holder.itemView.apply {
            Glide.with(this).load(category.image_url).into(iv_category)
            tv_category_name.text = category.name

            setOnClickListener {
                onItemClick?.let { it(category) }
            }
        }
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    fun onItemClickListener(listener: (Category) -> Unit) {
        onItemClick = listener
    }
}