package com.example.sqliteloginregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password,reenterPassword;
    Button btnRegister,btnLogin;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        reenterPassword = (EditText) findViewById(R.id.reenterPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnSignup);
        myDB = new DBHelper(this);

        btnRegister.setOnClickListener(view -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            String repass = reenterPassword.getText().toString();
            if(user.equals("")||pass.equals("")||repass.equals("")){
                Toast.makeText(MainActivity.this,"Fill all fields",Toast.LENGTH_LONG).show();
            }else{
                if(pass.equals(repass)){
                    Boolean userCheckResult = myDB.checkUsername(user);
                    if(!userCheckResult){
                        Boolean checkInsert = myDB.insertData(user,pass);
                        if(!checkInsert){
                            Toast.makeText(MainActivity.this,"Registration failed",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this,"Registration successful",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                        }
                    }else{
                        Toast.makeText(MainActivity.this,"Username already exists",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"Password not matching",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        });
    }
}