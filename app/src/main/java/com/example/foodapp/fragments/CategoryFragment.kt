package com.example.foodapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.*
import com.example.foodapp.fragments.CategoryFragmentArgs
import com.example.foodapp.adapters.DishesAdapter
import com.example.foodapp.adapters.TegsAdapter
import com.example.foodapp.models.Category
import com.example.foodapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_category.*

class CategoryFragment : Fragment() {
    private val args: CategoryFragmentArgs by navArgs()
    private lateinit var category: Category
    private lateinit var viewModel: HomeViewModel
    lateinit var dishesAdapter: DishesAdapter
    lateinit var tegsAdapter: TegsAdapter
    private var tegs = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = args.category
        viewModel = (activity as MainActivity).viewModel
        dishesAdapter = DishesAdapter()
        tegsAdapter = TegsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        category.apply {
            tv_category.text = name
        }

        prepareDishesRecyclerView()
        prepareTegsRecyclerView()
        viewModel.getDishes()
        observeDishesLiveData()
        onDishItemClick()
        onTegItemClick()

        iv_arrow_back.setOnClickListener {
            findNavController().navigate(R.id.action_categoryFragment_to_homeFragment)
        }
    }

    private fun prepareTegsRecyclerView() {
        rv_tegs.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = tegsAdapter
        }
    }

    private fun onDishItemClick() {
        dishesAdapter.onItemClickListener {
            DishDetailsDialog(it).show(childFragmentManager, DishDetailsDialog.TAG)
        }
    }

    private fun observeDishesLiveData() {
        viewModel.observeDishesLiveData().observe(viewLifecycleOwner) { dishes ->
            dishes.forEach { dish ->
                dish.tegs.forEach { teg ->
                    if (teg != null) {
                        tegs.add(teg)
                    }
                }
            }
            dishesAdapter.setDishesList(dishes)
            tegsAdapter.setTegList(tegs.toList())
        }
    }

    private fun onTegItemClick() {
        tegsAdapter.onItemClickListener { teg ->
            viewModel.observeDishesLiveData().observe(viewLifecycleOwner) { dishes ->
                val filteredList = dishes.filter { dish ->
                    dish.tegs.contains(teg)
                }
                dishesAdapter.setDishesList(filteredList)
            }
        }
    }

    private fun prepareDishesRecyclerView() {
        rv_dishes.apply {
            layoutManager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
            adapter = dishesAdapter
        }
    }
}