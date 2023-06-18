package com.example.foodapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.viewmodel.HomeViewModel
import com.example.foodapp.MainActivity
import com.example.foodapp.R
import com.example.foodapp.adapters.CartAdapter
import com.example.foodapp.util.DateImplementation
import com.example.foodapp.util.LocationImplementation
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.view_user_geo_date.*


class CartFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_date.text = DateImplementation().getDate()
        LocationImplementation().getCurrentLocation(requireContext())
        prepareRecyclerView()
        observeFavoritesLiveData()
    }

    private fun observeFavoritesLiveData() {
        viewModel.observeFavoritesLiveData().observe(viewLifecycleOwner, Observer { dishes ->
            cartAdapter.setFavorites(dishes)
            var sum = 0
            if (dishes.isNotEmpty()) {
                dishes.forEach { dish ->
                    sum += (dish.price * dish.amount)
                    val sumString = "Оплатить $sum ₽"
                    tv_sum.text = sumString
                }
            } else {
                tv_sum.text = "Оплатить"
            }
        })

    }

    private fun prepareRecyclerView() {
        cartAdapter = CartAdapter(viewModel)
        rv_cart.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = cartAdapter
        }
    }
}