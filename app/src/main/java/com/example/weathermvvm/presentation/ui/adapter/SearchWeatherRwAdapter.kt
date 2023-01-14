package com.example.weathermvvm.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weathermvvm.R


class SearchWeatherRwAdapter() : RecyclerView.Adapter<SearchWeatherRwAdapter.SearchWeatherHolder>() {
    class SearchWeatherHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    private var cityList = mutableListOf<>()

    fun setCity(city: List<>) {
        this.cityList = city.toMutableList()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SearchWeatherHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.weather_list_item, viewGroup, false)
        return SearchWeatherHolder(view)
    }

    override fun getItemCount(): Int = cityList.size

    override fun onBindViewHolder(holder: SearchWeatherHolder, position: Int) {
        val cities = cityList[position]

    }
}