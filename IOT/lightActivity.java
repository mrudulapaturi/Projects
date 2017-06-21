package uncc.abilash.edu;

import android.app.Activity;
//import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class lightActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);



        final Button lightmf = (Button) findViewById(R.id.button_lgmf);
        final Button lightup = (Button) findViewById(R.id.button_lgUp);
        lightmf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(
                        lightActivity.this,
                        lightmainActivity.class);
                startActivity(i);
            }

        });
        lightup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(
                        lightActivity.this,
                        lightupActivity.class);
                startActivity(i);
            }

        });
    }
}

