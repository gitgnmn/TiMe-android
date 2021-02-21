package com.kth.id2216.group3.time.ui.createcategory

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.kth.id2216.group3.time.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateCategoryFragment : Fragment() {

    private val viewModel: CreateCategoryViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_create_category, container, false)

        val createButton: Button = root.findViewById(R.id.create_category_button)
        createButton.setOnClickListener {
            val nameEditText: EditText = root.findViewById(R.id.create_category_name)

            // check if te value of the goal is set up and positive
            if (nameEditText.text.toString() != "" ) {
                viewModel.addCategory(
                    nameEditText.text.toString()
                )
                //avoid the annoying go back on the create menu
                root.findNavController().popBackStack()
                root.findNavController().popBackStack()
                root.findNavController().navigate(R.id.nav_categories)
            } else {
                val toast = Toast.makeText(this.context, getString(R.string.message_name_category), Toast.LENGTH_LONG)
                toast.show()
            }
        }

        return root
    }



}