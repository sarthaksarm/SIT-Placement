package com.sark.android.placement_sit1;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Process;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.flaviofaria.kenburnsview.Transition;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;


public class MainActivity extends AppCompatActivity {
Button adminlogin,user123bt;
RadioButton rb1,rb2,rb3,rbselected;
RadioGroup rgrp;
KenBurnsView kenBurnsView;
private boolean moving=true;
    private FirebaseAuth mAuth;
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kenBurnsView=(KenBurnsView)findViewById(R.id.image);
        AccelerateDecelerateInterpolator ACCELERATE_DECELERATE = new AccelerateDecelerateInterpolator();
        RandomTransitionGenerator generator = new RandomTransitionGenerator(10000, ACCELERATE_DECELERATE);
        kenBurnsView.setTransitionGenerator(generator);

        kenBurnsView.setTransitionListener(onTransittionListener());

        kenBurnsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moving){
                    kenBurnsView.pause();
                    moving=false;
                }
                else{
                    kenBurnsView.resume();;
                    moving=true;
                }
            }
        });

        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
        ref=firebaseDatabase.getReference("Userlogin");
        rgrp=findViewById(R.id.radiogrp);

        rb1=findViewById(R.id.rbbtnhome1);
        rb2=findViewById(R.id.rbbtnhome2);
        rb3=findViewById(R.id.rbbtnhome3);
        user123bt=(Button)findViewById(R.id.user123btn);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }
        FirebaseMessaging.getInstance().subscribeToTopic("companies");


        user123bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int rgsid= rgrp.getCheckedRadioButtonId();

                rbselected=(RadioButton)findViewById(rgsid);

                if(rbselected==rb1)
                {
                    Intent i=new Intent(MainActivity.this,user4.class);
                    startActivity(i);
                }
                else if(rbselected==rb2){
                    Intent i=new Intent(MainActivity.this,adminlogin.class);
                    startActivity(i);
                }
                else if(rbselected==rb3){
                    Intent i=new Intent(MainActivity.this,Fac_coordlogin.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(MainActivity.this,"Please select your category!",Toast.LENGTH_SHORT).show();

            }
        });
    }
    private KenBurnsView.TransitionListener onTransittionListener() {
        return new KenBurnsView.TransitionListener() {

            @Override
            public void onTransitionStart(Transition transition) {

               // Toast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTransitionEnd(Transition transition) {
               // Toast.makeText(MainActivity.this, "end", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are you sure, want to exit?");
        builder.setCancelable(true);

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setPositiveButton("Exit!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        AlertDialog alertdialog=builder.create();
        alertdialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mainhome,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if(id==R.id.deve){
            Intent i= new Intent(MainActivity.this, Developersfrhome.class);
            startActivity(i);
            return true;
        }
        if(id==R.id.abouthome){
            Intent i= new Intent(MainActivity.this, about.class);
            startActivity(i);
            return true;
        }

        if(id==R.id.exithome){
            finishAffinity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
