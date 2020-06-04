package com.example.ronin.parksmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class registerpage extends AppCompatActivity {

    databaseHelper data = new databaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage);

    }

    public void buttonclick(View v){
        if(v.getId() == R.id.signin){

            EditText name = (EditText) findViewById(R.id.name);
            EditText usernameregister = (EditText) findViewById(R.id.usernameregister);
            EditText email = (EditText) findViewById(R.id.email);
            EditText contact = (EditText) findViewById(R.id.contact);
            EditText plate = (EditText) findViewById(R.id.plate);
            EditText newpass = (EditText) findViewById(R.id.newpass);
            EditText confirmpass = (EditText) findViewById(R.id.confirmpass);

            String namestr = name.getText().toString();
            String usernameregisterstr = usernameregister.getText().toString();
            String emailstr = email.getText().toString();
            String contactstr = contact.getText().toString();
            String platestr = plate.getText().toString();
            String newpassstr = newpass.getText().toString();
            String confirmpassstr = confirmpass.getText().toString();

            if(!newpassstr.equals(confirmpassstr)){
                Toast pass = Toast.makeText(registerpage.this,"Passwords don't match", Toast.LENGTH_SHORT);
                pass.show();
            }
            else {
                //insert data in database
                Contact c = new Contact();
                c.setContactno(contactstr);
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setUsername(usernameregisterstr);
                c.setPass(newpassstr);
                c.setPlate(platestr);

                data.insertContact(c);
                Toast pass = Toast.makeText(registerpage.this,"Sign up successful", Toast.LENGTH_SHORT);
                pass.show();

                Intent in = new Intent(getApplicationContext(),MainActivity.class);
                in.putExtra("name", namestr);
                startActivity(in);
            }

        }
    }

}
