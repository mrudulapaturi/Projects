package uncc.abilash.edu;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
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

public class securityActivity extends Activity {
String status1="  ";
    String status2=" ";
    String status3=" ";
    String mode = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        final ImageButton armstay=(ImageButton)findViewById(R.id.imageButton_arm);
        final ImageButton armaway=(ImageButton)findViewById(R.id.imageButton_armaway);
        final ImageButton disarm=(ImageButton)findViewById(R.id.imageButton_disarm);
        armstay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Toast.makeText(getBaseContext(), "Garage doors have been locked", Toast.LENGTH_SHORT).show();
                status1="on";
                status2="off";
                mode = "Armed Stay";


                String result=getConnection1(status1,status2,mode);

                if(result.equals("true\n"))
                    Toast.makeText(getBaseContext(), "ARMED STAY ENABLED", Toast.LENGTH_SHORT).show();
            }

        });
        armaway.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Toast.makeText(getBaseContext(), "Garage doors have been locked", Toast.LENGTH_SHORT).show();
                status1="off";
                status2="on";
                status3="locked";
                mode = "Armed Away";


                String result1=getConnection2(status1,status2,status3,mode);

                if(result1.equals("true\n"))
                    Toast.makeText(getBaseContext(), "ARMED AWAY ENABLED", Toast.LENGTH_SHORT).show();
            }

        });
        disarm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Toast.makeText(getBaseContext(), "Garage doors have been locked", Toast.LENGTH_SHORT).show();
                status1="off";
                status2="off";
                status3="unlocked";
                mode = "Disarmed";


                String result2=getConnection3(status1,status2,status3,mode);

                if(result2.equals("true\n"))
                    Toast.makeText(getBaseContext(), "DISARMED ENABLED", Toast.LENGTH_SHORT).show();
            }

        });
    }
    public String getConnection1(String status1, String status2, String mode){
        InputStream inputStream = null;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>();
        nameValuePairs1.add(new BasicNameValuePair("status1",status1));
        nameValuePairs1.add(new BasicNameValuePair("status2",status2));
        nameValuePairs1.add(new BasicNameValuePair("mode",mode));



        //http postappSpinners
        try{
            HttpClient httpclient = new DefaultHttpClient();

            // have to change the ip here to correct ip
            HttpPost httppost = new HttpPost("http://192.168.1.3/security.php");
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
    public String getConnection2(String status1, String status2, String status3, String mode){
        InputStream inputStream = null;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>();
        nameValuePairs1.add(new BasicNameValuePair("status1",status1));
        nameValuePairs1.add(new BasicNameValuePair("status2",status2));
        nameValuePairs1.add(new BasicNameValuePair("status3",status3));
        nameValuePairs1.add(new BasicNameValuePair("mode",mode));



        //http postappSpinners
        try{
            HttpClient httpclient = new DefaultHttpClient();

            // have to change the ip here to correct ip
            HttpPost httppost = new HttpPost("http://192.168.1.3/security.php");
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

    public String getConnection3
            (String status1, String status2, String status3, String mode){
        InputStream inputStream = null;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>();
        nameValuePairs1.add(new BasicNameValuePair("status1",status1));
        nameValuePairs1.add(new BasicNameValuePair("status2",status2));
        nameValuePairs1.add(new BasicNameValuePair("status3",status3));
        nameValuePairs1.add(new BasicNameValuePair("mode",mode));



        //http postappSpinners
        try{
            HttpClient httpclient = new DefaultHttpClient();

            // have to change the ip here to correct ip
            HttpPost httppost = new HttpPost("http://192.168.1.3/security.php");
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
