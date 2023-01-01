package exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.HttpClient;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.model.NameTemperature;
import exercise.model.WeatherCity;
import liquibase.pro.packaged.W;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public String getWeatherById(long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City not found"));
        String cityName = city.getName();
        String responce = client.get("http://weather/api/v2/cities/" + cityName);
        return responce;
    }

    public Map<String, String> lookUp(long id) {

        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City not found"));

        String cityName = city.getName();
        String url = "http://weather/api/v2/cities/" + cityName;

        ObjectMapper mapper = new ObjectMapper();

        String responce = client.get(url);

        Map<String, String> result;

        try {
            result = mapper.readValue(responce, Map.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

}
