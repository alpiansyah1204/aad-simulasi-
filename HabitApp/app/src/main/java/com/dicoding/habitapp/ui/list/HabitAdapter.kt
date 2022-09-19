package com.dicoding.habitapp.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.habitapp.R
import com.dicoding.habitapp.data.Habit
import java.util.*

class HabitAdapter(
    private val onClick: (Habit) -> Unit
) : PagedListAdapter<Habit, HabitAdapter.HabitViewHolder>(DIFF_CALLBACK) {

    //TODO 8 : Create and initialize ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.habit_item, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        //TODO 9 : Get data and bind them to ViewHolder
        holder.bind(getItem(position)!!)
    }

    inner class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleTV: TextView = itemView.findViewById(R.id.item_tv_title)
        private val minutesTV: TextView = itemView.findViewById(R.id.item_tv_minutes)
        private val priority: ImageView = itemView.findViewById(R.id.item_priority_level)
        private val startTimeTV: TextView = itemView.findViewById(R.id.item_tv_start_time)


        lateinit var getHabit: Habit
        fun bind(habit: Habit) {
            with(habit) {
                getHabit = habit
                titleTV.text = title
                startTimeTV.text = startTime
                minutesTV.text = minutesFocus.toString()
                itemView.setOnClickListener {
                    onClick(habit)
                }
            }
            val colorPriority = when (habit.priorityLevel.lowercase(Locale.getDefault())) {
                "high" -> R.drawable.ic_priority_high
                "medium" -> R.drawable.ic_priority_medium
                "low" -> R.drawable.ic_priority_low
                else -> R.drawable.ic_priority_low
            }
            priority.setImageResource(colorPriority)
        }

    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Habit>() {
            override fun areItemsTheSame(oldItem: Habit, newItem: Habit): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Habit, newItem: Habit): Boolean {
                return oldItem == newItem
            }
        }
    }
}