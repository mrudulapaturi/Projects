package uncc.abilash.edu;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Himanshu on 10/7/2016.
 */
public class GetWeatherAsyncTask extends AsyncTask<String, Void, ArrayList<Weather>> {
    IWeatherData weatherData;

    public GetWeatherAsyncTask(IWeatherData weatherData) {
        this.weatherData = weatherData;
    }
    @Override

    protected ArrayList<Weather> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            Log.d("demo",url.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int statusCode = connection.getResponseCode();
            if(statusCode==HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();
                while (line != null) {
                    sb.append(line);
                    line = reader.readLine();
                }

                return WeatherUtil.WeatherJSONParser.parseWeather(sb.toString());
            }}
        catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Weather> weathers) {
        //Log.d("demo","fels like"+weathers.get(36).getFeelsLike());
        weatherData.setUpData(weathers);
    }
    public static interface IWeatherData
    {
        public void setUpData(ArrayList<Weather> weatherItems);
    }


}

