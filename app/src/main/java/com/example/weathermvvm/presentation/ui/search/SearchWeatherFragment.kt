package com.example.weathermvvm.presentation.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weathermvvm.R
import com.example.weathermvvm.databinding.FragmentSearchWeatherBinding
import com.example.weathermvvm.domain.model.weather.WeatherSearchResponse
import com.example.weathermvvm.extensions.onTextChange
import com.example.weathermvvm.presentation.ui.adapter.SearchWeatherRwAdapter
import com.example.weathermvvm.util.StringConstants
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

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

        viewModelSearch.weatherOnSuccessResponse.observe(viewLifecycleOwner) { response ->
            if (response != null) {
                with(searchWeatherRwAdapter) {
                    setResponse(response)
                    notifyDataSetChanged()
                }
                successResponseUiSetup(response)
            } else {
                unknownPlaceUiSetup()
            }
        }

        viewModelSearch.onErrorResponse.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireActivity(), errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()

        setFragmentResultListener(StringConstants.fromFavoriteToSearchKey) { key, bundle ->
            val result = bundle.getString(StringConstants.fromFavoriteToSearchDataName)
            result?.let {
                setNameToEditView(result)
                bundle.remove(StringConstants.fromFavoriteToSearchDataName)
            }
        }

        binding.searchLocationField.onTextChange { query ->
            if (binding.searchLocationField.text.isNotEmpty()) {
                doRequest(query)
            } else {
                emptySearchFieldUiSetup()
            }
        }

        with(binding) {
            if (tvPlaceName.text.isEmpty()) {
                rwWeather.isGone = true
            }
        }
    }

    private fun successResponseUiSetup(response: WeatherSearchResponse) {
        binding.apply {
            val locationName = response.city.name
            val latitude = response.city.coord.lat
            val longitude = response.city.coord.lon
            progressBar.isGone = true
            rwWeather.isVisible = true
            tvPlaceName.text = locationName
            favoritePlaceLogicSetup(locationName, latitude, longitude)
        }
    }

    private fun favoritePlaceLogicSetup(
        locationName: String,
        latitude: Double,
        longitude: Double
    ) {
        with(binding.ibFavorite) {
            isVisible = true

            viewModelSearch.checkIfLocationInFavorite(locationName)
            viewModelSearch.isLocationInFavorite.observe(viewLifecycleOwner) { inFavorite ->
                if (inFavorite) {
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

    private fun unknownPlaceUiSetup() {
        with(binding) {
            tvPlaceName.text = StringConstants.unknownPlace
            progressBar.isGone = true
            rwWeather.isGone = true
            ibFavorite.isGone = true
        }
    }

    private fun emptySearchFieldUiSetup() {
        with(binding) {
            tvPlaceName.text = StringConstants.whenSearchFieldIsEmpty
            progressBar.isGone = true
            rwWeather.isGone = true
            ibFavorite.isGone = true
        }
    }

    private fun doRequest(query: String) {
        viewModelSearch.getResponse(query)
        binding.progressBar.isVisible = true
        binding.rwWeather.isGone = true
    }

    private fun setNameToEditView(result: String) {
        binding.searchLocationField.setText(result)
        doRequest(result)
    }
}