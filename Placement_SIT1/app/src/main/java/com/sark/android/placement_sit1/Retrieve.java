package com.sark.android.placement_sit1;

import android.content.Intent;
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


public class Retrieve extends AppCompatActivity {
    ListView listView;
    FirebaseDatabase firebaseDatabase;
    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

     listView=findViewById(R.id.listView);

        Query query=firebaseDatabase.getInstance().getReference().child("User").orderByChild("key2");
        FirebaseListOptions<User> options=new FirebaseListOptions.Builder<User>()
                .setLayout(R.layout.user)
                .setQuery(query,User.class)
                .build();

        adapter=new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
               // String date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                TextView name=v.findViewById(R.id.username);
                TextView date=v.findViewById(R.id.userdate);
                TextView pack=v.findViewById(R.id.packageid);
                TextView branches=v.findViewById(R.id.branches);
                TextView deadlinedt=v.findViewById(R.id.deadlinedt);
               TextView timeedit=v.findViewById(R.id.timeedittxt);
                TextView linktxt=v.findViewById(R.id.regiset);

                        User std=(User)model;
                //if(std.getDate().compareTo(date1) > 0){
                //  pos[j++]=position;
                    name.setText("Company Visit: "+std.getName().toString());
                    date.setText("Date: "+std.getDate().toString());
                    pack.setText("Company Package: "+std.getPack().toString());
                    branches.setText("Eligible branch students: "+std.getBranch().toString());
                    deadlinedt.setText("Deadline date for registrations: "+std.getDatedead().toString());
                    timeedit.setText("Time: "+std.getTimedead().toString());
                    linktxt.setText("Click to register: "+std.getRegislink().toString());

            }

           /* @Override
            public Object getItem(int position) {
                return super.getItem(getItemCount()-1-position);
            }*/
        };

       listView.setAdapter(adapter);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(Retrieve.this,Update.class);

                User s=(User)parent.getItemAtPosition(position);
                i.putExtra("name",s.getName());
                i.putExtra("date",s.getDate());
                i.putExtra("key",s.getKey());
                i.putExtra("key2",s.getKey2());
                i.putExtra("deaddate",s.getDatedead());
                i.putExtra("deadtime",s.getTimedead());
                i.putExtra("package",s.getPack());
                i.putExtra("branches",s.getBranch());
                i.putExtra("link",s.getRegislink());
                startActivity(i);
            }
        });

    }

    private int getItemCount() {
        return listView.getCount();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(Retrieve.this,Company_details.class);
        startActivity(i);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main5,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if(id==R.id.developersmenu){
            Intent i= new Intent(Retrieve.this, Developers.class);
            startActivity(i);
            return true;
        }

        if(id==R.id.about5){
            Intent i= new Intent(Retrieve.this, about.class);
            startActivity(i);
            return true;
        }

        if(id==R.id.signout){
            Intent i= new Intent(Retrieve.this, MainActivity.class);
            Toast.makeText(Retrieve.this,"Signed-out",Toast.LENGTH_SHORT).show();
            startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}