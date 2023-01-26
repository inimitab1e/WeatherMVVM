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

    class SearchWeatherHolder(binding: WeatherListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val date = binding.tvDate
        val tempMax = binding.tvTempMax
        val tempMin = binding.tvTempMin
        val humidity = binding.tvHumidity
        val pressure = binding.tvPressure
        val windSpeed = binding.tvWindSpeed
        val weatherIcon = binding.ivWeatherIcon
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
        holder.date.text = weatherInfo.dt_txt
        holder.tempMax.text = weatherInfo.main.temp_max.toString()
        holder.tempMin.text = weatherInfo.main.temp_min.toString()
        holder.pressure.text = weatherInfo.main.pressure.toString()
        holder.humidity.text = weatherInfo.main.humidity.toString()
        holder.windSpeed.text = weatherInfo.wind.speed.toString()
        holder.weatherIcon.setImageResource(getWeatherIcon(weatherInfo.weather[0].main))
    }

    private fun getWeatherIcon(weather: String): Int {
        return when (weather) {
            WeatherState.Thunderstorm.toString() -> R.drawable.ic_weather_thunder
            WeatherState.Drizzle.toString() -> R.drawable.ic_weather_drizzle
            WeatherState.Rain.toString() -> R.drawable.ic_weather_rain
            WeatherState.Snow.toString() -> R.drawable.ic_weather_snow
            WeatherState.Clear.toString() -> R.drawable.ic_weather_sun
            WeatherState.Clouds.toString() -> R.drawable.ic_weather_clouds
            else -> R.drawable.ic_weather_mist
        }
    }
}

enum class WeatherState {
    Thunderstorm,
    Drizzle,
    Rain,
    Snow,
    Clear,
    Clouds
}