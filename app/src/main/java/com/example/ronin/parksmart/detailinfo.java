package com.example.ronin.parksmart;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class detailinfo extends AppCompatActivity {

    private Button chooseslot;
    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailinfo);

        date = (TextView)findViewById(R.id.date);
        String recievedDate = getIntent().getStringExtra("date");
        date.setText("Date: " + recievedDate);


        chooseslot= (Button) findViewById(R.id.chooseslot);



    }
    public void slotpick(View v){

        Intent i = new Intent(getApplicationContext(), slots.class);
        startActivity(i);
    }
}
