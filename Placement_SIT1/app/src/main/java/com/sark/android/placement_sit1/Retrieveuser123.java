package com.sark.android.placement_sit1;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
public class Retrieveuser123 extends AppCompatActivity {
    ListView listView;
    FirebaseDatabase firebaseDatabase;
    FirebaseListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieveuser123);
        listView = findViewById(R.id.listView);

        Query query = firebaseDatabase.getInstance().getReference().child("User").orderByChild("key2");
        FirebaseListOptions<User> options = new FirebaseListOptions.Builder<User>()
                .setLayout(R.layout.usernew)
                .setQuery(query, User.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                // String date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                TextView name = v.findViewById(R.id.username);
                TextView date = v.findViewById(R.id.userdate);
                TextView pack = v.findViewById(R.id.packageid);
                TextView branches = v.findViewById(R.id.branches);
                User std = (User) model;
                //if(std.getDate().compareTo(date1) > 0){
                //  pos[j++]=position;
                name.setText("Company Visit: " + std.getName().toString());
                date.setText("Date: " + std.getDate().toString());
                pack.setText("Company Package: " + std.getPack().toString());
                branches.setText("Eligible branch students: " + std.getBranch().toString());
            }

           /* @Override
            public Object getItem(int position) {
                return super.getItem(getItemCount() - 1 - position);
            }*/
        };
        listView.setAdapter(adapter);


    }
    private int getItemCount() {
        return listView.getCount();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder=new AlertDialog.Builder(Retrieveuser123.this);
        builder.setMessage("Are you sure, want to exit?");
        builder.setCancelable(true);

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setPositiveButton("Signout!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              Intent i=new Intent(Retrieveuser123.this,MainActivity.class);
              startActivity(i);
              Toast.makeText(Retrieveuser123.this,"Signed out",Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertdialog=builder.create();
        alertdialog.show();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if(id==R.id.developer){
            Intent i= new Intent(Retrieveuser123.this, Developers123.class);
            startActivity(i);
            return true;
        }

        if(id==R.id.exit){
            Intent i= new Intent(Retrieveuser123.this, MainActivity.class);
            startActivity(i);
            Toast.makeText(Retrieveuser123.this,"Signed out",Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
