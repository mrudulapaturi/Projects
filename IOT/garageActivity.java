package uncc.abilash.edu;

//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
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
import java.sql.SQLException;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;

public class garageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);

        final ImageButton lock = (ImageButton) findViewById(R.id.imageButton_lck);
        final ImageButton unlock = (ImageButton) findViewById(R.id.imageButton2_ulck);
        lock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // Toast.makeText(getBaseContext(), "Garage doors have been locked", Toast.LENGTH_SHORT).show();
                String status = "locked";
                String result=getConnection("locked");
                if(result.equals("true\n"))
                    Toast.makeText(getBaseContext(), "Garage doors have been locked", Toast.LENGTH_SHORT).show();
            }

        });
        unlock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Toast.makeText(getBaseContext(), "Garage doors have been locked", Toast.LENGTH_SHORT).show();
                String status = "unlocked";
                String result=getConnection("unlocked");
                if(result.equals("true\n"))
                    Toast.makeText(getBaseContext(), "Garage doors have been unlocked", Toast.LENGTH_SHORT).show();
            }

        });
    }
    public String getConnection(String status){
        InputStream inputStream = null;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>();
        nameValuePairs1.add(new BasicNameValuePair("status",status));


        //http postappSpinners
        try{
            HttpClient httpclient = new DefaultHttpClient();

            // have to change the ip here to correct ip
            HttpPost httppost = new HttpPost("http://192.168.1.14/garage.php");
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
