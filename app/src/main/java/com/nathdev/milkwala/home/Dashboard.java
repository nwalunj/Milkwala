package com.nathdev.milkwala.home;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.nathdev.milkwala.R;
import com.nathdev.milkwala.customer.AddCustomer;
import com.nathdev.milkwala.db.CustDataBaseAdapter;

/**
 * Created by navanath_walunj on 8/19/2017.
 */

public class Dashboard extends Activity {

    ImageView imgAddCustomer, btnCancel;
    CustDataBaseAdapter custDataBaseAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);


        imgAddCustomer = (ImageView) findViewById(R.id.milkdailyIcon);

        imgAddCustomer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  and Start The Activity
                Intent intentAddCustomer = new Intent(getApplicationContext(), AddCustomer.class);
                startActivity(intentAddCustomer);
            }

        });
    }

    /*public void addCustomer(View V) {
        final Dialog dialog = new Dialog(Dashboard.this);
        dialog.setContentView(R.layout.add_customer);
        dialog.setTitle("Add Customer");

        Button btnAddCustomer= (Button)findViewById(R.id.btnAddCustomer);
        // create a instance of SQLite Database
        custDataBaseAdapter = new CustDataBaseAdapter(this);
        custDataBaseAdapter = custDataBaseAdapter.open();

        // Get The Reference Of Buttons
        btnAddCustomer = (Button)dialog.findViewById(R.id.btnAddCustomer);
        //btnCancel = (Button) findViewById(R.id.btnCancelCustomer);

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
                final EditText milkqty = (EditText)findViewById(R.id.custmilkqty);
                String milkqtyStr = milkqty.getText().toString();

                // Insert Data into Database
                Integer custID=custDataBaseAdapter.insertEntry(customerNameStr, emaileStr,phoneStr,addressStr,milkqtyStr);



            }
        });
    }*/
}
