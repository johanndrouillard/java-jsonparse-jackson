import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Parse {

    private final static String JSON_WEATHER_PATH = "weather.json";

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = ObjectMapper.readTree(new File("weather.json"));

            String cityName = root.get("name").asText();

            JsonNode coord = root.get("coord");
            Double cityLatitude = coord.get("lat").asDouble();
            Double cityLongitude = coord.get("lon").asDouble();

            Wind wind = objectMapper.convertValue(root.get("wind"), Wind.class);

            Weather[] weathers = objectMapper.convertValue(root.get("weather"), Weather[].class);

            System.out.printf("City name: %s%n", cityName);
            System.out.printf("City latitude: %s%n", cityLatitude);
            System.out.printf("City longitude: %s%n", cityLongitude);
            System.out.printf("Wind infos: %s%n", wind.toString());
            for (Weather weather : weathers) {
                System.out.printf("Weather infos: %s%n", weather.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
