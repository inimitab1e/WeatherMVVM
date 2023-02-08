package com.example.weathermvvm.data

import com.example.weathermvvm.data.modelDto.weather.*
import com.example.weathermvvm.domain.model.coordinates.LocationCoordsResponseItem
import com.example.weathermvvm.domain.model.weather.*

fun WeatherSearchResponseDto.toWeatherSearchResponse() = WeatherSearchResponse(
    city = city.toCity(),
    list = list.map { commonInfoDto -> commonInfoDto.toCommonInfo() }
)

private fun CityDto.toCity() = City(
    name = name,
    coord = coord.toCoord()
)

private fun CoordDto.toCoord() = Coord(
    lat = lat,
    lon = lon
)

private fun CommonInfoDto.toCommonInfo() = CommonInfo(
    dt_txt = dt_txt,
    clouds = clouds.toClouds(),
    main = main.toMain(),
    weather = weather.map { weatherDto -> weatherDto.toWeather() },
    wind = wind.toWind()
)

private fun CloudsDto.toClouds() = Clouds(
    all = all
)

private fun MainDto.toMain() = Main(
    pressure = pressure,
    temp_max = temp_max,
    temp_min = temp_min,
    humidity = humidity
)

private fun WeatherDto.toWeather() = Weather(
    main = main
)

private fun WindDto.toWind() = Wind(
    speed = speed
)