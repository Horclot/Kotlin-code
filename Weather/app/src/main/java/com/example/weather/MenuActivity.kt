package com.example.weather

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

class MenuActivity : AppCompatActivity() {
    private lateinit var retrofit: Retrofit
    private lateinit var weatherText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val cityText: TextView = findViewById(R.id.cityText)
        val weather2 = intent.getStringExtra("DataKey")
        cityText.text = weather2
        val previousCityTextView: TextView = findViewById(R.id.previousCityTextView)
        val previousCity = intent.getStringExtra("previousCity")

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.weather.yandex.ru/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        weatherText = findViewById(R.id.weatherText)

        if (previousCity != null && previousCity.isNotEmpty()) {
            previousCityTextView.text = "Предыдущий город: $previousCity"
            cityText.text = previousCity
            getWeatherData(previousCity)
        } else {
            weather2?.let { getWeatherData(it) }
            cityText.text = weather2
        }
    }

    private fun getWeatherData(city: String) {
        val language = "ru_RU"
        val apiKey = "edb27374-2457-4d9d-a783-5645e6c061d3" // Заменен на предоставленный ключ

        val service = retrofit.create(WeatherApiService::class.java)
        val call = service.getWeather(city, language, apiKey)

        call.enqueue(object : Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                try {
                    if (response.isSuccessful) {
                        val weatherData = response.body()
                        if (weatherData != null) {
                            // Обработайте полученные данные о погоде
                            Log.d("Weather", "Температура: ${weatherData.temp}°C")
                            // Выводим погоду в TextView
                            weatherText.text = "Температура: ${weatherData.temp}°C"
                        } else {
                            Log.e("Weather", "Пустой ответ от сервера")
                        }
                    } else {
                        Log.e("Weather", "Ошибка при запросе к API: ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("Weather", "Ошибка при обработке данных", e)
                }
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                Log.e("Weather", "Ошибка при выполнении запроса", t)
            }
        })
    }
}

interface WeatherApiService {
    @GET("forecast")
    fun getWeather(
        @Query("q") city: String,
        @Query("lang") language: String,
        @Header("X-Yandex-API-Key") apiKey: String
    ): Call<WeatherData>
}

data class WeatherData(
    val temp: Double,
    val feels_like: Double,
    val temp_water: Double,
    val icon: String,
    val condition: String,
    val wind_speed: Double,
    val wind_gust: Double,
    val wind_dir: String,
    val pressure_mm: Double,
    val humidity: Int,
    val daytime: String,
    val polar: String,
    val season: String,
    val obs_time: Long
)
