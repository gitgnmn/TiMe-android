package com.kth.id2216.group3.time.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.kth.id2216.group3.time.R
import com.kth.id2216.group3.time.data.entities.Timer

/**
 * Extends the [TimerAdapter] class to [RecyclerView.Adapter]
    and implement the unimplemented methods
 */
class TimerAdapter(
    var context: Context?,
    private var timers: LiveData<List<Timer>>
    ) : RecyclerView.Adapter<TimerAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflating the Layout(Instantiates list_item.xml layout file into View object)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        // Passing view to ViewHolder
        return ViewHolder(view)
    }

    // Binding data to the into specified position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //necessary check that the timers array is not null
        if (timers.value != null) {

            holder.name.text = timers.value!![position].name
            holder.goal.text = timers.value!![position].getGoalFormatted()
            holder.hours.text = timers.value!![position].getHoursFormatted()
            if (timers.value!![position].categoryId != -1) {
                holder.categories.text = timers.value!![position].categoryId.toString()
            } else {
                holder.categories.text = ""
            }


            val goal = timers.value!![position].goal
            val hours = timers.value!![position].hours

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
    }

    /**
     * Returns number of items currently available in Adapter
     */
    override fun getItemCount(): Int {
        if (timers.value == null) {
            return 0
        }
        return timers.value!!.size
    }

    // Initializing the Views
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById<View>(R.id.item_name) as TextView
        var goal: TextView = view.findViewById<View>(R.id.item_goal) as TextView
        var hours: TextView = view.findViewById<View>(R.id.item_hours) as TextView
        var categories: TextView = view.findViewById<View>(R.id.item_categories) as TextView
        var pbar: ProgressBar = view.findViewById<View>(R.id.item_progress) as ProgressBar

    }

}