package com.example.ronin.parksmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class confirm extends AppCompatActivity {

    private Button qrcode;
    private Button cancelbooking;
    private TextView id;
    private TextView duration;
    String sendId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);


        String reciecedhour = getIntent().getStringExtra("Duration");
        String reciecedminute = getIntent().getStringExtra("Durationn");

        duration = (TextView)findViewById(R.id.duration);


        qrcode = (Button)findViewById(R.id.qrcode);
        cancelbooking = (Button)findViewById(R.id.cancelbook);
        id = (TextView) findViewById(R.id.id);
        id.equals("4567123");
        sendId = id.getText().toString();




    }

    public void qrcode(View v){
        Intent i = new Intent(getApplicationContext(), Qrcode.class);
        i.putExtra("id",sendId);
        startActivity(i);
    }

    public void cancelbook(View v){
        Toast pas = Toast.makeText(confirm.this,"Booking Cancelled!", Toast.LENGTH_SHORT);
        pas.show();
        Intent i = new Intent(getApplicationContext(), displayinfo.class);
        startActivity(i);
    }
}
