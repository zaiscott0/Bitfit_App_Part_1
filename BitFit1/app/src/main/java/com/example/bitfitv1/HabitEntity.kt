package com.example.bitfitv1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "habit_table")
data class HabitEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "hour") val hour: Int?,
    @ColumnInfo(name = "comment") val comment: String?,
    @ColumnInfo(name= "rate") val rate: Int?
        )