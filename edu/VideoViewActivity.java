package uncc.abilash.edu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewActivity extends Activity {
    ProgressDialog pDialog;
    VideoView videoview;
    //String VideoURL="https://www.androidbegin.com/tutorial/AndroidCommercial.3gp" +"";
    String VideoURL="http://192.168.1.13:8080/" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        videoview=(VideoView)findViewById(R.id.VideoView);
        pDialog = new ProgressDialog(VideoViewActivity.this);
        pDialog.setTitle("Webcam feed");
        pDialog.setMessage("Buffering....");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        try{
            MediaController mediaController = new MediaController(VideoViewActivity.this);
            mediaController.setAnchorView(videoview);
            Uri video= Uri.parse(VideoURL);
            videoview.setMediaController(mediaController);
            videoview.setVideoURI(video);

        }catch (Exception e){
            Log.e("Error",e.getMessage());
            e.printStackTrace();
        }
        videoview.requestFocus();
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoview.start();
            }
        });
    }
}
