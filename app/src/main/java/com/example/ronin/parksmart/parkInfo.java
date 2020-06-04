package com.example.ronin.parksmart;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class parkInfo extends AppCompatActivity {

    private static final String TAG = "parkInfo";
    private Button datetime;
    private TextView date;

    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog.OnTimeSetListener timeSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_info);

        datetime = (Button) findViewById(R.id.picktime);
        date = (TextView) findViewById(R.id.ddate);

        datetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);

                DatePickerDialog dialog = new DatePickerDialog(parkInfo.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                TimePickerDialog dialogTime = new TimePickerDialog(parkInfo.this,timeSetListener, hour, minute, false);
                dialogTime.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;

                Log.d(TAG, "onDateSet: mm/dd/yyy: "+ year + "/" + month +"/" + dayOfMonth);
                String date = month + "/" + dayOfMonth + "/"+ year;
                Intent i = new Intent(getApplicationContext(), detailinfo.class);
                i.putExtra("date",date);
                startActivity(i);

            }
        };

        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                 Log.d(TAG,"onTimeSet: time:" + hourOfDay +"/" + minute );
                String time = hourOfDay +":" + minute;
            }
        };

    }


}
