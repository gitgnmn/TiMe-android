package com.kth.id2216.group3.time.ui.editCategory


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import com.kth.id2216.group3.time.R
import com.kth.id2216.group3.time.data.entities.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditCategoryFragment : Fragment() {

        private val viewModel: EditCategoryViewModel by viewModels()

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {

            val root = inflater.inflate(R.layout.fragment_edit_category, container, false)

            val receivedId = requireArguments().getInt("categoryId")
            val category = viewModel.getCategory(receivedId)

            val nameEditText: EditText = root.findViewById(R.id.create_category_name)
            category.observe(viewLifecycleOwner, {
                nameEditText.setText(it.name)
            })

            setupEditButton(root, category)

            setupDeleteButton(root, category)

            return root
        }

    private fun setupEditButton(root: View, category: LiveData<Category>) {

        val createButton: Button = root.findViewById(R.id.edit_category_button)
        createButton.setOnClickListener {
            val nameEditText: EditText = root.findViewById(R.id.create_category_name)

            // check if te value of the goal is set up and positive
            if (nameEditText.text.toString() != "" ) {
                //updating the name
                category.observe(viewLifecycleOwner, {
                    it.name = nameEditText.text.toString()
                    viewModel.updateCategory(it)
                })
                //avoid the annoying go back on the create menu
                root.findNavController().popBackStack()
                root.findNavController().popBackStack()
                root.findNavController().navigate(R.id.nav_categories)
            } else {
                val toast = Toast.makeText(this.context, getString(R.string.message_name_category), Toast.LENGTH_LONG)
                toast.show()
            }
        }

    }

    private fun setupDeleteButton(root: View, category: LiveData<Category>) {

        val deleteButton: Button = root.findViewById(R.id.delete_category_button)
        deleteButton.setOnClickListener {
            category.observe(viewLifecycleOwner, {
                viewModel.deleteCategory(it)
            })
            //avoid the annoying go back on the create menu
            root.findNavController().popBackStack()
            root.findNavController().popBackStack()
            root.findNavController().navigate(R.id.nav_categories)
        }
    }



}
