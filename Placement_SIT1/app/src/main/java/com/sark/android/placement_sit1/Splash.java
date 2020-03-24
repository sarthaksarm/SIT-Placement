package com.sark.android.placement_sit1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
private TextView tv;
private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
      //  tv=(TextView)findViewById(R.id.txtsplash);
        iv=(ImageView)findViewById(R.id.imgsplash);


        Animation myanim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
     // tv.startAnimation(myanim);
      iv.startAnimation(myanim);

        Thread timer = new Thread(){
            public void run()
            {

               // overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                try{
                    Intent i= new Intent(Splash.this,MainActivity.class);
                    sleep(4000);
                    startActivity(i);

                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }
}