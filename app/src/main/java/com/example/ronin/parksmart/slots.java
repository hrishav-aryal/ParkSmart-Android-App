package com.example.ronin.parksmart;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class slots extends AppCompatActivity {

    private ImageView car10;
    private ImageView car6;
    private ImageView car3;
    String hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slots);

        car10 = (ImageView)findViewById(R.id.car10);
        car6 = (ImageView)findViewById(R.id.car6);
        car3 = (ImageView)findViewById(R.id.car3);

        View v = LayoutInflater.from(slots.this).inflate(R.layout.activity_duration_dialog_box, null);
        final EditText hours = (EditText)v.findViewById(R.id.hours);
        final EditText minutes = (EditText)v.findViewById(R.id.minutes);

        AlertDialog.Builder builder = new AlertDialog.Builder(slots.this);
        builder.setView(v)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(hours==null){
                            hour = "0";
                        }else if(minutes==null){
                            minute="0";
                        }
                        hour = hours.getText().toString();
                        minute = minutes.getText().toString();
                    }
                }).setNegativeButton("Cancel", null).setCancelable(false);

        AlertDialog alert = builder.create();
        alert.show();



        car3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), bookthespot.class);
                startActivity(i);
            }
        });
        car6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), bookthespot.class);
                startActivity(i);
            }
        });
        car10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), bookthespot.class);
                startActivity(i);
            }
        });
    }
}
