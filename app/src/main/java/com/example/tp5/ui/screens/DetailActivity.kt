package com.example.tp5.ui.screens

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp5.MainActivity
import com.example.tp5.R
import com.example.tp5.core.database.BusScheduleApplication
import com.example.tp5.core.database.models.Schedule
import com.example.tp5.core.viewmodels.BusScheduleViewModel
import com.example.tp5.core.viewmodels.BusScheduleViewModelFactory
import com.example.tp5.databinding.ActivityDetailBinding
import com.example.tp5.ui.adapters.BusStopAdapter


class DetailActivity : AppCompatActivity() {
    lateinit var stopName: TextView
    private lateinit var binding: ActivityDetailBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit  var busStopAdapter: BusStopAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        stopName = binding.station
        recyclerView= binding.recyclerView2
        val stopName = intent.getStringExtra("stopName")
        this.stopName.text = stopName
        recyclerView.layoutManager = LinearLayoutManager(this)
        busStopAdapter = BusStopAdapter(emptyList()) { schedule ->
            print("hola")
        }
        var list: LiveData<List<Schedule>>

        if(stopName != null){
            Thread {
                list = viewModel.scheduleForStopName(stopName)
                Handler(Looper.getMainLooper()).post {
                    viewModel.scheduleForStopName(stopName).observe(this) {

                        busStopAdapter.updateList(it)
                    }
                }
            }.start()
        }
        recyclerView.adapter = busStopAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_rf, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_back)
        {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    private val viewModel : BusScheduleViewModel by viewModels() {
        BusScheduleViewModelFactory(
            (application as
                    BusScheduleApplication).database.scheduleDao()
        )}
}