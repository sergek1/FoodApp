package com.example.foodapp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import kotlinx.android.synthetic.main.teg_item.view.*

class TegsAdapter : RecyclerView.Adapter<TegsAdapter.TegViewHolder>() {
    private var selectedItemPosition: Int = 0
    private var onItemClick: ((String) -> Unit)? = null

    private var tegsList = ArrayList<String>()

    fun setTegList(tegsList: List<String>) {
        this.tegsList = tegsList as ArrayList<String>
        notifyDataSetChanged()
    }

    inner class TegViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TegViewHolder {
        return TegViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.teg_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TegViewHolder, position: Int) {
        val currentPosition = holder.adapterPosition
        val teg = tegsList[position]
        holder.itemView.apply {
            tv_teg.text = teg
            setOnClickListener {
                onItemClick?.let { it(teg) }
                selectedItemPosition = currentPosition
                notifyDataSetChanged()
            }
            if (selectedItemPosition == currentPosition) {
                tv_teg.setTextColor(Color.WHITE)
                card_view_teg.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.blue_51_100_224_1
                    )
                )
            } else {
                tv_teg.setTextColor(Color.BLACK)
                card_view_teg.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.white_248_247_245_1
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return tegsList.size
    }

    fun onItemClickListener(listener: (String) -> Unit) {
        onItemClick = listener
    }
}