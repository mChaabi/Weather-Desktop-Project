package com.Meteo.controller;

import com.Meteo.model.WeatherResponse;
import com.Meteo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{city}")
    public WeatherResponse getWeather(@PathVariable String city) {
        return weatherService.getWeather(city);
    }

    // Toutes les villes + villages d'Europe et Maroc
    @GetMapping("/all")
    public List<WeatherResponse> getAllCities() {
        List<String> places = List.of(
                // Maroc
                "Casablanca", "Rabat", "Fes", "Marrakech", "Tangier",
                "Tetouan", "Agadir", "Oujda", "Meknes", "Kenitra",
                "Safi", "El Jadida", "Beni Mellal", "Nador", "Ifrane",
                // France — villes + villages
                "Paris", "Lyon", "Marseille", "Bordeaux", "Toulouse",
                "Nice", "Strasbourg", "Nantes", "Rennes", "Grenoble",
                "Chamonix", "Annecy", "Colmar", "Carcassonne", "Avignon",
                // Espagne
                "Madrid", "Barcelona", "Seville", "Valencia", "Bilbao",
                "Granada", "Toledo", "Segovia", "Cordoba", "Salamanca",
                // Portugal
                "Lisbon", "Porto", "Sintra", "Evora", "Braga",
                // Italie
                "Rome", "Milan", "Venice", "Florence", "Naples",
                "Siena", "Pisa", "Amalfi", "Cinque Terre", "Bari",
                // Allemagne
                "Berlin", "Munich", "Hamburg", "Frankfurt", "Cologne",
                "Rothenburg", "Heidelberg", "Dresden", "Freiburg",
                // Royaume-Uni
                "London", "Edinburgh", "Manchester", "Bristol", "Bath",
                "Oxford", "Cambridge", "York", "Brighton",
                // Pays-Bas + Belgique
                "Amsterdam", "Rotterdam", "Utrecht", "Brussels", "Bruges",
                "Ghent", "Antwerp",
                // Suisse + Autriche
                "Zurich", "Geneva", "Bern", "Lausanne", "Vienna",
                "Salzburg", "Innsbruck",
                // Europe de l'Est
                "Prague", "Budapest", "Warsaw", "Krakow", "Bratislava",
                "Ljubljana", "Zagreb", "Dubrovnik", "Sarajevo",
                // Scandinavie
                "Stockholm", "Oslo", "Copenhagen", "Helsinki", "Bergen",
                // Grèce + Turquie
                "Athens", "Thessaloniki", "Santorini", "Istanbul", "Ankara"
        );

        return places.parallelStream()   // parallelStream = plus rapide
                .map(city -> {
                    try { return weatherService.getWeather(city); }
                    catch (Exception e) { return null; }
                })
                .filter(w -> w != null)
                .toList();
    }

    // Endpoint par région
    @GetMapping("/region/{region}")
    public List<WeatherResponse> getByRegion(@PathVariable String region) {
        List<String> places = switch (region.toLowerCase()) {
            case "maroc"  -> List.of("Casablanca","Rabat","Fes","Marrakech","Tangier","Tetouan","Agadir","Oujda","Meknes","Ifrane");
            case "france" -> List.of("Paris","Lyon","Marseille","Bordeaux","Nice","Chamonix","Annecy","Colmar","Avignon");
            case "espagne"-> List.of("Madrid","Barcelona","Seville","Valencia","Granada","Toledo","Segovia");
            case "italie" -> List.of("Rome","Milan","Venice","Florence","Naples","Siena","Amalfi");
            case "uk"     -> List.of("London","Edinburgh","Manchester","Bristol","Oxford","Bath","York");
            default       -> List.of("Paris","London","Berlin","Madrid","Rome");
        };
        return places.parallelStream()
                .map(city -> { try { return weatherService.getWeather(city); } catch(Exception e){ return null; }})
                .filter(w -> w != null).toList();
    }
}