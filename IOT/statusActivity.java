package uncc.abilash.edu;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class statusActivity extends Activity {

TextView gd;
    TextView fd;
    TextView bd;
    TextView ss;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d("status act", "in status");
        setContentView(R.layout.activity_status);
         gd= (TextView)findViewById(R.id.textViewgd);
         fd= (TextView)findViewById(R.id.textViewfd);
         bd= (TextView)findViewById(R.id.textViewbd);
         ss= (TextView)findViewById(R.id.textViewss);
        try{
           // HttpClient httpclient = new DefaultHttpClient();

            // have to change the ip here to correct ip
           //1 HttpGet httpget= new HttpGet("http://192.168.1.3//status.php");
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://192.168.1.3//status.php")
                    .build();
           // HttpResponse response = httpclient.execute(httpget);
            //HttpEntity entity = response.getEntity();


            Response response = client.newCall(request).execute();
            ResponseBody resb=response.body();

            JSONArray r=new JSONArray(resb.string());
            gd.setText(r.getJSONObject(r.length()-1).getString("garage_doors"));
            fd.setText(r.getJSONObject(r.length()-1).getString("frontdoor"));
            bd.setText(r.getJSONObject(r.length()-1).getString("backdoor"));
            ss.setText(r.getJSONObject(r.length()-1).getString("security_sys"));



        }
        catch(Exception e){
            Log.e("log_tag", "Error in http connection "+e.toString());
            Toast.makeText(getBaseContext(), "Server Not Responding", Toast.LENGTH_SHORT).show();


        }
    }
}
