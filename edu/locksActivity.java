package uncc.abilash.edu;

//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
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

public class locksActivity extends Activity {
 ArrayList<String> selections = new ArrayList<String>();
    ArrayList<String> selections1 = new ArrayList<String>();
 String status1=" ";
    String status2=" ";
    String status3=" ";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locks);
        final CheckBox garagedoor = (CheckBox)findViewById(R.id.checkBox_gd);
        final CheckBox frontdoor = (CheckBox)findViewById(R.id.checkBox_fd);
        final CheckBox backdoor = (CheckBox)findViewById(R.id.checkBox_bd);
        final ImageButton lock = (ImageButton)findViewById(R.id.imageButton_lock);
        final ImageButton unlock = (ImageButton)findViewById(R.id.imageButton_unlock);
        lock.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                if(garagedoor.isChecked())
                {   status1 = "locked";
                    selections.add("Garage doors");
                   //String result=getConnection("locked");

                }else
                    status1= " ";
                if(frontdoor.isChecked())
                {  status2="locked";
                    selections.add("Front Doors");
                    //String result=getConnection("locked");

                } else
                   status2=" ";
                if(backdoor.isChecked())
                { status3 = "locked";
                    selections.add("Back doors");
                    //String result=getConnection("locked");

                }else
                  status3 = " ";
                String allItems = " ";
                String result=getConnection(status1,status2,status3);
                for(String str: selections){
                     allItems = allItems + "\n" + str + "  " + "are locked";

                }
                Toast.makeText(getApplicationContext(),allItems,Toast.LENGTH_LONG).show();

            }

        });
        unlock.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                if(garagedoor.isChecked())
                {
                    selections1.add("Garage doors");
                    status1 = "unlocked";
                    //String result=getConnection("locked");

                }else
                    status1= " ";
                if(frontdoor.isChecked())
                {  selections1.add("Front doors");
                    status2="unlocked";
                    //String result=getConnection("locked");

                } else
                    status2=" ";
                if(backdoor.isChecked())
                {
                    selections1.add("Back doors");
                    status3 = "unlocked";
                    //String result=getConnection("locked");

                }else
                    status3 = " ";
                String allItems = " ";
                String result=getConnection(status1,status2,status3);
                for(String str: selections1){
                    allItems = allItems + "\n" + str + "  " + "are unlocked";

                }
                Toast.makeText(getApplicationContext(),allItems,Toast.LENGTH_LONG).show();

            }

        });

    }
    public String getConnection(String status1,String status2,String status3){
        InputStream inputStream = null;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>();
        nameValuePairs1.add(new BasicNameValuePair("status1",status1));
        nameValuePairs1.add(new BasicNameValuePair("status2",status2));
        nameValuePairs1.add(new BasicNameValuePair("status3",status3));


        //http postappSpinners
        try{
            HttpClient httpclient = new DefaultHttpClient();

            // have to change the ip here to correct ip
            HttpPost httppost = new HttpPost("http://192.168.1.14/locks.php");
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
