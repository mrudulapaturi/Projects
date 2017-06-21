package uncc.abilash.edu;

import android.app.Activity;
//import android.support.v7.app.ActionBarActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class HomeActivity extends Activity {
    static String userString;
    private String pack_name =  "com.example.himanshu.weatherforecast";
    private String class_name = "com.example.himanshu.weatherforecast.MainActivity";
     Button status;
    private WebView webcamView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final Button security_sys = (Button) findViewById(R.id.button_ss);
        final Button garage_doors = (Button) findViewById(R.id.button_gd);
        final Button lights = (Button) findViewById(R.id.button_lit);
        final Button locks = (Button) findViewById(R.id.button_lck);
        final Button thermostat = (Button) findViewById(R.id.button_ts);
        final Button sensors = (Button) findViewById(R.id.button_sen);

        final Button motion_detector = (Button) findViewById(R.id.button_md);
        final Button webcam = (Button) findViewById(R.id.button_webcam);
        final Button weather = (Button) findViewById(R.id.button_weather);
       status = (Button) findViewById(R.id.button_stat);
     /*   webcamView = (WebView) findViewById(R.id.webcamview);
        webcamView.getSettings().setJavaScriptEnabled(true);
        webcamView.setWebViewClient(new HelloWebViewClient());*/
        final WebView webview = new WebView(this);

        status.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("but", "click");
                Intent statusActivity = new Intent(getBaseContext(), statusActivity.class);

                startActivity(statusActivity);
            }

        });
        webcam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                webview
                        .getSettings().setJavaScriptEnabled(true);
                setContentView(webview);
                webview.loadUrl("http://192.168.1.5:8080/");

              //  webcamView.loadUrl("http://192.168.1.13:8080/");

      /*          Uri uri = Uri.parse("http://192.168.1.13:8080/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
*//*
               *//* Intent VideoViewActivity = new Intent(getBaseContext(), VideoViewActivity.class);

                startActivity(VideoViewActivity);*/
            }

        });
        weather.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchCall();
                //Intent MainActivity = new Intent(getBaseContext(), MainActivity.class);

                //startActivity(MainActivity);
            }

        });


        security_sys.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("username", userString);
                Intent securityActivity = new Intent(getBaseContext(), securityActivity.class);
                securityActivity.putExtras(bundle);
                startActivity(securityActivity);
            }

        });
        garage_doors.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("username", userString);
                Intent garageActivity = new Intent(getBaseContext(), garageActivity.class);
                garageActivity.putExtras(bundle);
                startActivity(garageActivity);
            }

        });
        thermostat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("username", userString);
                Intent  Thermostat = new Intent(getBaseContext(), Thermostat.class);
                Thermostat.putExtras(bundle);
                startActivity(Thermostat);
            }

        });

        lights.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("username", userString);
                Intent lightActivity = new Intent(getBaseContext(), lightActivity.class);
                lightActivity.putExtras(bundle);
                startActivity(lightActivity);
            }

        });

        locks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("username", userString);
                Intent locksActivity = new Intent(getBaseContext(), locksActivity.class);
                locksActivity.putExtras(bundle);
                startActivity(locksActivity);
            }

        });

        sensors.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("username", userString);
                Intent sensorActivity = new Intent(getBaseContext(), sensorActivity.class);
                sensorActivity.putExtras(bundle);
                startActivity(sensorActivity);
            }

        });
        motion_detector.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("username", userString);
                Intent motionActivity = new Intent(getBaseContext(), motionActivity.class);
                motionActivity.putExtras(bundle);
                startActivity(motionActivity);
            }

        });


    }

    private void launchCall() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(intent.CATEGORY_LAUNCHER);
        intent.setComponent(new ComponentName(pack_name,class_name));
        try{
            startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

/*
    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }*/
}

