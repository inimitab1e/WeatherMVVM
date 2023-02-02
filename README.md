# WeatherMVVM
### Project Description
This is a weather (5 Day / 3 Hour) forecast Android Application with MVVM pattern.

### Features
- Searching weather by name;
- Searching weather by location on map;
- List of favorite places.

### Libraries
- Navigation
- RecyclerView
- Retrofit
- Hilt
- LiveData
- Serialization
- Coroutines
- Room
- Timber

### API
I used [Open Weather Map API](https://openweathermap.org/api) for collecting weather information and [Open Street Map](https://github.com/osmdroid/osmdroid) for map.

### Project Setup
Clone the project and open it using Android Studio. Then create `apikey.properties` in root file. You need to specify the `api_key`, `base_url_coordinates` and `base_url_weather` in your `apikey.properties` file. You can find this values here: [Forecast API](https://openweathermap.org/forecast5), [Coordinates API](https://openweathermap.org/api/geocoding-api)

Example:
```properties
#this is apikey.properties example
api_key="your api key"
base_url_coordinates="https://api.openweathermap.org/geo/1.0/direct?"
base_url_weather="https://api.openweathermap.org/data/2.5/forecast?"
```
