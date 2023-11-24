package com.example.tp5.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tp5.core.database.dao.ScheduleDao
import com.example.tp5.core.database.models.Schedule

@Database(entities = [Schedule::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun scheduleDao(): ScheduleDao

    companion object {
        private const val DATABASE_NAME = "bus_schedule"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            INSTANCE?.let { return it }
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .createFromAsset("database/bus_schedule.db")
                .build()

            INSTANCE = instance

            return instance

        }
    }
}