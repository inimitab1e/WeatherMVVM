package com.example.weathermvvm.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weathermvvm.R
import com.example.weathermvvm.databinding.ActivityMainBinding
import com.example.weathermvvm.utils.NetworkConnection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var navConrtoller: NavController
    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
        checkConnectivity()
    }

    private fun setupViews() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.home_page) as NavHostFragment
        navConrtoller = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navConrtoller)
    }

    private fun checkConnectivity() {
        val connectivity = NetworkConnection(this)
        connectivity.observe(this){
                isConnected ->
             if (!isConnected) {
                with(binding) {
                    bottomNavigationView.isVisible = false
                    badNetworkConnectionPage.isVisible = true
                }
            } else {
                 with(binding) {
                     bottomNavigationView.isVisible = true
                     badNetworkConnectionPage.isGone = true
                 }
             }
        }
    }
}