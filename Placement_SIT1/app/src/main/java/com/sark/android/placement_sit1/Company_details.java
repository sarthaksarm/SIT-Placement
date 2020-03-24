package com.sark.android.placement_sit1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Company_details extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{
    EditText  phone, time,pack,regislink;
    Button b1, b2,b3,b4,ugselect,pgselect;
    TextView textView1,timedead,datedead;
    int year_x, month_x, day_x;
    int hour_x, min_x;
    static final int DIALOG_ID = 0;
    static final int DIALOG_ID1 = 1;
    static final int DIALOG_ID2 = 2;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    FirebaseStorage firebaseStorage;
    User user;
    String n;
    String keyid;
    TextView branch,date;
    String branches;
    ArrayList<String> mcaselection=new ArrayList<String>();
    ArrayList<String> ugselection=new ArrayList<String>();
    ArrayList<String> pgselection=new ArrayList<String>();
    static  int check=1;
    static int checkpg=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);

        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
        ref=firebaseDatabase.getReference("User");
        user=new User();
        branch=findViewById(R.id.branchtxt);

        date=(TextView)findViewById(R.id.dt);
        time=(EditText)findViewById(R.id.tm);
        pack=(EditText)findViewById(R.id.packagetxt);
        timedead=findViewById(R.id.timeedit);
        datedead=findViewById(R.id.dtdeadline);
        regislink=findViewById(R.id.regislinket);

        b1=(Button)findViewById(R.id.save);
        ugselect=findViewById(R.id.ugselectall);
        pgselect=findViewById(R.id.pgselectall);

        ugselect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(check%2!=0) {
                    CheckBox a = findViewById(R.id.checkBox);
                    a.setChecked(true);
                    checkboxes(a);
                    CheckBox b = findViewById(R.id.checkBox2);
                    b.setChecked(true);
                    checkboxes(b);
                    CheckBox c = findViewById(R.id.checkBox3);
                    c.setChecked(true);
                    checkboxes(c);
                    CheckBox d = findViewById(R.id.checkBox4);
                    d.setChecked(true);
                    checkboxes(d);
                    CheckBox e = findViewById(R.id.checkBox5);
                    e.setChecked(true);
                    checkboxes(e);
                    CheckBox f = findViewById(R.id.checkBox6);
                    f.setChecked(true);
                    checkboxes(f);
                    CheckBox g = findViewById(R.id.checkBox7);
                    g.setChecked(true);
                    checkboxes(g);
                    CheckBox h = findViewById(R.id.checkBox8);
                    h.setChecked(true);
                    checkboxes(h);
                    CheckBox i = findViewById(R.id.checkBox9);
                    i.setChecked(true);
                    checkboxes(i);
                    CheckBox j = findViewById(R.id.checkBox10);
                    j.setChecked(true);
                    checkboxes(j);
                    CheckBox k = findViewById(R.id.checkBox11);
                    k.setChecked(true);
                    checkboxes(k);
                    check++;
                }
            }
        });

        pgselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(checkpg%2!=0) {
                     CheckBox b = findViewById(R.id.checkBox12);
                     b.setChecked(true);
                     checkboxes(b);
                     CheckBox c = findViewById(R.id.checkBox13);
                     c.setChecked(true);
                     checkboxes(c);
                     CheckBox d = findViewById(R.id.checkBox14);
                     d.setChecked(true);
                     checkboxes(d);
                     CheckBox e = findViewById(R.id.checkBox15);
                     e.setChecked(true);
                     checkboxes(e);
                     CheckBox f = findViewById(R.id.checkBox16);
                     f.setChecked(true);
                     checkboxes(f);
                     CheckBox g = findViewById(R.id.checkBox17);
                     g.setChecked(true);
                     checkboxes(g);
                     CheckBox h = findViewById(R.id.checkBox18);
                     h.setChecked(true);
                     checkboxes(h);
                     CheckBox i = findViewById(R.id.checkBox19);
                     i.setChecked(true);
                     checkboxes(i);
                     CheckBox j = findViewById(R.id.checkBox20);
                     j.setChecked(true);
                     checkboxes(j);
                     CheckBox k = findViewById(R.id.checkBox21);
                     k.setChecked(true);
                     checkboxes(k);
                     CheckBox l = findViewById(R.id.checkBox22);
                     l.setChecked(true);
                     checkboxes(l);
                     CheckBox a = findViewById(R.id.checkBox23);
                     a.setChecked(true);
                     checkboxes(a);
                     checkpg++;
                 }
            }
        });
        b4=(Button)findViewById(R.id.timebtn);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment timePicker=new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"Time Picker");
            }
        });

        final Calendar cal= Calendar.getInstance();
        year_x= cal.get(Calendar.YEAR);
        month_x= cal.get(Calendar.MONTH);
        day_x=cal.get(Calendar.DAY_OF_MONTH);

        showDialogOnButtonClick();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String branchselection="";
                String ugbranchselection;
                String pgbranchselection;

                String bestring="",mtechstring="",mcastring="";


                CheckBox a1=findViewById(R.id.checkBox);
                CheckBox b1=findViewById(R.id.checkBox2);
                CheckBox c1=findViewById(R.id.checkBox3);
                CheckBox d1=findViewById(R.id.checkBox4);
                CheckBox e1=findViewById(R.id.checkBox5);
                CheckBox f1=findViewById(R.id.checkBox6);
                CheckBox g1=findViewById(R.id.checkBox7);
                CheckBox h1=findViewById(R.id.checkBox8);
                CheckBox i1=findViewById(R.id.checkBox9);
                CheckBox j1=findViewById(R.id.checkBox10);
                CheckBox k1=findViewById(R.id.checkBox11);

                CheckBox a=findViewById(R.id.checkBox23);
                CheckBox b=findViewById(R.id.checkBox12);
                CheckBox c=findViewById(R.id.checkBox13);
                CheckBox d=findViewById(R.id.checkBox14);
                CheckBox e=findViewById(R.id.checkBox15);
                CheckBox f=findViewById(R.id.checkBox16);
                CheckBox g=findViewById(R.id.checkBox17);
                CheckBox h=findViewById(R.id.checkBox18);
                CheckBox i=findViewById(R.id.checkBox19);
                CheckBox j=findViewById(R.id.checkBox20);
                CheckBox k=findViewById(R.id.checkBox21);
                CheckBox l=findViewById(R.id.checkBox22);

                CheckBox m=findViewById(R.id.checkBox25);

                if(m.isChecked())
                    mcastring="\n\nMCA";


                if(a1.isChecked()||b1.isChecked()||c1.isChecked()||d1.isChecked()||e1.isChecked()||f1.isChecked()||g1.isChecked()||h1.isChecked()
                        ||i1.isChecked()||j1.isChecked()||k1.isChecked())
                     bestring="" +
                             "\n\nB.E: ";

                if(a.isChecked()||b.isChecked()||c.isChecked()||d.isChecked()||e.isChecked()||f.isChecked()||g.isChecked()||h.isChecked()
                        ||i.isChecked()||j.isChecked()||k.isChecked()||l.isChecked())
                    mtechstring="\n\nMTech: ";

               ugbranchselection=bestring;
               pgbranchselection=mtechstring;

                for(String Selectionsug: ugselection)
                {
                    ugbranchselection=ugbranchselection+Selectionsug+" ";

                }

                for(String Selectionspg: pgselection)
                {
                    pgbranchselection=pgbranchselection+Selectionspg+" ";

                }

          branchselection=mcastring+ugbranchselection+pgbranchselection;

                if(branchselection.equals("")){
                    Toast.makeText(Company_details.this,"No branch has been selected. Please select atleast one branch",Toast.LENGTH_SHORT).show();
                }
                else {

                final String na = date.getText().toString();//na stores the date
                final String ax = time.getText().toString();//ax stores the company name
                final String packagedetail = pack.getText().toString();
                final String dateline=datedead.getText().toString();
                final String timeline=timedead.getText().toString();
                final String link=regislink.getText().toString();


                if (na.isEmpty())
                    Toast.makeText(Company_details.this, "You have not entered date!!", Toast.LENGTH_SHORT).show();

                else if (ax.isEmpty())
                    Toast.makeText(Company_details.this, "You have not entered company name!!", Toast.LENGTH_SHORT).show();
                else if (packagedetail.isEmpty())
                    Toast.makeText(Company_details.this, "You have not entered package details!!", Toast.LENGTH_SHORT).show();
                else if (dateline.isEmpty())
                    Toast.makeText(Company_details.this, "You have not entered Deadline date!!", Toast.LENGTH_SHORT).show();
                else if (timeline.isEmpty())
                    Toast.makeText(Company_details.this, "You have not entered Deadline time!!", Toast.LENGTH_SHORT).show();

                else if (link.isEmpty())
                    Toast.makeText(Company_details.this, "You have not entered company's registration link!!", Toast.LENGTH_SHORT).show();
                else
                {
                           keyid=textView1.getText().toString();

                           insert(keyid,branchselection);
                           check=1;
                           checkpg=1;
                   // Toast.makeText(Company_details.this, ax + "    " + na, Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Company_details.this,Company_details.class);
                    startActivity(intent);
                        }
                    }}
                  //  }
          //  }
            }
        );
    }
    public void showDialogOnButtonClick() {
        b2 = (Button) findViewById(R.id.dt2);
        b2.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {

                        showDialog(DIALOG_ID);

                    }
                });
        b3 = (Button) findViewById(R.id.dtdead);
        b3.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {

                        showDialog(DIALOG_ID2);

                    }
                });

    }
