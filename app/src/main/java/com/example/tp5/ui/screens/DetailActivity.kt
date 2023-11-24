package com.example.tp5.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.tp5.MainActivity
import com.example.tp5.R
import com.example.tp5.ui.fragments.map2
import com.example.tp5.ui.fragments.map_fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat

class DetailActivity : AppCompatActivity() {
    lateinit var stopName: TextView
    lateinit var arrivalDate: TextView
    lateinit var arrivalTime: TextView
    private lateinit var navController: NavController
    lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        stopName = findViewById(R.id.station)
        arrivalTime = findViewById(R.id.date)
        arrivalDate = findViewById(R.id.date2)
        val stopName = intent.getStringExtra("stopName")
        val arrival = intent.getStringExtra("arrival")
        this.stopName.text = stopName
        if (arrival != null) {
            this.arrivalDate.text = arrival.getDate()
            this.arrivalTime.text = arrival.getTime()
        }
        supportFragmentManager.beginTransaction().replace(R.id.map, map_fragment()).commit()
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.map1 -> {
                    val fragment= map_fragment()
                    val transaction = supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.map, fragment)
                     transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }
                R.id.map2 -> {
                    val fragment= map2()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.map, fragment)
                transaction.addToBackStack(null)
                 transaction.commit()
                    true
                }
                else -> false
            }
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    @SuppressLint("SimpleDateFormat")
    private fun String.getTime(): String {
        val formatter = SimpleDateFormat("HH:mm:ss dd/MM/yyyy")
        val date = formatter.parse(this)

        val timeFormatter = SimpleDateFormat("HH:mm:ss")
        return timeFormatter.format(date)
    }

    @SuppressLint("SimpleDateFormat")
    private fun String.getDate(): String {
        val formatter = SimpleDateFormat("HH:mm:ss dd/MM/yyyy")
        val date = formatter.parse(this)

        val dateFormatter = SimpleDateFormat("dd/MM/yyyy")
        return dateFormatter.format(date)
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
}