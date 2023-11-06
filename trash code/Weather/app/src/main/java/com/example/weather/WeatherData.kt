data class WeatherData(
    val temp: Double,
    val feels_like: Double,
    val temp_water: Double,
    val icon: String,
    val condition: String,
    val wind_speed: Double,
    val wind_gust: Double,
    val wind_dir: String,
    val pressure_mm: Int, // Изменено на Int
    val humidity: Int,
    val daytime: String,
    val polar: Boolean, // Изменено на Boolean
    val season: String,
    val obs_time: Long
)