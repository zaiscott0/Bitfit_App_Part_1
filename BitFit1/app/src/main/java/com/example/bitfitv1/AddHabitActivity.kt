package com.example.bitfitv1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class AddHabitActivity : AppCompatActivity() {
    private lateinit var etRate: EditText
    private lateinit var etComment: EditText
    private lateinit var skHours: SeekBar
    private lateinit var btnAddEntry: Button
    private lateinit var skBarValue: TextView
    private var currentTime: Date = Calendar.getInstance().time
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_habit)
        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val currentDataTime: String = sdf.format(Date())
        etComment = findViewById(R.id.etComment)
        skHours = findViewById(R.id.seekBarHour)
        btnAddEntry = findViewById(R.id.btnAddItem)
        skBarValue = findViewById(R.id.tvValueSeekBar)
        etRate = findViewById(R.id.etRate)
        skHours.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                skBarValue.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
        btnAddEntry.setOnClickListener {
            val intent = Intent(this, MainHabitActivity::class.java)
            val habit = Habit(currentDataTime, skBarValue.text.toString().toInt(), etComment.text.toString(), etRate.text.toString().toInt())
            intent.putExtra("ENTRY_EXTRA",habit)
            this.startActivity(intent)
        }


    }
}