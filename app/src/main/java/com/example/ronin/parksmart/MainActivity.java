package com.example.ronin.parksmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import static android.R.attr.name;


public class MainActivity extends AppCompatActivity {

    private Button login;
    private Button register;

    databaseHelper data = new databaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button)findViewById(R.id.login);
        register = (Button)findViewById(R.id.register);


    }


    public void buttonclick(View v){

        if(v.getId() == R.id.login){

            EditText username = (EditText) findViewById(R.id.username);
            String name = username.getText().toString();

            EditText password = (EditText) findViewById(R.id.password);
            String pass = password.getText().toString();

            String pd = data.searchPass(name);
            String namee = getIntent().getStringExtra("name");

           Intent i = new Intent(getApplicationContext(), parkInfo.class);
            startActivity(i);

            if (pass.equals(pd)){
                Intent in = new Intent(getApplicationContext(), displayinfo.class);
                in.putExtra("Name", namee);
                startActivity(in);
            }else{
                Toast pas = Toast.makeText(MainActivity.this,"Credential don't match", Toast.LENGTH_SHORT);
                pas.show();
            }



        }

        else if(v.getId() == R.id.register){

            Intent in = new Intent(getApplicationContext(),registerpage.class);
            startActivity(in);

        }

    }
}
