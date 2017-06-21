package uncc.abilash.edu;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Thermostat extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermostat);

        final Button thermomain = (Button) findViewById(R.id.button_mf);
        final Button thermup = (Button) findViewById(R.id.button_up);

        thermomain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent thermostatmainActivity = new Intent(getBaseContext(),thermostatmainActivity.class);

                startActivity(thermostatmainActivity);
            }

        });

        thermup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(
                        Thermostat.this,
                        thermostatupActivity.class);
                startActivity(i);
            }

        });

    }
}
