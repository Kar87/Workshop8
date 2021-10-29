/**
 * Author: Sai Shalini Karaikatte Venugopal
 * Code for inserting or updating Customer details
 */

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

//Register activity with form input
public class RegisterActivity extends AppCompatActivity {
    TextView tvId, tvRp;
    EditText etCustomerId, etFirstName, etLastName, etAddress, etCity, etProv,etPostal, etCountry,  etHomePhone,
            etBusPhone, etEmail, etAgentId, etUserName, etPassword, etRePassword;
     Button btnRegister, btnEdit;
    DBHelper db;
    CustomerDB customerDB;
    boolean passUpdate = false;
    boolean userUpdate = false;
    boolean isNull = false;

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
        btnEdit = findViewById(R.id.btnEdit);

        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");
        String user = intent.getStringExtra("user");
        String pwd = intent.getStringExtra("password");
        customerDB = new CustomerDB(this);
        db = new DBHelper(this);


        //if user logged in populate user details
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
            btnRegister.setVisibility(View.INVISIBLE);
            etFirstName.setEnabled(false);
            etLastName.setEnabled(false);
            etAddress.setEnabled(false);
            etCity.setEnabled(false);
            etProv.setEnabled(false);
            etPostal.setEnabled(false);
            etCountry.setEnabled(false);
            etHomePhone.setEnabled(false);
            etBusPhone.setEnabled(false);
            etEmail.setEnabled(false);
            etAgentId.setEnabled(false);
            etUserName.setEnabled(false);
            etPassword.setEnabled(false);
            etRePassword.setVisibility(View.INVISIBLE);
            tvRp.setVisibility(View.INVISIBLE);
        }
        //if user is registering disable customer ID and allow insert
        else {
            tvId.setVisibility(View.INVISIBLE);
            etCustomerId.setVisibility(View.INVISIBLE);
            etRePassword.setVisibility(View.VISIBLE);
            etRePassword.isFocusable();
            btnRegister.setText("Sign Up");
            btnEdit.setVisibility(View.INVISIBLE);
        }

        //on click of Save validate and insert or updates based on the mode
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //boolean valid = true;
                boolean valid = validate();
                if (valid) {
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
                             * Save the changes to existing customer
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
                                if (passUpdate || userUpdate)
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                else {
                                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                                    intent.putExtra("user", user);
                                    intent.putExtra("password", pwd);
                                    startActivity(intent);
                                }
                            }
                            /**
                             * Save the customer agent being added
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
            }
        });

        //allow data edit when EDIT button is clicked
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnEdit.setVisibility(View.INVISIBLE);
                btnRegister.setVisibility(View.VISIBLE);
                etFirstName.setEnabled(true);
                etLastName.setEnabled(true);
                etAddress.setEnabled(true);
                etCity.setEnabled(true);
                etProv.setEnabled(true);
                etPostal.setEnabled(true);
                etCountry.setEnabled(true);
                etHomePhone.setEnabled(true);
                etBusPhone.setEnabled(true);
                etEmail.setEnabled(true);
                etAgentId.setEnabled(true);
                etUserName.setEnabled(true);
                etPassword.setEnabled(true);
                etRePassword.setVisibility(View.INVISIBLE);
                tvRp.setVisibility(View.INVISIBLE);

                //when password is changed re-direct to login screen
                TextWatcher textWatcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        tvRp.setVisibility(View.VISIBLE);
                        etRePassword.setVisibility(View.VISIBLE);
                        etRePassword.isFocusable();
                        passUpdate = true;
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                };

                etPassword.addTextChangedListener(textWatcher);

                //when user-name is changed re-direct to login screen
                TextWatcher textWatcher1 = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        userUpdate = true;
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                };

                etUserName.addTextChangedListener(textWatcher1);

            }
        });

    }

    //validate fields
    private boolean validate() {
        StringBuilder errors = new StringBuilder();

        //validate for mandatory field and length
        if (etFirstName.getText().toString().matches("")) {
            errors.append("- Please enter a Firstname.\n");
        }
        else if(etFirstName.getText().toString().trim().length() > 25) {
            errors.append("- Firstname cannot exceed 25 characters.\n");
        }
        //validate for mandatory field and length
        if (etLastName.getText().toString().matches("")) {
            errors.append("- Please enter a Lastname.\n");
        }
        else if(etLastName.getText().toString().trim().length() > 25) {
            errors.append("- Lastname cannot exceed 25 characters.\n");
        }

        //validate for mandatory field and length
        if (etAddress.getText().toString().matches("")) {
            errors.append("- Please enter a Address.\n");
        }
        else if(etAddress.getText().toString().trim().length() > 75) {
            errors.append("- Address cannot exceed 75 characters.\n");
        }

        //validate for mandatory field and length
        if (etCity.getText().toString().matches("")) {
            errors.append("- Please enter a City.\n");
        }
        else if(etCity.getText().toString().trim().length() > 50) {
            errors.append("- Lastname cannot exceed 50 characters.\n");
        }

        //validate for mandatory field and regex
        if (etProv.getText().toString().matches(""))  {
            errors.append("- Please enter a Province.\n");
        }
        else if (!(etProv.getText().toString().matches("[a-zA-Z]{2}"))) {
            errors.append("- Please enter a valid Province ex: AB for Alberta\n");
        }

        //validate for mandatory field and regex
        if (etPostal.getText().toString().matches(""))  {
            errors.append("- Please enter a PostalCode.\n");
        }
        else if (!(etPostal.getText().toString().matches("^[A-Za-z]\\d[A-Za-z][ -]?\\d[A-Za-z]\\d$"))) {
            errors.append("- Please enter a valid Postal code of the format x9x9x9\n");
        }

        //validate field for regex
        if (etHomePhone.getText().toString().trim().length() > 0) {
            if (!(etHomePhone.getText().toString().matches("^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$"))) {
                errors.append("- Please enter a valid Home phone number\n");
            }
        }

        //validatefor mandatory field and regex
        if (etBusPhone.getText().toString().matches(""))  {
            errors.append("- Please enter a Business Phone Number.\n");
        }
        else if (!(etBusPhone.getText().toString().matches("^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$"))) {
            errors.append("- Please enter a valid Business phone number\n");
        }

        //validate for mandatory field and regex
        if (etEmail.getText().toString().matches(""))  {
            errors.append("- Please enter an Email address.\n");
        }
        else if (!(etEmail.getText().toString().matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$"))) {
            errors.append("- Please enter a valid email address\n");
        }

        //validate mandatory field and integer
        if (etAgentId.getText().toString().trim().length() == 0) {
            errors.append("- Please enter a valid AgentId\n");
        }
        else if (etAgentId.getText().toString().trim().length() > 0) {
            try {
                int id = (Integer.parseInt(etAgentId.getText().toString()));
            } catch (NumberFormatException e) {
                errors.append("- Agent Id has to be numeric.\n");
            }
            if ((Integer.parseInt(etAgentId.getText().toString())) > 9)
            {
                errors.append("- Agent does not exist.\n");
            }
        }

        //validate for mandatory field and length
        if (etUserName.getText().toString().matches(""))  {
            errors.append("- Please enter a User Name.\n");
        }
        else if(etLastName.getText().toString().trim().length() > 25) {
            errors.append("- Password cannot exceed 25 characters.\n");
        }

        //validate for mandatory field and length
        if (etPassword.getText().toString().matches(""))  {
            errors.append("- Please enter a Password.\n");
        }
        else if(etPassword.getText().toString().trim().length() > 25) {
            errors.append("- Password cannot exceed 25 characters.\n");
        }

        //validate for matching password if re-typed
        if(passUpdate)
        {
            if (etRePassword.getText().toString().matches(""))  {
                errors.append("- Please retype password.\n");
            }
            else if(!etRePassword.getText().toString().matches(etPassword.getText().toString())) {
                errors.append("- Retyped password mismatch.\n");
            }

        }

        //Display Error
        if (errors.length() > 0) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(RegisterActivity.this);
            builder1.setMessage(errors);
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
            return false;
        }
        // No errors
        return true;
    }
}