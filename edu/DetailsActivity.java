package uncc.abilash.edu;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetailsActivity extends Activity {
    Weather weather;
    TextView textView_mintemp,textView_maxtemp,textView_location,textView_humidity,textView_dewpoint,textView_cloud,textView_pressure,textView_temp;
    ImageView img_condition;
    TextView textView_feelslike,textView_winds;
    String city, state;
    String max_temp, min_temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        img_condition = (ImageView) findViewById(R.id.imageView_icondetail);
        if(getIntent().getExtras()!=null) {
            weather = (Weather) getIntent().getExtras().get("MainActivity");


            Picasso.with(getApplicationContext()).load(weather.getIconUrl()).into(img_condition);
            textView_location = (TextView) findViewById(R.id.textView_locationValue);
            textView_location.setText (city + "," + state+"("+weather.getTime()+")");

            textView_temp = (TextView)findViewById(R.id.textView_temp);
            textView_temp.setText(weather.getTemperature()+(char) 0x00B0+"F"+"\n"+weather.getClimateType());


            textView_mintemp = (TextView)findViewById(R.id.textView_min);
            textView_mintemp.setText("      "+min_temp+(char) 0x00B0+"F");

            textView_maxtemp =(TextView)findViewById(R.id.textView_max);
            textView_maxtemp.setText("      "+max_temp+(char) 0x00B0+"F");

            textView_cloud = (TextView)findViewById(R.id.textView_cloudValue);
            textView_cloud.setText(weather.getClouds());

            textView_dewpoint = (TextView)findViewById(R.id.textView_dewpointValue);
            textView_dewpoint.setText(weather.getDewpoint()+(char) 0x00B0+"F");

            textView_humidity=(TextView)findViewById(R.id.textView_humidityValue);
            textView_humidity.setText(weather.getHumidity()+"%");



            textView_winds =(TextView)findViewById(R.id.textView_windValue);
            textView_winds.setText(weather.getWindSpeed()+" mph, "+weather.getWinddirectvalue()+(char) 0x00B0+" ");

            //textView_winds.setText(weather.getWindDirection()+" "+weather.getWindSpeed());







        }
    }
}
