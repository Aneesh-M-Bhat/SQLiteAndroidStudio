package com.example.sqliteloginregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button btnLogin;
    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.editTextUsernameLogin);
        password = findViewById(R.id.editTextPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        myDB = new DBHelper(this);
        btnLogin.setOnClickListener(view->{
            String user = username.getText().toString();
            String pass = password.getText().toString();
            if(user.equals("")||pass.equals("")) Toast.makeText(LoginActivity.this,"Fill all fields",Toast.LENGTH_SHORT).show();
            else {
                Boolean checkUserAndPass = myDB.checkUsernamePassword(user, pass);
                if(checkUserAndPass){
                    Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}