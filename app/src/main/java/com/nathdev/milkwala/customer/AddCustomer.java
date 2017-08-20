package com.nathdev.milkwala.customer;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.nathdev.milkwala.R;
import com.nathdev.milkwala.db.CustDataBaseAdapter;

public class AddCustomer extends Activity {

    Button btnAddCustomer, btnCancel;
    CustDataBaseAdapter custDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_customer);

        System.out.print("Calling database open connection");
        // create a instance of SQLite Database
        custDataBaseAdapter = new CustDataBaseAdapter(this);
        custDataBaseAdapter = custDataBaseAdapter.open();

    }
    public void addCustomer(View V) {
        final Dialog dialog = new Dialog(AddCustomer.this);
        dialog.setContentView(R.layout.add_customer);
        dialog.setTitle("Add Customer");

        // Get The Reference Of Buttons
        btnAddCustomer = (Button) findViewById(R.id.btnAddCustomer);
        btnCancel = (Button) findViewById(R.id.btnCancelCustomer);


        btnAddCustomer.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                final EditText customerName = (EditText)findViewById(R.id.custName);
                String customerNameStr = customerName.getText().toString();
                final EditText email = (EditText)findViewById(R.id.custEmail);
                String emaileStr = email.getText().toString();
                final EditText phone = (EditText)findViewById(R.id.custphone);
                String phoneStr = phone.getText().toString();
                final EditText address = (EditText)findViewById(R.id.custAddress);
                String addressStr = address.getText().toString();
                final EditText milkqty = (EditText) findViewById(R.id.custmilkqty);
                String milkqtyStr = milkqty.getText().toString();

                // Insert Data into Database
              Integer custID=custDataBaseAdapter.insertEntry(customerNameStr, emaileStr,phoneStr,addressStr,milkqtyStr);



            }
        });
    }







}
