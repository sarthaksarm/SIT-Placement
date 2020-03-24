package com.sark.android.placement_sit1;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.flaviofaria.kenburnsview.Transition;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;

public class Fac_coordlogin extends AppCompatActivity {
    Button facloginbtn;
    EditText mailet;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    FirebaseStorage firebaseStorage;
    int t=0;

    KenBurnsView kenBurnsView;
    private boolean moving=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_coordlogin);
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
        ref=firebaseDatabase.getReference("FacultyCoordinators");
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
        facloginbtn=(Button)findViewById(R.id.facloginbtn);
        mailet=(EditText)findViewById(R.id.mailet);

//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            NotificationManager mNotificationManager =
//                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
//            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
//            mChannel.enableLights(true);
//            mChannel.setLightColor(Color.RED);
//            mChannel.enableVibration(true);
//            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
//            mNotificationManager.createNotificationChannel(mChannel);
//        }

        facloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailuser=mailet.getText().toString();
                if(emailuser.isEmpty())
                    Toast.makeText(Fac_coordlogin.this,"Mail-id can't be left empty",Toast.LENGTH_SHORT).show();

                else{
                    final String mailid = mailet.getText().toString();

                    ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Iterable<DataSnapshot> csnap = dataSnapshot.getChildren();

                                    for (DataSnapshot user : csnap) {
                                        User u = user.getValue(User.class);

                                        if (mailid.equalsIgnoreCase(u.getMailid())) {

                                           // FirebaseMessaging.getInstance().subscribeToTopic("companies");

                                            Intent j = new Intent(Fac_coordlogin.this, Retrieve_coord.class);
                                            startActivity(j);
                                            t++;
                                            break;
                                        }
                                    }
                                    if(t==0)
                                        Toast.makeText(Fac_coordlogin.this, "This mail-id is not Registered.", Toast.LENGTH_SHORT).show();
                                    else
                                        Toast.makeText(Fac_coordlogin.this, "Welcome "+mailid+" to SIT Placement app", Toast.LENGTH_SHORT).show();

                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                }
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
}