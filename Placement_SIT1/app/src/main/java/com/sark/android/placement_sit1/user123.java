package com.sark.android.placement_sit1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class user123 extends AppCompatActivity {
Button sbmtbtn;
EditText user123et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user123);

        user123et=(EditText)findViewById(R.id.user123nameet);
        sbmtbtn = (Button) findViewById(R.id.sbmtbtn);

        sbmtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username=user123et.getText().toString();

                if(username.isEmpty())
                    Toast.makeText(user123.this,"USN can not be empty",Toast.LENGTH_SHORT).show();

               else if(!(username.length()==10)) {
                    Toast.makeText(user123.this,"USN entered is invalid",Toast.LENGTH_SHORT).show();
                }

                else {

                    if(Validate(username))
                    {

                        Intent i = new Intent(user123.this, Retrieveuser123.class);
                        startActivity(i);
                        user123et.setText("");
                        Toast.makeText(user123.this,"Signing in...",Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(user123.this,"USN invalid!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private Boolean Validate(String usn){

        int a=usn.charAt(0);
        char b=usn.charAt(1);
        char c=usn.charAt(2);
        int d=usn.charAt(3);
        int e=usn.charAt(4);
        char f=usn.charAt(5);
        char g=usn.charAt(6);
        int h=usn.charAt(7);
        int i=usn.charAt(8);
        int j=usn.charAt(9);
   // Toast.makeText(user123.this,"abcd char: "+a+" "+b+" "+c+" "+d+" "+e+" "+f+" "+g+" "+h+" "+i+" "+j,Toast.LENGTH_SHORT).show();
        boolean z1=false,z2=false,z3=false,z4=false,z5=false,z6=false;

        String sixseven=f+""+g;

        if(d>=48&&d<=57)
            z1=true;

      if(e>=48&&e<=57&&e!=53)
            z2=true;

        if(h>=48&&h<=57)
            z4=true;
       if(i>=48&&i<=57)
            z5=true;
       if(j>=48&&j<=57)
            z6=true;


        String arr[]=new String[] {"CS","IS","ME","TE","CV","CH","EC","EI","EE","IE"};
//Toast.makeText(user123.this,"String sixseven= "+sixseven,Toast.LENGTH_SHORT).show();
       for (int y = 0; y< arr.length; y++) {
            if (sixseven.equalsIgnoreCase(arr[y])) {
                z3=true;
                break;
            }
        }


if(a==49&&(b=='S'||b=='s')&&(c=='I'||c=='i')&&z1&&z2&&z3&&z4&&z5&&z6)
{
    return true;
}
return false;

}}
