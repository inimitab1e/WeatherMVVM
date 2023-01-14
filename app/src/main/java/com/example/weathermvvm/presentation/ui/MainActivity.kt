package com.example.weathermvvm.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.weathermvvm.R
import com.example.weathermvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navConrtoller: NavController
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupViews()
    }

    private fun setupViews() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.home_page) as NavHostFragment
        navConrtoller = navHostFragment.navController
        binding?.bottomNavigationView?.let { bottomNavigationView ->
            NavigationUI.setupWithNavController(bottomNavigationView, navConrtoller)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}