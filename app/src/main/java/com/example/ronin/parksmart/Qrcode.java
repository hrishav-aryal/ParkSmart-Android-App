package com.example.ronin.parksmart;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Qrcode extends AppCompatActivity {

    private ImageView Qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        Qr = (ImageView) findViewById(R.id.qrcode);

        String recievedId = getIntent().getStringExtra("id");


        TextView id = (TextView)findViewById(R.id.idd);
        id.setText(recievedId);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(recievedId, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            Qr.setImageBitmap(bitmap);

        }catch (WriterException e){
            e.printStackTrace();
        }

    }

    public void goback(View v){
        Intent i = new Intent(getApplicationContext(), confirm.class);
        startActivity(i);
    }
}
