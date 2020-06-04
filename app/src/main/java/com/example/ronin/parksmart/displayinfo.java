package com.example.ronin.parksmart;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class displayinfo extends AppCompatActivity {

    private Button getLocation;
    private Button selectAddress;
    private final int PLACE_PICKER_REQUEST=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayinfo);

        getLocation = (Button) findViewById(R.id.getLocation);
        selectAddress = (Button) findViewById(R.id.selectAd);
        TextView t = (TextView)findViewById(R.id.un);


        String name = getIntent().getStringExtra("Name");
        t.setText("Hello " + name);
    }

    public void buttonclick(View v){
        if(v.equals(selectAddress)){
            Intent i = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(i);
        }

    }


    public void startplacepicker(){
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            Intent intent = builder.build(this);
            startActivityForResult(intent,PLACE_PICKER_REQUEST);
        }
        catch (Exception e) {
            e.printStackTrace();

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==PLACE_PICKER_REQUEST && resultCode==RESULT_OK){
            Place place =  PlacePicker.getPlace(data, this);
            String address =  String.format("Place: %s", place.getAddress().toString());

        }
    }


}
