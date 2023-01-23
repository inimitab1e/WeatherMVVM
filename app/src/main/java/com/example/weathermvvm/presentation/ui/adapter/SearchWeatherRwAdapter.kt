package com.example.weathermvvm.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weathermvvm.R
import com.example.weathermvvm.databinding.WeatherListItemBinding
import com.example.weathermvvm.domain.model.weather.CommonInfo
import com.example.weathermvvm.domain.model.weather.WeatherSearchResponse


class SearchWeatherRwAdapter :
    RecyclerView.Adapter<SearchWeatherRwAdapter.SearchWeatherHolder>() {

    class SearchWeatherHolder(binding: WeatherListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var date = binding.tvDate
        val tempMax = binding.tvTempMax
        val tempMin = binding.tvTempMin
        val humidity = binding.tvHumidity
        val pressure = binding.tvPressure
        val windSpeed = binding.tvWindSpeed
        val weatherIcon = binding.ivWeatherIcon
        val weatherIconDescription = binding.tvDescription
    }

    private var response: List<CommonInfo> = listOf()

    fun setResponse(weatherResponse: WeatherSearchResponse) {
        response = weatherResponse.list
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SearchWeatherHolder {
        val binding =
            WeatherListItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return SearchWeatherHolder(binding)
    }

    override fun getItemCount(): Int = response.size

    override fun onBindViewHolder(holder: SearchWeatherHolder, position: Int) {
        val weatherInfo = response[position]
        holder.date.text = weatherInfo.dt_txt[position].toString()
        holder.tempMax.text = weatherInfo.main.temp_max.toString()
        holder.tempMin.text = weatherInfo.main.temp_min.toString()
        holder.pressure.text = weatherInfo.main.pressure.toString()
    }
}