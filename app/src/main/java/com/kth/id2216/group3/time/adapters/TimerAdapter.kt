package com.kth.id2216.group3.time.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kth.id2216.group3.time.R
import com.kth.id2216.group3.time.data.entities.Timer
import java.time.Duration

/**
 * Extends the [TimerAdapter] class to [RecyclerView.Adapter]
    and implement the unimplemented methods
 */
class TimerAdapter(
    var context: Context?
    ) : RecyclerView.Adapter<TimerAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    var timers: List<Timer>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflating the Layout(Instantiates list_item.xml layout file into View object)
        val view = inflater.inflate(R.layout.list_timers, parent, false)
        // Passing view to ViewHolder
        return ViewHolder(view)
    }

    // Binding data to the into specified position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //necessary check that the timers array is not null
        if (timers != null) {
            holder.id.text = timers!![position].id.toString()
            holder.name.text = timers!![position].name
            holder.goal.text = "${timers!![position].goal.toHours()} h"
            val duration = timers!![position].time
            holder.time.text = "${duration.toHours()} h ${duration.minusHours(duration.toHours()).toMinutes()} m"
            if (timers!![position].categoryId != -1) {
                holder.categories.text = timers!![position].categoryId.toString()
            } else {
                holder.categories.text = ""
            }

            val goal = timers!![position].goal
            val time = timers!![position].time

            if (goal != Duration.ZERO) {
                val g = goal.toMillis()
                val t = time.toMillis()
                val progress = Math.floor((t.toDouble() / g.toDouble()) * 100)
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
        if (timers == null) {
            return 0
        }
        return timers!!.size
    }

    // Initializing the Views
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var id: TextView = view.findViewById(R.id.timer_id)
        var name: TextView = view.findViewById(R.id.item_name)
        var goal: TextView = view.findViewById(R.id.item_goal)
        var time: TextView = view.findViewById(R.id.item_hours)
        var categories: TextView = view.findViewById(R.id.item_categories)
        var pbar: ProgressBar = view.findViewById(R.id.item_progress)
        //var bottomButton: View = view.findViewById(R.id.item_button_divider)
        var timerIsRunning = 0

        /*
            init {

                bottomButton.setOnClickListener {
                    val btnStart = view.findViewById<Button>(R.id.item_button_start)
                    val btnPause = view.findViewById<Button>(R.id.item_button_stop)

                    if(timerIsRunning == 1) {
                        btnPause.visibility = View.VISIBLE
                        btnStart.visibility = View.INVISIBLE
                        timerIsRunning = 0
                    }
                    else if(timerIsRunning == 0) {
                        btnPause.visibility = View.INVISIBLE
                        btnStart.visibility = View.VISIBLE
                        timerIsRunning = 1
                    }
                }
            }
        */
    }

}