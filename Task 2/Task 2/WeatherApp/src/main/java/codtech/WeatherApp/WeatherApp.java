package codtech.WeatherApp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class WeatherApp {

    public static void main(String[] args) {

        try {
            String city = "Pune";
            String apiKey = "e4a3e92a1baa6ef4946afe09a1fe5761";

            String urlString = "https://api.openweathermap.org/data/2.5/weather?q="
                    + city + "&appid=" + apiKey + "&units=metric";

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            // Parse JSON
            JSONObject jsonObject = new JSONObject(response.toString());

            String cityName = jsonObject.getString("name");
            JSONObject main = jsonObject.getJSONObject("main");
            double temperature = main.getDouble("temp");
            double humidity = main.getDouble("humidity");

            String weatherDescription = jsonObject
                    .getJSONArray("weather")
                    .getJSONObject(0)
                    .getString("description");

            // Display Structured Output
            System.out.println("===== Weather Report =====");
            System.out.println("City: " + cityName);
            System.out.println("Temperature: " + temperature + " °C");
            System.out.println("Humidity: " + humidity + " %");
            System.out.println("Weather: " + weatherDescription);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
