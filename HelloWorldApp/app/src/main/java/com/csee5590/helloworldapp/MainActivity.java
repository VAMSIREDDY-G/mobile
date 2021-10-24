package com.csee5590.helloworldapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText password;
    TextView info;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.uName);
        password=(EditText)findViewById(R.id.uPassword);
        info=(TextView)findViewById(R.id.declare);
        login=(Button)findViewById(R.id.btnlogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(name.getText().toString(),password.getText().toString());
            }
        });
    }
    private void validate(String userName, String userPassword)
    {
        if(userName.equals("Admin") && userPassword.equals("admin@123"))
        {
            Intent redirect = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(redirect);
        }
        else
        {
            info.setText("UserName or Password is invalid !!!");
        }
    }
}
