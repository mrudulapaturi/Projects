package uncc.abilash.edu;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Himanshu on 10/7/2016.
 */
public class WeatherUtil {
    static JSONObject root;
    static Weather weather;
    static String temperature;
    // static JSONObject choicesJSONObject;
    static public class WeatherJSONParser {
        static ArrayList<Weather> parseWeather(String in) throws JSONException {
            ArrayList<Weather> weatherList = new ArrayList<Weather>();
            try {
                root = new JSONObject(in);
                JSONArray weatherJSONArray = root.getJSONArray("hourly_forecast");
            /*JSONArray weatherJSONArrayresponse = root.getJSONArray("response");
            JSONObject responseweatherJson = weatherJSONArrayresponse.getJSONObject(0);
            responseweatherJson.getString("error");
            */

                //JSONObject error = root.getJSONObject("error");

                for (int i = 0; i < weatherJSONArray.length(); i++) {
                    JSONObject weatherJSONArrayObject = weatherJSONArray.getJSONObject(i);
                    weather = new Weather();
                    // weather.setTemperature(weatherJSONArrayObject.getString("temp"));

                    JSONObject tempObject = weatherJSONArrayObject.getJSONObject("temp");
                    weather.setTemperature(tempObject.getString("english"));
                    //Log.d("demo",weather.getTemperature());

                    JSONObject gettimeObject = weatherJSONArrayObject.getJSONObject("FCTTIME");
                    weather.setTime(gettimeObject.getString("civil"));


                    JSONObject dewObject = weatherJSONArrayObject.getJSONObject("dewpoint");
                    weather.setDewpoint(dewObject.getString("english"));

                    weather.setClouds(weatherJSONArrayObject.getString("condition"));

                    weather.setIconUrl(weatherJSONArrayObject.getString("icon_url"));

                    JSONObject windspeedObject = weatherJSONArrayObject.getJSONObject("wspd");
                    weather.setWindSpeed(windspeedObject.getString("english"));



                    weather.setClimateType(weatherJSONArrayObject.getString("wx"));

                    weather.setHumidity(weatherJSONArrayObject.getString("humidity"));








                /*
                "hourly_forecast": [
		{
		"FCTTIME": {
		"hour": "20","hour_padded": "20","min": "00","min_unpadded": "0","sec": "0","year": "2016","mon": "10","mon_padded": "10","mon_abbrev": "Oct","mday": "7","mday_padded": "07","yday": "280","isdst": "1","epoch": "1475884800","pretty": "8:00 PM EDT on October 07, 2016","civil": "8:00 PM","month_name": "October","month_name_abbrev": "Oct","weekday_name": "Friday","weekday_name_night": "Friday Night","weekday_name_abbrev": "Fri","weekday_name_unlang": "Friday","weekday_name_night_unlang": "Friday Night","ampm": "PM","tz": "","age": "","UTCDATE": ""
		},
		"temp": {"english": "69", "metric": "21"},
		"dewpoint": {"english": "67", "metric": "19"},
		"condition": "Rain",
		"icon": "rain",
		"icon_url":"http://icons.wxug.com/i/c/k/nt_rain.gif",
		"fctcode": "13",
		"sky": "100",
		"wspd": {"english": "10", "metric": "16"},
		"wdir": {"dir": "NE", "degrees": "42"},
		"wx": "Rain",
		"uvi": "0",
		"humidity": "94",
		"windchill": {"english": "-9999", "metric": "-9999"},
		"heatindex": {"english": "-9999", "metric": "-9999"},
		"feelslike": {"english": "69", "metric": "21"},
		"qpf": {"english": "0.04", "metric": "1"},
		"snow": {"english": "0.0", "metric": "0"},
		"pop": "69",
		"mslp": {"english": "29.94", "metric": "1014"}
		}
		,
		{


                 */


                }
                return weatherList;
            }catch (Exception e){

            }
            return null;
        }
    }

}
