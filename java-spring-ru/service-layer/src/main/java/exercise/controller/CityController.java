package exercise.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.model.City;
import exercise.model.NameTemperature;
import exercise.model.WeatherCity;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import liquibase.pro.packaged.C;
import liquibase.pro.packaged.N;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("")
@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}")
    public WeatherCity get(@PathVariable long id) throws JsonProcessingException {
        String responce =  weatherService.getWeatherById(id);
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherCity weatherCity = objectMapper.readValue(responce, WeatherCity.class);
        return weatherCity;
    }

    @GetMapping(path = "/search")
    public List<Map<String, String>> getCities(@RequestParam(required = false) String name) {

        List<City> filteredCities;

        if (name == null) {
            filteredCities = cityRepository.findAllByOrderByName();
        } else {
            filteredCities = cityRepository.findByNameStartingWithIgnoreCase(name);
        }

        List<Map<String, String>> citiesWithWeather = filteredCities.stream()
                .map(city -> {
                    Map<String, String> weather = weatherService.lookUp(city.getId());
                    return Map.of(
                            "name", city.getName(),
                            "temperature", weather.get("temperature")
                    );
                })
                .collect(Collectors.toList());

        return citiesWithWeather;
    }
    // END
}

