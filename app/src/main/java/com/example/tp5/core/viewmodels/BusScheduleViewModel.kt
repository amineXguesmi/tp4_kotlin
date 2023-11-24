package com.example.tp5.core.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tp5.core.database.dao.ScheduleDao
import com.example.tp5.core.database.models.Schedule

class BusScheduleViewModel(private val scheduleDao:
                           ScheduleDao
): ViewModel() {
    fun fullSchedule(): LiveData<List<Schedule>> = scheduleDao.getAll()

    fun scheduleForStopName(name: String): LiveData<List<Schedule>> =
        scheduleDao.getByStopName(name)


                           }