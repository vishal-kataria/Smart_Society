package com.example.smart_society;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_society.javaClass.DatabaseHelper;
import com.example.smart_society.javaClass.FieldNames;

import java.util.ArrayList;
import java.util.Collections;

public class Admin_edit_maintenance extends AppCompatActivity {

    EditText name,parking,flatno,maintenamount,penalty,paid,maintenancemonth;
    ToggleButton maintenance;
    Button viewal2,modify1,btnFetch,maintenancebill,btnfetchbill;
    Spinner spIDs,spIDs1;
    ArrayList<String> almonth;
    ArrayList<String> alflat;
    ArrayAdapter<String> adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_maintenance);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Maintenance Bill Records");
        DatabaseHelper.getInstance(getApplicationContext());
        DatabaseHelper.open();
        spIDs = findViewById(R.id.spinner);
        spIDs1 = findViewById(R.id.spinner1);
        btnFetch = findViewById(R.id.btn1);
        btnfetchbill=findViewById(R.id.btn2);
        maintenance=findViewById(R.id.maintenance);
        maintenancebill=findViewById(R.id.maintenancebill);
        maintenamount=findViewById(R.id.st4);
        maintenancemonth=findViewById(R.id.st6);
        paid=findViewById(R.id.st7);
        penalty=findViewById(R.id.st5);
        almonth = new ArrayList<>();
        alflat=new ArrayList<>(DatabaseHelper.getflatno());
        parking=findViewById(R.id.st2);
        name=findViewById(R.id.st1);
        flatno=findViewById(R.id.st3);

        name.setFocusable(false);
        flatno.setFocusable(false);
        parking.setFocusable(false);

        viewal2=(Button)findViewById(R.id.viewallbtn1);
        modify1=(Button)findViewById(R.id.modifybtn1);
        spIDs1.setVisibility(View.GONE);
        btnfetchbill.setVisibility(View.GONE);

        alflat = DatabaseHelper.getflatno();
        DatabaseHelper.close();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(Admin_edit_maintenance.this,
                android.R.layout.simple_spinner_dropdown_item, alflat);
        spIDs.setAdapter(adapter);

        viewal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper.getInstance(getApplicationContext());
                DatabaseHelper.open();
                Cursor c=DatabaseHelper.sql.rawQuery("SELECT * FROM maintenance", null);
                if(c.getCount()==0)
                {
                    showMessage("Oops!!", "No records found");

                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    //  buffer.append("ID: "+c.getString(0)+"\n");
                    buffer.append("Name: "+c.getString(1)+"\n");
                    buffer.append("Flat-no: "+c.getString(2)+"\n");
                    buffer.append("Parking vehicle details: "+c.getString(3)+"\n");
                    buffer.append("Maintenance Amount: "+c.getString(4)+"\n");
                    buffer.append("Maintenance month: "+c.getString(5)+"\n");
                    buffer.append("Penalty: "+c.getString(6)+"\n");
                    buffer.append("Paid: "+c.getString(7)+"\n");
                    buffer.append("-------------------------------------------------------------------"+"\n");

                }
                showMessage("Maintenance Bills", buffer.toString());
                DatabaseHelper.close();
            }
        });

        modify1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(maintenamount.getText().toString().trim().length()==0||maintenancemonth.getText().toString().trim().length()==0||
                        penalty.getText().toString().trim().length()==0||paid.getText().toString().trim().length()==0)
                {
                    showMessage("Oops!!", "Please select a Id and click on Fetch,then select a month to modify the selected Id maintenance Bill");
                    return;
                }
                DatabaseHelper.getInstance(getApplicationContext());
                DatabaseHelper.open();
                String i = "";
                i+=alflat.get(spIDs.getSelectedItemPosition());
                Cursor c=DatabaseHelper.sql.rawQuery("SELECT * FROM maintenance  WHERE s_flatno='"+i+"'", null);
                if(c.moveToFirst())
                {
                    String i2 = "";
                    i2+=almonth.get(spIDs1.getSelectedItemPosition());

                    DatabaseHelper.sql.execSQL("UPDATE maintenance SET s_mainamount='"+maintenamount.getText()+"',s_month='"+maintenancemonth.getText()+
                            "',s_penalty='"+penalty.getText()+"',s_paid='"+paid.getText()+"' WHERE s_month='"+i2+"'");
                    showMessage("Success", "Record Modified");
                }
                else
                {
                    showMessage("Oops!!", "Invalid ");
                }
                clearText();
                DatabaseHelper.close();
            }
        });

        btnfetchbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (almonth.isEmpty()){
                    showMessage("Oops!!","no records found");
                }
                else {
                    DatabaseHelper.getInstance(getApplicationContext());
                    DatabaseHelper.open();
                    String i2 = "";
                    i2 += almonth.get(spIDs1.getSelectedItemPosition());
                    Cursor c = DatabaseHelper.sql.rawQuery("SELECT * FROM maintenance WHERE s_month='" + i2 + "'", null);
                    if (c.moveToFirst()) {


                        maintenamount.setText(c.getString(4));
                        maintenancemonth.setText(c.getString(5));
                        penalty.setText(c.getString(6));
                        paid.setText(c.getString(7));


                    } else {
                        showMessage("Oops!!", "Invalid Batch name");
                        clearText();
                        DatabaseHelper.close();
                    }
                }

            }
        });

        maintenance.setText("paid");
        maintenance.setTextOn(null);
        maintenance.setTextOff(null);
        maintenance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    maintenance.setTextOn("paid");
                    paid.setText("Paid");
                }
                else{
                    maintenance.setTextOff("Unpaid");
                    paid.setText("UnPaid");
                }
            }
        });

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alflat.isEmpty())
                {
                    showMessage("Oops!!","No records Found");

                }
                else
                {
                    spIDs1.setVisibility(View.VISIBLE);
                    btnfetchbill.setVisibility(View.VISIBLE);
                    DatabaseHelper.getInstance(getApplicationContext());
                    DatabaseHelper.open();

                    Cursor cu = DatabaseHelper.sql.rawQuery("SELECT * FROM maintenance WHERE s_flatno='" + alflat.get(spIDs.getSelectedItemPosition()) + "'", null);
                    if (cu.moveToFirst())
                    {
                        name.setText(cu.getString(1));
                        flatno.setText(cu.getString(2));
                        parking.setText(cu.getString(3));
                    }
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(Admin_edit_maintenance.this,
                            android.R.layout.simple_spinner_dropdown_item, almonth);
                    almonth.clear();
                    adapter1.notifyDataSetChanged();

                    Cursor c = DatabaseHelper.sql.rawQuery("SELECT * FROM maintenance WHERE s_flatno='" + alflat.get(spIDs.getSelectedItemPosition()) + "'", null);
                    c.moveToFirst();

                    while (!c.isAfterLast()) {

                        new ArrayList(Collections.singleton(c.getString(c.getColumnIndex(FieldNames.C_MAINTENANCEMONTH))));
                        almonth.add((c.getString(c.getColumnIndex(FieldNames.C_MAINTENANCEMONTH))));
                        c.moveToNext();
                        spIDs1.setAdapter(adapter1);
                    }

                    DatabaseHelper.close();

                }
            }
        });

    }
    private void showMessage(String title, String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        maintenamount.setText("");
        penalty.setText("");
        maintenancemonth.setText("");

        maintenamount.requestFocus();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.adminlogout, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.adminn)
        {
            Intent i = new Intent(getApplicationContext(),Admin_Homepage.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.admin_logout)
        {
            Intent i = new Intent(getApplicationContext(),User_login.class);
            startActivity(i);
            finish();
        }
        else if (id == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
