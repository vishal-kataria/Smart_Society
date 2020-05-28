package com.example.smart_society;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_society.javaClass.DatabaseHelper;

import java.util.ArrayList;

public class Admin_Maintenance extends AppCompatActivity {

    EditText name,parking,flatno,maintenamount,penalty,paid,maintenancemonth;
    Button viewal2,modify1,btnFetch,maintenancebill;
    Spinner spIDs;
    ArrayList<Integer> alSid;
    ArrayList<String> alname,alflat,alphone,alparking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__maintenance);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Maintenance Bill ");

        Log.i("called","calleddddd");
        spIDs = findViewById(R.id.spinner1);
        btnFetch = findViewById(R.id.btn1);

        maintenancebill=findViewById(R.id.maintenancebill);
        maintenamount=findViewById(R.id.st4);
        maintenancemonth=findViewById(R.id.st6);
        paid=findViewById(R.id.st7);
        penalty=findViewById(R.id.st5);
        parking=findViewById(R.id.st2);
        name=findViewById(R.id.st1);
        flatno=findViewById(R.id.st3);
        name.setFocusable(false);
        flatno.setFocusable(false);
        parking.setFocusable(false);
        paid.setFocusable(false);
        paid.setVisibility(View.GONE);
        viewal2=findViewById(R.id.viewallbtn1);
        modify1=findViewById(R.id.modifybtn1);
        alflat = DatabaseHelper.getflatno();

        alflat = DatabaseHelper.getflatno();
        DatabaseHelper.close();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(Admin_Maintenance.this,
                android.R.layout.simple_spinner_dropdown_item, alflat);
        spIDs.setAdapter(adapter);
        alSid = new ArrayList<>(DatabaseHelper.getId());
        alname = new ArrayList<>(DatabaseHelper.getData());
        alflat=new ArrayList<>(DatabaseHelper.getflatno());
        alphone=new ArrayList<>(DatabaseHelper.getphone());
        alparking=new ArrayList<>(DatabaseHelper.getparking());

        modify1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Admin_Maintenance.this,Admin_edit_maintenance.class);
                startActivity(i);

            }
        });

        maintenancebill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(maintenamount.getText().toString().trim().length()==0||
                        maintenancemonth.getText().toString().trim().length()==0)
                {
                    showMessage("Oops!!", "Please select a flatno and click on Fetch,to make a Maintenance bill");
                    return;
                }
                String i = "";
                i+=alflat.get(spIDs.getSelectedItemPosition());
                DatabaseHelper.getInstance(getApplicationContext());
                DatabaseHelper.open();
                DatabaseHelper.sql.execSQL("INSERT INTO maintenance VALUES('"+i+"','"+name.getText()+"','"+flatno.getText()+
                        "','"+parking.getText()+"','"+maintenamount.getText()+"','"+maintenancemonth.getText()+"','"+penalty.getText()+"','"+paid.getText()+"');");
                showMessage("Success", "Record added successfully");
                clearText();
                DatabaseHelper.close();
            }
        });

        viewal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper.getInstance(getApplicationContext());
                DatabaseHelper.open();
                Cursor c=DatabaseHelper.sql.rawQuery("SELECT * FROM maintenance", null);
                if(c.getCount()==0)
                {
                    showMessage("Oops!!", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
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
            }
        });

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alflat.isEmpty())
                {
                    showMessage("Oops!!", "Please select a flatno and click on Fetch,to make a Maintenance bill");
                    return;
                }
                DatabaseHelper.getInstance(getApplicationContext());
                DatabaseHelper.open();
                alname = DatabaseHelper.getData();
                alflat = DatabaseHelper.getflatno();
                alparking = DatabaseHelper.getparking();
                name.setText(alname.get(spIDs.getSelectedItemPosition()));
                flatno.setText(alflat.get(spIDs.getSelectedItemPosition()));
                parking.setText(alparking.get(spIDs.getSelectedItemPosition()));
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
