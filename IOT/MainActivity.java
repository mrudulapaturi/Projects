package uncc.abilash.edu;

//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;




        import android.app.ProgressDialog;
        import android.content.Intent;
        //import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Comparator;

public class MainActivity extends Activity implements GetWeatherAsyncTask.IWeatherData{
    IWeatherArray iweatherarray;

    EditText editText_city, editText_state;
    ProgressDialog progressDialog;
    Button button_submit;
    URL url;
    String weatherURL;
    public static final String WEATHER_LIST="weather_list";
    public static final String DELETED_FAVORITES_LIST="favorites_list";
    public static final String STATE_INFO="state_info";
    public static final String CITY_INFO="city_info";
    ArrayList<Weather> OriginalWeatherList;
   // ArrayList<Favorites> favoritesArrayList;
    String state, city_temp,city;
    String favorite_city, favorite_state,favorite_city_temp;
    //HourlyActivity ha = new HourlyActivity();
    public MainActivity() {
    }

    public MainActivity(IWeatherArray iweatherarray) {
        this.iweatherarray = iweatherarray;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                    weatherURL = "http://api.wunderground.com/api/014b1e771d5db37d/hourly/q/" + favorite_state + "/" + favorite_city_temp + ".json";
                    Log.d("demo", "This is my URL" + weatherURL);
                    new GetWeatherAsyncTask(MainActivity.this).execute(weatherURL);
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setMessage("Loading News");
                    progressDialog.show();




        button_submit = (Button)findViewById(R.id.button_submit);



        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_city = (EditText)findViewById(R.id.editText_city);
                editText_state=(EditText)findViewById(R.id.editText_state);
                state = editText_state.getText().toString();
                city = editText_city.getText().toString();
                city_temp = editText_city.getText().toString().replaceAll(" ","_");
                Intent i = new Intent(MainActivity.this,DetailsActivity.class);
                i.putParcelableArrayListExtra("MainActivity",OriginalWeatherList);
                startActivity(i);
                if(state.equals("") || city.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter valid state and city information",Toast.LENGTH_LONG).show();
                }
                else {

                    weatherURL = "http://api.wunderground.com/api/014b1e771d5db37d/hourly/q/" + state + "/" + city_temp + ".json";
                    Log.d("demo", "This is my URL" + weatherURL);
                    new GetWeatherAsyncTask(MainActivity.this).execute(weatherURL);
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setMessage("Loading News");
                    progressDialog.show();

                }
                //http://api.wunderground.com/api_key/hourly/q/state_initial/city_name.json
            }
        });



    }




    @Override
    public void setUpData(ArrayList<Weather> weatherItems) {
        progressDialog.dismiss();
        OriginalWeatherList = weatherItems;


    }

    public static interface IWeatherArray
    {
       // public void setUpDataArray(ArrayList<Favorites> FavouriteItems);
    }
}

