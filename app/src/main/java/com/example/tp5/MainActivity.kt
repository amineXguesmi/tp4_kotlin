package com.example.tp5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp5.core.database.BusScheduleApplication
import com.example.tp5.core.database.models.Schedule
import com.example.tp5.core.viewmodels.BusScheduleViewModel
import com.example.tp5.core.viewmodels.BusScheduleViewModelFactory
import com.example.tp5.databinding.ActivityMainBinding
import com.example.tp5.ui.adapters.BusStopAdapter
import com.example.tp5.ui.screens.DetailActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit  var busStopAdapter: BusStopAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        busStopAdapter = BusStopAdapter(emptyList()) { schedule ->
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra("stopName", schedule.stop_name)
            startActivity(intent)
        }

        var list: LiveData<List<Schedule>>

        Thread {
            list = viewModel.fullSchedule()
            Handler(Looper.getMainLooper()).post {
                viewModel.fullSchedule().observe(this) {
                    busStopAdapter.updateList(it)
                }
            }
        }.start()
        recyclerView.adapter = busStopAdapter
    }

    private val viewModel : BusScheduleViewModel by viewModels() {
        BusScheduleViewModelFactory(
            (application as
                    BusScheduleApplication).database.scheduleDao()
        )
    }


}