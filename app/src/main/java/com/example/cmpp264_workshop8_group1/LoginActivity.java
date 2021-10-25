package com.example.cmpp264_workshop8_group1;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername1, etPassword1;
    Button btnSignIn1;
    CustomerDB customerdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername1 = findViewById(R.id.etUsername1);
        etPassword1 = findViewById(R.id.etPassword1);
        btnSignIn1 = findViewById(R.id.btnSignIn1);
        customerdb = new CustomerDB(this);

        btnSignIn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = etUsername1.getText().toString();
                String pwd = etPassword1.getText().toString();

                if (user.equals("") || pwd.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Enter User Name and Password!");
                    builder.setCancelable(true);

                    builder.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert1 = builder.create();
                    alert1.show();
                }
                else if (customerdb.checkUserName(user) == false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("User name does not exist!Do you want to Register?");
                    builder.setCancelable(true);

                    builder.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                                    intent.putExtra("mode", "insert");
                                    startActivity(intent);
                                }
                            });

                    builder.setNegativeButton(
                            "Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert1 = builder.create();
                    alert1.show();
                }
                else if (customerdb.checkUserNamePassword(user, pwd))
                {
                    Toast.makeText(LoginActivity.this, "Sign In successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("user",user);
                    intent.putExtra("password",pwd);
                    startActivity(intent);
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Invalid credentials");
                    builder.setCancelable(true);

                    builder.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert2 = builder.create();
                    alert2.show();
                }
            }

        });
    }
}