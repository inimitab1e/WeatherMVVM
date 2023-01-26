package com.example.weathermvvm.presentation.ui.search

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weathermvvm.R
import com.example.weathermvvm.databinding.FragmentSearchWeatherBinding
import com.example.weathermvvm.extensions.onTextChange
import com.example.weathermvvm.presentation.ui.adapter.SearchWeatherRwAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SearchWeatherFragment : Fragment(R.layout.fragment_search_weather) {

    private val binding by viewBinding(FragmentSearchWeatherBinding::bind)
    private val viewModelSearch: SearchWeatherViewModel by viewModels()
    private val searchWeatherRwAdapter: SearchWeatherRwAdapter by lazy {
        SearchWeatherRwAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rwWeather.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = searchWeatherRwAdapter
        }
    }

    override fun onResume() {
        super.onResume()

        with(binding) {
            searchLocationField.onTextChange { query ->
                viewModelSearch.getResponse(query)
                progressBar.isVisible = true
                rwWeather.isGone = true
            }
        }

        viewModelSearch.weatherOnSuccessResponse.observe(viewLifecycleOwner) { response ->
            if (response != null) {
                val locationName = response.city.name
                Timber.e("name: %s", locationName)
                val latitude = response.city.coord.lat
                val longitude = response.city.coord.lon
                with(searchWeatherRwAdapter) {
                    setResponse(response)
                    notifyDataSetChanged()
                }
                with(binding) {
                    progressBar.isGone = true
                    rwWeather.isVisible = true
                    tvPlaceName.text = locationName
                    with(ibFavorite) {
                        isVisible = true

                        viewModelSearch.checkIfLocationInFavorite(locationName)
                        viewModelSearch.isLocationInFavorite.observe(viewLifecycleOwner) { flag ->
                            if (flag) {
                                setImageResource(R.drawable.ic_filled_heart)
                                setOnClickListener {
                                    setImageResource(R.drawable.ic_unfilled_heart)
                                    viewModelSearch.deletePlaceFromListOfFavorites(locationName)
                                }
                            } else {
                                setImageResource(R.drawable.ic_unfilled_heart)
                                setOnClickListener {
                                    setImageResource(R.drawable.ic_filled_heart)
                                    viewModelSearch.addToFavorite(
                                        name = locationName,
                                        latitude = latitude,
                                        longitude = longitude
                                    )
                                }
                            }
                        }
                    }
                }
            } else {
                with(binding) {
                    tvPlaceName.text = "Unknown place :("
                    progressBar.isGone = true
                    rwWeather.isGone = true
                    ibFavorite.isGone = true
                }
            }
        }

        with(binding) {
            if (tvPlaceName.text.isEmpty()) {
                rwWeather.isGone = true
            }
        }
    }
}