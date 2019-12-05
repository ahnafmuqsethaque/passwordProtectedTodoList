package network;

//import jdk.nashorn.internal.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

//import org.json.simple.JSONObject;
//import org.json.simple.JsonObject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ApiWeather {
    private String apiKey;
    private String currentWeatherDescription;

    public ApiWeather(String key) {
        this.apiKey = key;
    }


    //String apikey = "80315d4688bacb8d674b29147f4b1ef4"; //fill this in with the API key they email you

    //TODO: ask why error if we put the code below here instead of inside jsonStringBuilder?

//    String vancouverWeatherQuery = "https://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID=";
//    String theURL = vancouverWeatherQuery + getApiKey();




    //MODIFIES:this
    //EFFECTS: returns the json parsed string

    public String jsonParseInfo(String jsonString) throws ParseException {

        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
        JSONArray jsonArray = (JSONArray) jsonObject.get("weather");
        JSONObject jsonWeatherObject = (JSONObject) jsonArray.get(0);

        return jsonWeatherObject.get("description").toString();

    }

    //MODIFIES:this
    //EFFECTS: builds the json string and returns it

    public String jsonStringBuilder() throws IOException {

        BufferedReader br = null;

        try {
            String vancouverWeatherQuery = "https://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID=";
            String theURL = vancouverWeatherQuery + getApiKey();
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }

            return sb.toString();

        } finally {
            if (br != null) {
                br.close();
            }
        }
    }



    public void setCurrentWeatherDescription() {
        try {
            String jsonConvertedStringBuilder = jsonStringBuilder();
            currentWeatherDescription = jsonParseInfo(jsonConvertedStringBuilder);
        } catch (Exception e) {
            System.out.println("Could not retrieve weather data");
        }
    }

    public String getCurrentWeatherDescription() {
        return currentWeatherDescription;
    }

    public String getApiKey() {
        return apiKey;
    }




}
