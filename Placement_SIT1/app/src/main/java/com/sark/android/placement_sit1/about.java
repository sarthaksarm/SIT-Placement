package com.sark.android.placement_sit1;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class about extends AppCompatActivity {
TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        WebView webView= (WebView) findViewById(R.id.webview1);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
       // t1=(TextView)findViewById(R.id.t1);
       // t1.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);

        String htmlText = " %s ";
        String myData = "<html><body style=\"text-align:justify\">The purpose and main aim of developing this app may be the missing piece of many unfinished projects, to provide a hand-on  access  with all the essential company details so that none of the students would miss any placement opportunity due to lack of information.<br><br>All the users of the app would be benefited by having an exact handout solution to escape from those queues, which ask the same repeated question from placement department of our college every time, \"Which is the upcoming company and its details?\", in form of SIT Placement app. It also becomes problematic to keep track of each company among a list of companies, every day from mails, so having all company details at one place would make this job simpler and easier.<br><br>It has also been ensured that none of its users other than SIT students, " +
                "can access any information regarding companies registered in the app.<br><br><u>Special thanks to</u> :<br><b>Dr. R. Aparna</b><br>(Prof. and Head department of ISE)<br><b>Dr. Purohit Srinivasacharya</b><br>(Associate Professor)<br><b>S Sharath Kumar</b><br>(Assistant Professor)<br></body></html>";

        myData.replace("\\r\\n", "<br>").replace("\\n", "<br>");
        webView.loadDataWithBaseURL(null,String.format(htmlText, myData), "text/html", "utf-8","utf-8");
        webSettings.setDefaultFontSize(19);
        webView.setBackgroundColor(000000);
        webView.setPaddingRelative(2,2,2,2);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu7,menu);
        return super.onCreateOptionsMenu(menu);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if(id==android.R.id.home)
            onBackPressed();

        return super.onOptionsItemSelected(item);
    }
}
