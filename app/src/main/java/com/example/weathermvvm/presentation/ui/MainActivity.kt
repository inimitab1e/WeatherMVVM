package com.example.weathermvvm.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weathermvvm.R
import com.example.weathermvvm.databinding.ActivityMainBinding
import com.example.weathermvvm.utils.NetworkConnection
import com.example.weathermvvm.utils.SettingsSharedPreferences
import com.example.weathermvvm.utils.StringConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var navConrtoller: NavController
    private val binding by viewBinding(ActivityMainBinding::bind)
    private val connectivity: NetworkConnection by lazy {
        NetworkConnection(this)
    }
    private val settingsSharesPref: SettingsSharedPreferences by lazy {
        SettingsSharedPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNavigation()
        checkAppThemeState()
        checkConnectivity()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.home_page) as NavHostFragment
        navConrtoller = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navConrtoller)

        navConrtoller.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.settingsFragment) {
                binding.bottomNavigationView.isGone = true
            } else {
                binding.bottomNavigationView.isVisible = true
            }
        }
    }

    private fun checkAppThemeState() {
        val state = settingsSharesPref.getAppThemeState()
        if (state) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun checkConnectivity() {
        connectivity.observe(this) { isConnected ->
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> handleOnSettingsButtonClick()
        android.R.id.home -> handleOnHomeActionbarButtonClick()
        else -> super.onOptionsItemSelected(item)
    }

    private fun handleOnSettingsButtonClick(): Boolean {
        navConrtoller.navigate(R.id.open_settings_fragment)
        binding.bottomNavigationView.isGone = true
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = StringConstants.settingsActionBarTitle
        return true
    }

    private fun handleOnHomeActionbarButtonClick(): Boolean {
        navConrtoller.navigate(navConrtoller.previousBackStackEntry!!.destination.id)
        binding.bottomNavigationView.isVisible = true
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = getString(R.string.app_name)
        return true
    }
}