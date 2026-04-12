package com.Meteo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherResponse {
    private String city;
    private double temperature;
    private double feelsLike;      // ressenti
    private double tempMin;        // min du jour
    private double tempMax;        // max du jour
    private String condition;
    private String icon;           // code icône OWM ex: "01d"
    private double humidity;
    private double windSpeed;
    private int windDegree;        // direction du vent en degrés
    private int pressure;          // pression en hPa
    private int visibility;        // visibilité en mètres
    private int cloudiness;        // couverture nuageuse %
    private long sunrise;          // timestamp lever soleil
    private long sunset;           // timestamp coucher soleil
    private double lat;
    private double lon;
}