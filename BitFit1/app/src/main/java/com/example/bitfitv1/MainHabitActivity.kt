package com.example.bitfitv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainHabitActivity : AppCompatActivity() {
    private val habits = mutableListOf<Habit>()
    private lateinit var rvFeed: RecyclerView
    private lateinit var btnAdd: Button
    private lateinit var tvAverage: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvFeed = findViewById(R.id.rvHabits)
        val habitAdapter = HabitAdapter(this, habits)
        rvFeed.adapter = habitAdapter
        rvFeed.layoutManager = LinearLayoutManager(this)
        lifecycleScope.launch {
            (application as BitFitApplication).db.habitDao().getAll().collect() {
                databaseList ->
                databaseList.map { entity ->
                    Habit(
                        entity.date,
                        entity.hour,
                        entity.comment,
                        entity.rate
                    )
                }.also { mappedList ->
                    habits.clear()
                    habits.addAll(mappedList)
                    habitAdapter.notifyDataSetChanged()

                }
            }
        }

        val habit = intent.getSerializableExtra("ENTRY_EXTRA")
        if (habit != null){
            Log.d("AddActivity", "got an extra")
            Log.d("AddActivity", (habit as Habit).toString())
            lifecycleScope.launch(Dispatchers.IO) {
                (application as BitFitApplication).db.habitDao().insert(
                    HabitEntity(
                        date = habit.date,
                        hour = habit.hour,
                        comment = habit.comment,
                        rate = habit.rate
                    )
                )
            }
        } else {
            Log.d("AddActivity", "no extra")
        }
        btnAdd = findViewById(R.id.btnNewEntry)
        btnAdd.setOnClickListener {
            Log.d("AddActivity", "add item Clicked")
            val intent = Intent(this, AddHabitActivity::class.java)
            this.startActivity(intent)
        }

    }
}