package com.example.foodapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.viewmodel.HomeViewModel
import com.example.foodapp.MainActivity
import com.example.foodapp.R
import com.example.foodapp.adapters.CategoriesAdapter
import com.example.foodapp.util.DateImplementation
import com.example.foodapp.util.LocationImplementation
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_user_geo_date.*


class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    lateinit var categoriesAdapter: CategoriesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        categoriesAdapter = CategoriesAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_date.text = DateImplementation().getDate()
        LocationImplementation().getCurrentLocation(requireContext())
        prepareCategoriesRecyclerView()
        viewModel.getCategories()
        observeCategoriesLiveData()
        onCategoryItemClick()
    }


    private fun onCategoryItemClick() {
        categoriesAdapter.onItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("category", it)
            }
            findNavController().navigate(R.id.action_homeFragment_to_categoryFragment, bundle)
        }
    }

    private fun observeCategoriesLiveData() {
        viewModel.observeCategoriesLiveData()
            .observe(viewLifecycleOwner) { categories ->
                categoriesAdapter.setCategoryList(categories)
            }
    }

    private fun prepareCategoriesRecyclerView() {
//        categoriesAdapter = CategoriesAdapter()
        rv_categories.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter
        }
    }
}