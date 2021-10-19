package com.example.cmpp264_workshop8_group1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    TextView tvId, tvRp;
    EditText etCustomerId, etFirstName, etLastName, etAddress, etCity, etProv,etPostal, etCountry,  etHomePhone,
            etBusPhone, etEmail, etAgentId, etUserName, etPassword, etRePassword;
    Button btnRegister;
    DBHelper db;
    CustomerDB customerDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvId = findViewById(R.id.tvId);
        tvRp = findViewById(R.id.tvRp);
        etCustomerId = findViewById(R.id.etCustomerId);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etAddress = findViewById(R.id.etAddress);
        etCity = findViewById(R.id.etCity);
        etProv = findViewById(R.id.etProv);
        etPostal = findViewById(R.id.etPostal);
        etCountry = findViewById(R.id.etCountry);
        etHomePhone = findViewById(R.id.etHomePhone);
        etBusPhone = findViewById(R.id.etBusPhone);
        etEmail = findViewById(R.id.etEmail);
        etAgentId = findViewById(R.id.etAgentId);
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        etRePassword = findViewById(R.id.etRePassword);
        btnRegister = findViewById(R.id.btnRegister);

        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");
        String user = intent.getStringExtra("user");
        String pwd = intent.getStringExtra("password");
        customerDB = new CustomerDB(this);
        db = new DBHelper(this);

        if (mode.equals("update")) {
            Customer customer = customerDB.getCustomer(user, pwd);
            etCustomerId.setText(customer.getCustomerId() + "");
            etFirstName.setText(customer.getCustFirstName());
            etLastName.setText(customer.getCustLastName());
            etAddress.setText(customer.getCustAddress());
            etCity.setText(customer.getCustCity());
            etProv.setText(customer.getCustProv());
            etPostal.setText(customer.getCustPostal());
            etCountry.setText(customer.getCustCountry());
            etHomePhone.setText(customer.getCustHomePhone());
            etBusPhone.setText(customer.getCustBusPhone());
            etEmail.setText(customer.getCustEmail());
            etAgentId.setText(customer.getAgentId() + "");
            etUserName.setText(customer.getUserName());
            etPassword.setText(customer.getPassword());
            btnRegister.setText("SAVE");
            etRePassword.setVisibility(View.INVISIBLE);
            tvRp.setVisibility(View.INVISIBLE);
            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    tvRp.setVisibility(View.VISIBLE);
                    etRePassword.setVisibility(View.VISIBLE);
                    etRePassword.isFocusable();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            };

            etPassword.addTextChangedListener(textWatcher);

        } else {
            tvId.setVisibility(View.INVISIBLE);
            etCustomerId.setVisibility(View.INVISIBLE);
            etRePassword.setVisibility(View.VISIBLE);
            etRePassword.isFocusable();
            btnRegister.setText("Sign Up");
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
                alert.setTitle("Save");
                alert.setMessage("Save changes?");
                /**
                 * Perform save action if user confirms the changes
                 */
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /**
                         * Save the changes to existing agent
                         */
                        if (mode.equals("update")) {
                            Customer customer = new Customer(Integer.parseInt(etCustomerId.getText().toString()), etFirstName.getText().toString(),
                                    etLastName.getText().toString(), etAddress.getText().toString(),
                                    etCity.getText().toString(), etProv.getText().toString(),
                                    etPostal.getText().toString(), etCountry.getText().toString(),
                                    etHomePhone.getText().toString(), etBusPhone.getText().toString(),
                                    etEmail.getText().toString(), Integer.parseInt(etAgentId.getText().toString()),
                                    etUserName.getText().toString(), etPassword.getText().toString());
                                customerDB.updateCustomer(customer);
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                        /**
                         * Save the new agent being added
                         */
                        else {
                            Customer customer = new Customer(0, etFirstName.getText().toString(),
                                    etLastName.getText().toString(), etAddress.getText().toString(),
                                    etCity.getText().toString(), etProv.getText().toString(),
                                    etPostal.getText().toString(), etCountry.getText().toString(),
                                    etHomePhone.getText().toString(), etBusPhone.getText().toString(),
                                    etEmail.getText().toString(), Integer.parseInt(etAgentId.getText().toString()),
                                    etUserName.getText().toString(), etPassword.getText().toString());
                            customerDB.insertCustomer(customer);
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                        dialog.dismiss();
                    }
                });
                /**
                 * Do not save if user does not confirm
                 */
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();
            }
        });
    }
}