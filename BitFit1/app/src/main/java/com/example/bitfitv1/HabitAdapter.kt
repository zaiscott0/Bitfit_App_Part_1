package com.example.bitfitv1

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class HabitAdapter (private val context: Context, private val habits: MutableList<Habit>): RecyclerView.Adapter<HabitAdapter.ViewHolder>(){
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private val tvDate = itemView.findViewById<TextView>(R.id.tvDate)
        private val tvComments = itemView.findViewById<TextView>(R.id.tvComments)
        private val tvRate = itemView.findViewById<TextView>(R.id.tvrate)
        private val tvHours = itemView.findViewById<TextView>(R.id.tvHours)
        override fun onClick(v: View?) {
            Log.i("Clicked Adapter", "The click works")
        }
        fun bind(habit: Habit) {
            tvDate.text = habit.date.toString()
            tvComments.text = habit.comment
            tvRate.text = "Feeling " + habit.rate.toString()
            tvHours.text = "Slept " + habit.hour.toString() + " hours"
        }
        init {
            itemView.setOnClickListener(this)
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_habit, viewGroup,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val habit = habits[position]
        holder.bind(habit)
    }

    override fun getItemCount(): Int {
        return habits.size
    }
}