package com.kth.id2216.group3.time

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Extends the Adapter class to RecyclerView.Adapter
// and implement the unimplemented methods
class Adapter // Constructor for initialization
(var context: Context, var timers: ArrayList<Map<String, Any>>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflating the Layout(Instantiates list_item.xml layout file into View object)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        // Passing view to ViewHolder
        return ViewHolder(view)
    }

    // Binding data to the into specified position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TypeCast Object to int type
        val name = timers[position]["name"] as String
        val goal = timers[position]["goal"] as Int
        val hours = timers[position]["hours"] as Int
        val categories = timers[position]["categories"] as String

        val goalM = goal%1
        val goalH = goal - goalM
        val goalText = "$goalH h $goalM m"

        val hoursM = hours%1
        val hoursH = hours - hoursM
        val hoursText = "$hoursH h $hoursM m"

        holder.name.text = name
        holder.goal.text = goalText
        holder.hours.text = hoursText
        holder.categories.text = categories

        if (goal != 0) {
            val g = goal.toDouble()
            val h = hours.toDouble()
            val progress = (h / g) * 100
            holder.pbar.progress = progress.toInt()
        }
        else {
            holder.pbar.progress = 0
        }
    }

    override fun getItemCount(): Int {
        // Returns number of items currently available in Adapter
        return timers.size
    }

    // Initializing the Views
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView
        var goal: TextView
        var hours: TextView
        var categories: TextView
        var pbar: ProgressBar

        init {
            name = view.findViewById<View>(R.id.item_name) as TextView
            goal = view.findViewById<View>(R.id.item_goal) as TextView
            hours = view.findViewById<View>(R.id.item_hours) as TextView
            categories = view.findViewById<View>(R.id.item_categories) as TextView
            pbar = view.findViewById<View>(R.id.item_progress) as ProgressBar
        }
    }

}