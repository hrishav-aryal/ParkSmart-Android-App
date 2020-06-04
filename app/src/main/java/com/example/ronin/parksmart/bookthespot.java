package com.example.ronin.parksmart;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;

public class bookthespot extends AppCompatActivity {

    private Button paypal;
    private Button card;
    private TextView afterpayment;
    public static final int PAYPAL_REQUEST_CODE =123;
    private static PayPalConfiguration configuration;
    String paypalClientID = "AblI0rST7MjfyhBp2RS3xb16MZ3wYagrkI6vvcM4unEY3BUwkLRK7wWIFm38AIdkxm4uJR-ZKEfQT_iR";
    Intent service;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookthespot);

        paypal= (Button) findViewById(R.id.paypal);
        card = (Button) findViewById(R.id.creditdebit);
        afterpayment =(TextView)findViewById(R.id.afterpayment);

        configuration = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(paypalClientID);
        service = new Intent (this, PayPalService.class);
        service.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configuration);
        startService(service);
    }

    public void cardpay(View v){
        Intent i = new Intent(getApplicationContext(), payWithCard.class);
        startActivity(i);
    }

    public void clickpay(View v){
        PayPalPayment payment = new PayPalPayment(new BigDecimal(2), "USD", "Payment with Paypal",
                PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configuration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PAYPAL_REQUEST_CODE)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                // we have to confirm that the payment worked to avoid fraud
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                if(confirmation != null)
                {
                    String state = confirmation.getProofOfPayment().getState();

                    if(state.equals("approved")) {
                        Toast toast = Toast.makeText(bookthespot.this,"Payment Succesful", Toast.LENGTH_SHORT);
                        toast.show();
                        afterpayment.setText("Payment Successful!");
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(getApplicationContext(), confirm.class);
                                startActivity(i);
                            }
                        }, 2000);
                    }

                    else {
                        Toast toast = Toast.makeText(bookthespot.this,"ERROR in the payment", Toast.LENGTH_SHORT);
                        toast.show();
                        afterpayment.setText("ERROR! Try Again");
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(getApplicationContext(), confirm.class);
                                startActivity(i);
                            }
                        }, 2000);
                    }

                }
                else{
                    Toast toast = Toast.makeText(bookthespot.this,"ERROR in the payment", Toast.LENGTH_SHORT);
                    toast.show();
                    afterpayment.setText("NULL");
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(getApplicationContext(), confirm.class);
                            startActivity(i);
                        }
                    }, 2000);
                }

            }
        }
    }
}


