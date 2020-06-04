package com.example.ronin.parksmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;

public class payWithCard extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_with_card);

        CardForm cardForm = (CardForm)findViewById(R.id.cardform);
        TextView payamount = (TextView)findViewById(R.id.payment_amount);
        Button paybutton = (Button) findViewById(R.id.btn_pay);

        payamount.setText("$2");
        paybutton.setText(String.format("Pay %s", payamount.getText()));

        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {
                Toast toast = Toast.makeText(payWithCard.this,"Payment Successful", Toast.LENGTH_SHORT);
                toast.show();
                Intent i = new Intent(getApplicationContext(), confirm.class);
                startActivity(i);
            }
        });
    }
}
