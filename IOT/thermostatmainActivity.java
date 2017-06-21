package uncc.abilash.edu;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class thermostatmainActivity extends Activity {
    private Handler handler = new Handler();
    String status1=" ";
    String status2=" ";
    int progress = 0;
    int control_temp=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermostatmain);
        final Button heat = (Button) findViewById(R.id.button_hm);
        final Button cool = (Button) findViewById(R.id.button_cm);
        final Button auto = (Button) findViewById(R.id.button_am);
        final Button on = (Button) findViewById(R.id.btn_on);
        final Button off = (Button) findViewById(R.id.btn_off);
        final Button enter = (Button) findViewById(R.id.button_entr);
        final ProgressBar temp = (ProgressBar) findViewById(R.id.progressBar);
        final TextView current_temp = (TextView) findViewById(R.id.Cur_temp);
        final EditText des_temp = (EditText) findViewById(R.id.Des_temp);
        current_temp.setText("50");


        //control_temp = Integer.parseInt(des_temp.getText().toString());

        heat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                status1 = "heat";
                String result = getConnection(status1, status2);
                if (result.equals("true\n")) {
                    Toast.makeText(getBaseContext(), "Heat mode ENABLED", Toast.LENGTH_SHORT).show();
                }

            }

        });
        cool.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                status1 = "cool";
                String result = getConnection(status1, status2);
                if (result.equals("true\n"))
                    Toast.makeText(getBaseContext(), "Cold mode ENABLED", Toast.LENGTH_LONG).show();
            }

        });
        auto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                status1 = "auto";
                String result = getConnection(status1, status2);
                if (result.equals("true\n"))
                    Toast.makeText(getBaseContext(), "Auto mode ENABLED", Toast.LENGTH_LONG).show();
            }

        });
        on.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                status2 = "on";
                String result = getConnection(status1, status2);
                if (result.equals("true\n"))
                    Toast.makeText(getBaseContext(), "FAN turned ON", Toast.LENGTH_LONG).show();
            }

        });
        off.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                status2 = "off";
                String result = getConnection(status1, status2);
                if (result.equals("true\n"))
                    Toast.makeText(getBaseContext(), "FAN turned OFF", Toast.LENGTH_LONG).show();
            }

        });
        enter.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                final int ctrl_temp = Integer.parseInt(des_temp.getText().toString());
                progress=50;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(progress < ctrl_temp){
                            progress+=1;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    temp.setProgress(progress);
                                    current_temp.setText("Current temp:" + progress);
                                }
                            });

                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        while(progress > ctrl_temp){
                            progress-=1;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    temp.setProgress(progress);
                                    current_temp.setText("Current temp:" + progress);
                                }
                            });

                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                }).start();
            }
        });





    }

    private String getConnection(String status1, String status2) {
        InputStream inputStream = null;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>();
        nameValuePairs1.add(new BasicNameValuePair("status1",status1));
        nameValuePairs1.add(new BasicNameValuePair("status2",status2));


        //http postappSpinners
        try{
            HttpClient httpclient = new DefaultHttpClient();

            // have to change the ip here to correct ip
            HttpPost httppost = new HttpPost("http://192.168.1.3/thermo_main.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs1));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
        }
        catch(Exception e){
            Log.e("log_tag", "Error in http connection "+e.toString());
            Toast.makeText(getBaseContext(), "Server Not Responding", Toast.LENGTH_SHORT).show();


        }
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            inputStream.close();
            result=sb.toString();
        }
        catch(Exception e){
            Log.e("log_tag", "Error converting result "+e.toString());
        }
        return result;



    }


}
