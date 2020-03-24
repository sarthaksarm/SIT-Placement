package com.sark.android.placement_sit1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class newuser123 extends AppCompatActivity {
Button insertbtn;
EditText regisemailet;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    FirebaseStorage firebaseStorage;

    KenBurnsView kenBurnsView;
    private boolean moving=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newuser123);
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
        ref=firebaseDatabase.getReference("UserRegister");
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
        insertbtn=(Button)findViewById(R.id.insertbtn);
        regisemailet=(EditText)findViewById(R.id.regisemailet);

        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insert emailuser into registeruser table
                final String emailuser=regisemailet.getText().toString();
                if(emailuser.isEmpty())
                    Toast.makeText(newuser123.this,"Email address can't be left empty",Toast.LENGTH_SHORT).show();


                else{
                            ref.child(System.currentTimeMillis() + "").setValue(emailuser);
                            regisemailet.setText("");

                            final AlertDialog.Builder builder = new AlertDialog.Builder(newuser123.this);
                            builder.setMessage("Registered successfully!\nPlease wait until placement department verifies your email address" +
                                    ".");
                            builder.setCancelable(true);

                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(newuser123.this, MainActivity.class);
                                    startActivity(i);
                                }
                            });
                            AlertDialog alertdialog = builder.create();
                            alertdialog.show();
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
