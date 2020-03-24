package com.sark.android.placement_sit1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Deveoperfruser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deveoperfruser);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main3,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==android.R.id.home)
            onBackPressed();

        if (id == R.id.about3) {
            Intent i = new Intent(Deveoperfruser.this, about.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.exit) {
            Intent i = new Intent(Deveoperfruser.this, MainActivity.class);
            startActivity(i);
            Toast.makeText(Deveoperfruser.this, "Signed out", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
