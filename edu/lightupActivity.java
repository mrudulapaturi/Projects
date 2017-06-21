package uncc.abilash.edu;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
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

public class lightupActivity extends Activity {
    int progress_value;
    String status1=" " ;
    String status2=" " ;
    String status3=" " ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lightup);
        final Switch light1_up = (Switch) findViewById(R.id.switch_lit1up);
        final Switch light2_up = (Switch) findViewById(R.id.switch_lit2up);
        final Switch light3_up = (Switch) findViewById(R.id.switch_lit3up);
        final SeekBar lit1_barup = (SeekBar)findViewById(R.id.seekBar_lit1up);
        final Button done = (Button) findViewById(R.id.button_up);
        final TextView lit1up = (TextView) findViewById(R.id.textView_lit1up);
        final TextView lit2up = (TextView) findViewById(R.id.textView_lit2up);
        final TextView lit3up = (TextView) findViewById(R.id.textView_lit3up);
        final SeekBar lit2_barup = (SeekBar)findViewById(R.id.seekBar_lit2up);
        final SeekBar lit3_barup = (SeekBar)findViewById(R.id.seekBar_lit3up);
        lit1_barup.setEnabled(false);
        lit2_barup.setEnabled(false);
        lit3_barup.setEnabled(false);
        lit1up.setEnabled(false);
        lit2up.setEnabled(false);
        lit3up.setEnabled(false);
        light1_up.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    lit1_barup.setEnabled(true);
                    lit1_barup.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            progress_value = progress;
                            lit1up.setText("Brightness:" + "  " +  progress + "/" + lit1_barup.getMax());
                            //status2 = "on" + "  " + progress;
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                            lit1up.setText("Brightness:" + "  " +  progress_value+ "/" + lit1_barup.getMax());

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            lit1up.setText("Brightness:" + "  " +  progress_value + "/" + lit1_barup.getMax());
                            status1 = "on" + "  " + progress_value;
                        }
                    });
                    lit1up.setEnabled(true);


                }
                else
                    status1 = "off";

            }
        });
        light2_up.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    lit2_barup.setEnabled(true);
                    lit2_barup.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            progress_value = progress;
                            lit2up.setText("Brightness:" + "  " +  progress + "/" + lit2_barup.getMax());
                            //status2 = "on" + "  " + progress;
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                            lit2up.setText("Brightness:" + "  " +  progress_value+ "/" + lit2_barup.getMax());

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            lit2up.setText("Brightness:" + "  " +  progress_value + "/" + lit2_barup.getMax());
                            status2 = "on" + "  " + progress_value;
                        }
                    });
                    lit2up.setEnabled(true);


                }
                else
                    status2 = "off";

            }
        });
        light3_up.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    lit3_barup.setEnabled(true);
                    lit3up.setEnabled(true);
                    lit3_barup.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            progress_value = progress;
                            lit3up.setText("Brightness:" + "  " +  progress + "/" + lit3_barup.getMax());
                            //status2 = "on" + "  " + progress;
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                            lit3up.setText("Brightness:" + "  " +  progress_value+ "/" + lit3_barup.getMax());

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            lit3up.setText("Brightness:" + "  " +  progress_value + "/" + lit3_barup.getMax());
                            status3 = "on" + "  " + progress_value;
                        }
                    });
                }
                else
                    status3 = "off";

            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Toast.makeText(getBaseContext(), "Garage doors have been locked", Toast.LENGTH_SHORT).show();
                //String status = "locked";
                String result=getConnection(status1,status2,status3);

                if(result.equals("true\n"))
                    Toast.makeText(getBaseContext(), "Lights have been turned on/off", Toast.LENGTH_SHORT).show();
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
            HttpPost httppost = new HttpPost("http://192.168.1.3/lights_up.php");
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

