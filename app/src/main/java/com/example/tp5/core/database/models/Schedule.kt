package com.example.tp5.core.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class Schedule(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val stop_name: String,

    val arrival_time: Int
)