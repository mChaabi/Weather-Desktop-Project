package com.Meteo.service;

import com.Meteo.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherResponse getWeather(String city) {
        String url = apiUrl + "?q=" + city
                + "&appid=" + apiKey
                + "&units=metric&lang=fr";

        Map response = restTemplate.getForObject(url, Map.class);

        // Bloc "main"
        Map main = (Map) response.get("main");

        // Bloc "weather"
        List weatherList = (List) response.get("weather");
        Map weatherInfo = (Map) weatherList.get(0);

        // Bloc "wind"
        Map wind = (Map) response.get("wind");

        // Bloc "sys" (sunrise/sunset)
        Map sys = (Map) response.get("sys");

        // Bloc "coord"
        Map coord = (Map) response.get("coord");

        // Bloc "clouds"
        Map clouds = (Map) response.get("clouds");

        return WeatherResponse.builder()
                .city(city)
                .temperature(toDouble(main.get("temp")))
                .feelsLike(toDouble(main.get("feels_like")))
                .tempMin(toDouble(main.get("temp_min")))
                .tempMax(toDouble(main.get("temp_max")))
                .humidity(toDouble(main.get("humidity")))
                .pressure(toInt(main.get("pressure")))
                .condition((String) weatherInfo.get("description"))
                .icon((String) weatherInfo.get("icon"))
                .windSpeed(toDouble(wind.get("speed")))
                .windDegree(toInt(wind.get("deg")))
                .visibility(toInt(response.get("visibility")))
                .cloudiness(toInt(clouds.get("all")))
                .sunrise(toLong(sys.get("sunrise")))
                .sunset(toLong(sys.get("sunset")))
                .lat(toDouble(coord.get("lat")))
                .lon(toDouble(coord.get("lon")))
                .build();
    }

    // helpers pour éviter les ClassCastException
    private double toDouble(Object val) {
        if (val == null) return 0.0;
        if (val instanceof Integer) return ((Integer) val).doubleValue();
        return (Double) val;
    }

    private int toInt(Object val) {
        if (val == null) return 0;
        if (val instanceof Double) return ((Double) val).intValue();
        return (Integer) val;
    }

    private long toLong(Object val) {
        if (val == null) return 0L;
        if (val instanceof Integer) return ((Integer) val).longValue();
        return (Long) val;
    }
}