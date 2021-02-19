package com.kth.id2216.group3.time.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kth.id2216.group3.time.R
import com.kth.id2216.group3.time.adapters.CategoryAdapter
import com.kth.id2216.group3.time.data.entities.Category
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment handling the list of categories
 */
@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private val categoriesViewModel: CategoriesViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        //get adapter
        val adapter = CategoryAdapter(context)

        // Insert example data
        val category1 = Category(id = 1, name = "ID2216")
        val category2 = Category(id = 2, name = "Personal projects")

        categoriesViewModel.addCategory(category1)
        categoriesViewModel.addCategory(category2)

        //get data
        categoriesViewModel.getAllCategories().observe(viewLifecycleOwner, {
            adapter.categories = it
        })

        val root = inflater.inflate(R.layout.fragment_categories, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.categories_recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = adapter

        return root
    }







}