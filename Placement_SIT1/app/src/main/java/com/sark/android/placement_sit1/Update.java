package com.sark.android.placement_sit1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Update extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
String nm,dat,keyid,key2;
TextView textView1,dtet,deaddtet,deadtmet;
String deaddate,deadtime;
EditText nmet,packet;
DatabaseReference ref;
Button b2,update,b3,b4;
    int year_x, month_x, day_x;
    int hour_x, min_x;
    static final int DIALOG_ID = 0;
    static final int DIALOG_ID1 = 1;
    static final int DIALOG_ID2 = 2;
    int t=1;
    int keyiddate;
    EditText regiset;
    String keyidupdate,pack,branches,linktxt;
Button datebtn,tmbtn,ugselect,pgselect;
    ArrayList<String> mcaselection=new ArrayList<String>();
    ArrayList<String> ugselection=new ArrayList<String>();
    ArrayList<String> pgselection=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        datebtn=findViewById(R.id.dtbtn);
       // tmbtn=findViewById(R.id.tmbtn);
        deaddtet=findViewById(R.id.deaddateet);
        deadtmet=findViewById(R.id.deadtimeet);
        packet=findViewById(R.id.packageedittxt);
        nmet=findViewById(R.id.tm);
        dtet=findViewById(R.id.dt);
        regiset=findViewById(R.id.regislinketupdate);

        pack=getIntent().getStringExtra("package");
        linktxt=getIntent().getStringExtra("link");
        nm=getIntent().getStringExtra("name");
        dat=getIntent().getStringExtra("date");
        keyid=getIntent().getStringExtra("key");
        key2=getIntent().getStringExtra("key2");
        deaddate=getIntent().getStringExtra("deaddate");
        deadtime=getIntent().getStringExtra("deadtime");
        branches=getIntent().getStringExtra("branches");

        textView1 = (TextView) findViewById(R.id.textView1);

        textView1.setText(""+dat.charAt(6)+dat.charAt(7)+dat.charAt(8)+dat.charAt(9)+dat.charAt(3)+dat.charAt(4)+dat.charAt(0)+dat.charAt(1));

        nmet.setText(nm);
        dtet.setText(dat);
        deaddtet.setText(deaddate);
        deadtmet.setText(deadtime);
        packet.setText(pack);
        regiset.setText(linktxt);

        final Calendar cal= Calendar.getInstance();
        year_x= cal.get(Calendar.YEAR);
        month_x= cal.get(Calendar.MONTH);
        day_x=cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();


        ugselect=findViewById(R.id.ugselectall);
        pgselect=findViewById(R.id.pgselectall);

        ugselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox a=findViewById(R.id.checkBox);
                a.setChecked(true);
                checkboxes(a);
                CheckBox b=findViewById(R.id.checkBox2);
                b.setChecked(true);
                checkboxes(b);
                CheckBox c=findViewById(R.id.checkBox3);
                c.setChecked(true);
                checkboxes(c);
                CheckBox d=findViewById(R.id.checkBox4);
                d.setChecked(true);
                checkboxes(d);
                CheckBox e=findViewById(R.id.checkBox5);
                e.setChecked(true);
                checkboxes(e);
                CheckBox f=findViewById(R.id.checkBox6);
                f.setChecked(true);
                checkboxes(f);
                CheckBox g=findViewById(R.id.checkBox7);
                g.setChecked(true);
                checkboxes(g);
                CheckBox h=findViewById(R.id.checkBox8);
                h.setChecked(true);
                checkboxes(h);
                CheckBox i=findViewById(R.id.checkBox9);
                i.setChecked(true);
                checkboxes(i);
                CheckBox j=findViewById(R.id.checkBox10);
                j.setChecked(true);
                checkboxes(j);
                CheckBox k=findViewById(R.id.checkBox11);
                k.setChecked(true);
                checkboxes(k);
            }
        });

        pgselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckBox b=findViewById(R.id.checkBox12);
                b.setChecked(true);
                checkboxes(b);
                CheckBox c=findViewById(R.id.checkBox13);
                c.setChecked(true);
                checkboxes(c);
                CheckBox d=findViewById(R.id.checkBox14);
                d.setChecked(true);
                checkboxes(d);
                CheckBox e=findViewById(R.id.checkBox15);
                e.setChecked(true);
                checkboxes(e);
                CheckBox f=findViewById(R.id.checkBox16);
                f.setChecked(true);
                checkboxes(f);
                CheckBox g=findViewById(R.id.checkBox17);
                g.setChecked(true);
                checkboxes(g);
                CheckBox h=findViewById(R.id.checkBox18);
                h.setChecked(true);
                checkboxes(h);
                CheckBox i=findViewById(R.id.checkBox19);
                i.setChecked(true);
                checkboxes(i);
                CheckBox j=findViewById(R.id.checkBox20);
                j.setChecked(true);
                checkboxes(j);
                CheckBox k=findViewById(R.id.checkBox21);
                k.setChecked(true);
                checkboxes(k);
                CheckBox l=findViewById(R.id.checkBox22);
                l.setChecked(true);
                checkboxes(l);
                CheckBox a=findViewById(R.id.checkBox23);
                a.setChecked(true);
                checkboxes(a);

            }
        });



        b4=(Button)findViewById(R.id.tmbtn);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment timePicker=new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"Time Picker");
            }
        });

        ref=FirebaseDatabase.getInstance().getReference().child("User");

        update=(Button)findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatefn();
            }
        });

    }

    public void updatefn(){
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

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
                    mcastring="\nMCA";


                if(a1.isChecked()||b1.isChecked()||c1.isChecked()||d1.isChecked()||e1.isChecked()||f1.isChecked()||g1.isChecked()||h1.isChecked()
                        ||i1.isChecked()||j1.isChecked()||k1.isChecked())
                    bestring="\nB.E: ";

                if(a.isChecked()||b.isChecked()||c.isChecked()||d.isChecked()||e.isChecked()||f.isChecked()||g.isChecked()||h.isChecked()
                        ||i.isChecked()||j.isChecked()||k.isChecked()||l.isChecked())
                    mtechstring="\nMTech: ";

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
                            Toast.makeText(Update.this,"No branch has been selected. Please select atleast one branch",Toast.LENGTH_SHORT).show();
                        }