public void checkboxes(View v){

        boolean checked=((CheckBox)v).isChecked();


    switch (v.getId()) {

        case R.id.checkBox25:
            if (checked)
                mcaselection.add("MCA");

            else
                mcaselection.remove("MCA");
            break;
    }


        switch (v.getId()) {

            case R.id.checkBox:
                if (checked)
                    ugselection.add("CSE");

                else
                    ugselection.remove("CSE");

                break;

            case R.id.checkBox2:
                if (checked)
                    ugselection.add("ISE");

                else
                    ugselection.remove("ISE");

                break;
            case R.id.checkBox3:
                if (checked)
                    ugselection.add("ECE");

                else
                    ugselection.remove("ECE");

                break;
            case R.id.checkBox4:
                if (checked)
                    ugselection.add("MECH");

                else
                    ugselection.remove("MECH");

                break;
            case R.id.checkBox5:
                if (checked)
                    ugselection.add("TELE");

                else
                    ugselection.remove("TELE");

                break;
            case R.id.checkBox6:
                if (checked)
                    ugselection.add("CHEM");

                else
                    ugselection.remove("CHEM");

                break;
            case R.id.checkBox7:
                if (checked)
                    ugselection.add("EEE");

                else
                    ugselection.remove("EEE");

                break;
            case R.id.checkBox8:
                if (checked)
                    ugselection.add("IEM");

                else
                    ugselection.remove("IEM");

                break;
            case R.id.checkBox9:
                if (checked)
                    ugselection.add("EI");

                else
                    ugselection.remove("EI");
                break;
            case R.id.checkBox10:
                if (checked)
                    ugselection.add("Bio-Tech");

                else
                    ugselection.remove("Bio-Tech");
                break;
            case R.id.checkBox11:
                if (checked)
                    ugselection.add("CV");

                else
                    ugselection.remove("CV");
                break;
        }

    switch (v.getId()) {

        case R.id.checkBox12:
                if(checked)
                    pgselection.add("CNE");

                else
                    pgselection.remove("CNE");

                break;

            case R.id.checkBox13:
                if(checked)
                    pgselection.add("CF");

                else
                    pgselection.remove("CF");

                break;case R.id.checkBox14:
                if(checked)
                    pgselection.add("VLSI&ES");

                else
                    pgselection.remove("VLSI&ES");

                break;case R.id.checkBox15:
                if(checked)
                    pgselection.add("CVS");

                else
                    pgselection.remove("CVS");

                break;case R.id.checkBox16:
                if(checked)
                    pgselection.add("CIM");

                else
                    pgselection.remove("CIM");

                break;
            case R.id.checkBox17:
                if(checked)
                    pgselection.add("CS");

                else
                    pgselection.remove("CS");

                break;
            case R.id.checkBox18:
                if(checked)
                    pgselection.add("DC");

                else
                    pgselection.remove("DC");

                break;
            case R.id.checkBox19:
                if(checked)
                    pgselection.add("Manufacturing");

                else
                    pgselection.remove("Manufacturing");

                break;
            case R.id.checkBox20:
                if(checked)
                    pgselection.add("Nanotech");

                else
                    pgselection.remove("Nanotech");
              break;
            case R.id.checkBox21:
                if(checked)
                    pgselection.add("PE");

                else
                    pgselection.remove("PE");
                break;
            case R.id.checkBox22:
                if(checked)
                    pgselection.add("SP");

                else
                    pgselection.remove("SP");
                break;
            case R.id.checkBox23:
                if(checked)
                    pgselection.add("TE");

                else
                    pgselection.remove("TE");
                break;

        }
}
    @Override
    public void onBackPressed() {

        final AlertDialog.Builder builder=new AlertDialog.Builder(Company_details.this);
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
                Intent i= new Intent(Company_details.this,MainActivity.class);
                Toast.makeText(Company_details.this,"Signed out",Toast.LENGTH_SHORT).show();
               startActivity(i);
            }
        });
        AlertDialog alertdialog=builder.create();
        alertdialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if(id==R.id.developer){
            Intent i= new Intent(Company_details.this, Developers.class);
            startActivity(i);
            return true;
        }

        if(id==R.id.viewcmp){
            Intent i= new Intent(Company_details.this, Retrieve.class);
            startActivity(i);
            return true;
        }

        if(id==R.id.about1){
            Intent i= new Intent(Company_details.this, about.class);
            startActivity(i);
            return true;
        }

        if(id==R.id.signout){
            Intent i= new Intent(Company_details.this, MainActivity.class);
            Toast.makeText(Company_details.this,"Signed-out",Toast.LENGTH_SHORT).show();
            startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListner ,year_x ,month_x,day_x);
        else if(id == DIALOG_ID1)
            return new TimePickerDialog(Company_details.this, kTimePickerListner, hour_x,min_x,false);
        else if(id == DIALOG_ID2)
            return new DatePickerDialog(this, dpickerListner2 ,year_x ,month_x,day_x);
            return null;
    }
    private DatePickerDialog.OnDateSetListener dpickerListner
            =new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x = month+1;
            day_x =dayOfMonth;
            textView1 = (TextView) findViewById(R.id.keyiddate);

            TextView textView = (TextView) findViewById(R.id.dt);

            if(day_x<10){
                textView.setText("0"+day_x+ "/" +month_x +"/" +year_x);
                textView1.setText(year_x+ "" +month_x +"" +"0"+day_x);

            }

            if(month_x<10){
                textView.setText(day_x+ "/" +"0"+month_x +"/" +year_x);
                textView1.setText(year_x+ "" +"0"+month_x +"" +day_x);

            }

            if(day_x<10&&month_x<10){
                textView.setText("0"+day_x+ "/" +"0"+month_x +"/" +year_x);
                textView1.setText(year_x+ "" +"0"+month_x +"" +"0"+day_x);

            }

            if(day_x>=10&&month_x>=10){
                textView.setText(day_x+ "/" +month_x +"/" +year_x);
                textView1.setText(year_x+""+month_x+""+day_x);
            }

        }
    };
    private DatePickerDialog.OnDateSetListener dpickerListner2
            =new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x = month+1;
            day_x =dayOfMonth;
            TextView textView = (TextView) findViewById(R.id.dtdeadline);

            if(day_x<10){
                textView.setText("0"+day_x+ "/" +month_x +"/" +year_x);
                            }

            if(month_x<10){
                textView.setText(day_x+ "/" +"0"+month_x +"/" +year_x);
            }

            if(day_x<10&&month_x<10){
                textView.setText("0"+day_x+ "/" +"0"+month_x +"/" +year_x);
            }

            if(day_x>=10&&month_x>=10){
                textView.setText(day_x+ "/" +month_x +"/" +year_x);
            }

        }
    };


    protected  TimePickerDialog.OnTimeSetListener kTimePickerListner =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    hour_x=hourOfDay;
                    min_x=minute;
                    TextView textView = (TextView) findViewById(R.id.tm);
                    textView.setText(hour_x+ ":" +min_x);
                    // Toast.makeText(CompanyDetails.this,hour_x+ ":" +min_x ,Toast.LENGTH_SHORT).show();
                }
            };

    private void getValues(String keyid,String keyid2,String branchselect){

        user.setName(time.getText().toString());
        user.setDate(date.getText().toString());
        user.setPack(pack.getText().toString());
        user.setRegislink(regislink.getText().toString());
        user.setBranch(branchselect);
        user.setDatedead(datedead.getText().toString());
        user.setTimedead(timedead.getText().toString());
        user.setKey(keyid);
        user.setKey2(keyid2);

    }

private void insert(String key,String branchselect){

        getValues(System.currentTimeMillis()+"",key,branchselect);
        ref.child(System.currentTimeMillis()+"").setValue(user);

        Toast.makeText(Company_details.this, "Data Inserted ", Toast.LENGTH_SHORT).show();

}
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
         TextView timeedit=findViewById(R.id.timeedit);
         if(hourOfDay<10&&minute>=10)
         timeedit.setText("0"+hourOfDay+" : "+minute);

         else if(hourOfDay>=10&&minute<10)
             timeedit.setText(hourOfDay+" : "+"0"+minute);

         else if(hourOfDay<10&& minute<10)
             timeedit.setText("0"+hourOfDay+" : "+"0"+minute);

         else
             timeedit.setText(hourOfDay+" : "+minute);

    }
}