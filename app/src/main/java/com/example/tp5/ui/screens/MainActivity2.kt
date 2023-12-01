package com.example.tp5.ui.screens

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.tp5.MainActivity
import com.example.tp5.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        bottomNav = findViewById(R.id.bottomNavigationView)
        bottomNav.setOnItemSelectedListener  {
            when(it.itemId){
                R.id.detail -> {
                    navController.navigate(
                        R.id.detail,
                        Bundle().apply {
                            putString("stopName", "Elm Street")
                        }
                    )
                    true
                }
                R.id.list -> {
                    navController.popBackStack()
                    navController.navigate(R.id.list)
                    true
                }

                else -> false
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_rf, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_back)
        {
            navController.navigate(R.id.list)
        }
        return super.onOptionsItemSelected(item)
    }
}