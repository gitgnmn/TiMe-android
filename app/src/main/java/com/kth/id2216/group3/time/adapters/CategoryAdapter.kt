package com.kth.id2216.group3.time.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kth.id2216.group3.time.R
import com.kth.id2216.group3.time.data.entities.Category

class CategoryAdapter(
        var context: Context?
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    var categories: List<Category>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflating the Layout(Instantiates list_item.xml layout file into View object)
        val view = inflater.inflate(R.layout.list_categories, parent, false)
        // Passing view to ViewHolder
        return ViewHolder(view)
    }

    // Binding data to the into specified position
    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        //necessary check that the categories array is not null
        if (categories != null) {
            holder.name.text = categories!![position].name
        } else {
            holder.name.text = ""
        }
    }



    /**
     * Returns number of items currently available in Adapter
     */
    override fun getItemCount(): Int {
        if (categories == null) {
            return 0
        }
        return categories!!.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById<View>(R.id.category_name) as TextView
    }
}