else {

                            keyidupdate = textView1.getText().toString();
                            dataSnapshot.getRef().child(keyid).setValue(keyidupdate);
                            dataSnapshot.getRef().child(keyidupdate).child("branch").setValue(branchselection);
                            dataSnapshot.getRef().child(keyidupdate).child("date").setValue(dtet.getText().toString());
                            dataSnapshot.getRef().child(keyidupdate).child("datedead").setValue(deaddtet.getText().toString());
                            dataSnapshot.getRef().child(keyidupdate).child("key").setValue(keyidupdate);
                            dataSnapshot.getRef().child(keyidupdate).child("key2").setValue(keyidupdate);
                            dataSnapshot.getRef().child(keyidupdate).child("name").setValue(nmet.getText().toString());
                            dataSnapshot.getRef().child(keyidupdate).child("pack").setValue(packet.getText().toString());
                            dataSnapshot.getRef().child(keyidupdate).child("timedead").setValue(deadtmet.getText().toString());
                            dataSnapshot.getRef().child(keyidupdate).child("regislink").setValue(regiset.getText().toString());

                            if (!keyidupdate.equals(keyid))
                                dataSnapshot.getRef().child(keyid).removeValue();

                            Toast.makeText(Update.this, "Data Updated successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Update.this, Company_details.class);
                            startActivity(intent);

                        }
                    }
               // }
           // }
            @Override
            public void onCancelled(DatabaseError databaseError) {

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
        super.onBackPressed();
        Intent i=new Intent(Update.this,Company_details.class);
        startActivity(i);
    }

    public void showDialogOnButtonClick() {
        b2 = (Button) findViewById(R.id.dta);
        b3=(Button)findViewById(R.id.dtbtn);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID2);
            }
        });
        b2.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        showDialog(DIALOG_ID);
                    }
                });

        }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListner ,year_x ,month_x,day_x);
        else if(id == DIALOG_ID1)
            return new TimePickerDialog(Update.this, kTimePickerListner, hour_x,min_x,false);
        else if(id == DIALOG_ID2)
            return new DatePickerDialog(this, dpickerListner2 ,year_x ,month_x,day_x);
        else
            return null;
    }
    private DatePickerDialog.OnDateSetListener dpickerListner
            =new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x = month+1;
            day_x =dayOfMonth;
            textView1 = (TextView) findViewById(R.id.textView1);

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
            month_x = month;
            day_x =dayOfMonth;

            TextView textView = (TextView) findViewById(R.id.deaddateet);

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

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView timeedit=findViewById(R.id.deadtimeet);
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
