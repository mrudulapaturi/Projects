package uncc.abilash.edu;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
/**
 * Created by mrudula on 27/10/2016.
 */

public class Weather implements  Parcelable{



        String time, temperature,
                dewpoint, clouds, iconUrl, windSpeed, windDirection, climateType, humidity,
                feelsLike, pressure,winddirectvalue,month,year,day;

        public Weather()
        {

        }

        protected Weather(Parcel in) {
            time = in.readString();
            temperature = in.readString();
            dewpoint = in.readString();
            clouds = in.readString();
            iconUrl = in.readString();
            windSpeed = in.readString();
            climateType = in.readString();
            humidity = in.readString();

        }

        public static final Creator<Weather> CREATOR = new Creator<Weather>() {
            @Override
            public Weather createFromParcel(Parcel in) {
                return new Weather(in);
            }

            @Override
            public Weather[] newArray(int size) {
                return new Weather[size];
            }
        };



        public String getWinddirectvalue() {

            return winddirectvalue;
        }

        public void setWinddirectvalue(String winddirectvalue) {
            this.winddirectvalue = winddirectvalue;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getDewpoint() {
            return dewpoint;
        }

        public void setDewpoint(String dewpoint) {
            this.dewpoint = dewpoint;
        }

        public String getClouds() {
            return clouds;
        }

        public void setClouds(String clouds) {
            this.clouds = clouds;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(String windSpeed) {
            this.windSpeed = windSpeed;
        }



        public String getClimateType() {
            return climateType;
        }

        public void setClimateType(String climateType) {
            this.climateType = climateType;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }


        @Override
        public String toString() {
            return "Weather{" +
                    "time='" + time + '\'' +
                    ", temperature='" + temperature + '\'' +
                    ", dewpoint='" + dewpoint + '\'' +
                    ", clouds='" + clouds + '\'' +
                    ", iconUrl='" + iconUrl + '\'' +
                    ", windSpeed='" + windSpeed + '\'' +
                    ", climateType='" + climateType + '\'' +
                    ", humidity='" + humidity + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(time);
            dest.writeString(temperature);
            dest.writeString(dewpoint);
            dest.writeString(clouds);
            dest.writeString(iconUrl);
            dest.writeString(windSpeed);
            dest.writeString(climateType);
            dest.writeString(humidity);

        }
    }




