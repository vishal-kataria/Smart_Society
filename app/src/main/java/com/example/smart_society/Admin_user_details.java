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
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_society.javaClass.DatabaseHelper;

import java.util.ArrayList;

public class Admin_user_details extends AppCompatActivity {

    EditText name,phone,flatno,parking;
    Button add1,view1,viewal2,delete1,modify1,btnFetch;
    Spinner spIDs;
    ArrayList<Integer> alSid ;
    ArrayList<String> alname ;
    ArrayList<String> alflat;
    ArrayList<String> alphone;
    ArrayList<String> alparking;
    RelativeLayout lay1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_details);

        DatabaseHelper.getInstance(getApplicationContext());
        DatabaseHelper.open();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Users Edit Panel");
        spIDs = findViewById(R.id.spinner);
        btnFetch = findViewById(R.id.btn1);

        phone=findViewById(R.id.st2);
        name=findViewById(R.id.st1);
        flatno=findViewById(R.id.st3);
        parking=findViewById(R.id.st4);
        lay1=findViewById(R.id.lay1);
        viewal2=findViewById(R.id.viewallbtn1);
        delete1=findViewById(R.id.deletebtn1);
        alSid = new ArrayList<>(DatabaseHelper.getId());
        alname = new ArrayList<>(DatabaseHelper.getData());
        alflat=new ArrayList<>(DatabaseHelper.getflatno());
        alphone=new ArrayList<>(DatabaseHelper.getphone());
        alparking=new ArrayList<>(DatabaseHelper.getparking());
        flatno.setFocusable(false);
        modify1=findViewById(R.id.modifybtn1);

        alflat = DatabaseHelper.getflatno();
        DatabaseHelper.close();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(Admin_user_details.this, android.R.layout.simple_spinner_dropdown_item, alflat);
        spIDs.setAdapter(adapter);

        viewal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper.getInstance(getApplicationContext());
                DatabaseHelper.open();
                Cursor c=DatabaseHelper.sql.rawQuery("SELECT * FROM Registration", null);
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
                    buffer.append("Phone number: "+c.getString(3)+"\n");
                    buffer.append("Parking Vehicle details: "+c.getString(5)+"\n");
                    buffer.append("-------------------------------------------------------------------"+"\n");
                }
                showMessage("Users", buffer.toString());
                DatabaseHelper.close();
            }
        });

        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().trim().length()==0||flatno.getText().toString().trim().length()==0||
                        phone.getText().toString().trim().length()==0)
                {
                    showMessage("Oops!!", "Please select a flatno and click on Fetch,then Delete to delete a Record");
                    return;
                }
                DatabaseHelper.getInstance(getApplicationContext());
                DatabaseHelper.open();
                String i = "";
                i+=alflat.get(spIDs.getSelectedItemPosition());

                Cursor c=DatabaseHelper.sql.rawQuery("SELECT * FROM Registration WHERE s_flatno='"+i+"'", null);

                if(c.moveToFirst())
                {
                    DatabaseHelper.sql.execSQL("DELETE FROM Registration WHERE s_flatno='"+i+"'");
                    showMessage("Success", "Record Deleted");

                }
                alflat.remove(spIDs.getSelectedItemPosition());
                adapter.notifyDataSetChanged();




                clearText();
                DatabaseHelper.close();
            }
        });


        modify1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().trim().length()==0||flatno.getText().toString().trim().length()==0||
                        phone.getText().toString().trim().length()==0)
                {
                    showMessage("Oops!!", "Please select a Flat-no and click on Fetch,then make changes and modify");
                    return;
                }
                DatabaseHelper.getInstance(getApplicationContext());
                DatabaseHelper.open();
                String i = "";
                i+=alflat.get(spIDs.getSelectedItemPosition());
                Cursor c=DatabaseHelper.sql.rawQuery("SELECT * FROM Registration  WHERE s_flatno='"+i+"'", null);
                if(c.moveToFirst())
                {
                    DatabaseHelper.sql.execSQL("UPDATE Registration SET s_name='"+name.getText()+"',s_phone='"+phone.getText()+
                            "',s_parking='"+parking.getText()+"' WHERE s_flatno='"+flatno.getText()+"'");
                    showMessage("Success", "Record Modified");
                }
                else
                {
                    showMessage("Oops!!", "Invalid Batch name");
                }
                clearText();
                DatabaseHelper.close();
            }
        });

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alflat.isEmpty())
                {
                    showMessage("Oops!!","no records found");
                }
                else
                {
                    DatabaseHelper.getInstance(getApplicationContext());
                    DatabaseHelper.open();
                    alname = DatabaseHelper.getData();
                    alflat = DatabaseHelper.getflatno();
                    alphone = DatabaseHelper.getphone();
                    alparking=DatabaseHelper.getparking();
                    String value = "", value1 = "", value2 = "",value3="";
                    value += alname.get(spIDs.getSelectedItemPosition());
                    value1 += alflat.get(spIDs.getSelectedItemPosition());
                    value2 += alphone.get(spIDs.getSelectedItemPosition());
                    value3 += alparking.get(spIDs.getSelectedItemPosition());

                    name.setText(value);
                    flatno.setText(value1);
                    phone.setText(value2);
                    parking.setText(value3);
                        DatabaseHelper.close();
                }
            }
        });
    }
    private void showMessage(String title, String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        name.setText("");
        phone.setText("");
        flatno.setText("");
        parking.setText("");
        name.requestFocus();
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
