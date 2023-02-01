package com.example.weathermvvm.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
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
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var navConrtoller: NavController
    private val binding by viewBinding(ActivityMainBinding::bind)
    private val connectivity: NetworkConnection by lazy {
        NetworkConnection(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
        checkConnectivity()
    }

    private fun setupViews() {
        setSupportActionBar(binding.myToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.home_page) as NavHostFragment
        navConrtoller = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navConrtoller)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        return true
    }

    private fun checkConnectivity() {
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

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            // User chose the "Settings" item, show the app settings UI...
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}