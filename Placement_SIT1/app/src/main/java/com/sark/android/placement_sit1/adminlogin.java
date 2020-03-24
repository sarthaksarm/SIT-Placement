package com.sark.android.placement_sit1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.flaviofaria.kenburnsview.Transition;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class adminlogin extends AppCompatActivity {
    EditText email,pass;
Button adminlogin;
KenBurnsView kenBurnsView;
    private boolean moving=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
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

        email = (EditText) findViewById(R.id.etName);
        pass = (EditText) findViewById(R.id.etPassword);

        adminlogin = (Button) findViewById(R.id.adminloginbtn);
        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminloginfn();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main4,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if(id==R.id.develop){
            Intent i= new Intent(adminlogin.this, Developersfrhome.class);
            startActivity(i);
            return true;
        }

        if(id==R.id.userlogin){
            Intent i= new Intent(adminlogin.this, MainActivity.class);
            startActivity(i);
            return true;
        }
        if(id==R.id.about4){
            Intent i= new Intent(adminlogin.this, about.class);
            startActivity(i);
            return true;
        }

        if(id==R.id.exithomemenu){
            Intent i= new Intent(adminlogin.this, MainActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void adminloginfn() {
        String memail = email.getText().toString();
        String mpass = pass.getText().toString();

        if(TextUtils.isEmpty(memail)&&TextUtils.isEmpty(mpass)){
            Toast.makeText(adminlogin.this,"Both fields are empty",Toast.LENGTH_SHORT).show();

        }
        else {
            if (TextUtils.isEmpty(memail)) {
                Toast.makeText(adminlogin.this, "E-mail address Field is empty", Toast.LENGTH_SHORT).show();

            }

            if (TextUtils.isEmpty(mpass)) {
                Toast.makeText(adminlogin.this, "Password Field is empty", Toast.LENGTH_SHORT).show();

            }
        }
        if(!memail.equals("") &&!mpass.equals("")) {
            if(memail.equalsIgnoreCase("placement")&&mpass.equals("s@rk210")){
                Intent i=new Intent(adminlogin.this,Company_details.class);
                startActivity(i);
            }
            else{
                Toast.makeText(adminlogin.this,"Login failed! Credentials don't match",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
