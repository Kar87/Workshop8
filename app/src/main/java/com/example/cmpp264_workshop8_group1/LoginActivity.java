package com.example.cmpp264_workshop8_group1;


import androidx.appcompat.app.AppCompatActivity;
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
                    Toast.makeText(LoginActivity.this, "Please enter required fields", Toast.LENGTH_SHORT).show();
                    System.out.println("Enter values");
                }
                else if (customerdb.checkUserNamePassword(user, pwd))
                {
                        Toast.makeText(LoginActivity.this, "Sign In successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        intent.putExtra("user",user);
                        intent.putExtra("password",pwd);
                        intent.putExtra("mode", "update");
                        startActivity(intent);                }
                else
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();

            }

        });

    }
}