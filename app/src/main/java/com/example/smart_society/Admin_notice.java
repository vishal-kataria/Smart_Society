package com.example.smart_society;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_society.javaClass.DatabaseHelper;
import com.example.smart_society.javaClass.FieldNames;

public class Admin_notice extends AppCompatActivity {

    EditText header,subject,details,sign;
    Button add,delete,all;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notice);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Notice");
        header = findViewById(R.id.header);
        subject = findViewById(R.id.subject);
        details = findViewById(R.id.details);
        sign = findViewById(R.id.sign);

        add = findViewById(R.id.btnSubNot);
        delete = findViewById(R.id.deletebtn1);
        all = findViewById(R.id.viewallbtn2);

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper.getInstance(getApplicationContext());
                DatabaseHelper.open();
                Cursor c=DatabaseHelper.sql.rawQuery("SELECT * FROM notice", null);
                if(c.getCount()==0)
                {
                    showMessage("Oops!!", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Header : "+c.getString(0)+"\n");
                    buffer.append("Subject : "+c.getString(1)+"\n");
                    buffer.append("Details : "+c.getString(2)+"\n");
                    buffer.append("Sign by : "+c.getString(3)+"\n");
                    buffer.append("-----------------------------------------------------------"+"\n");
                }
                showMessage("Meetings", buffer.toString());
                DatabaseHelper.close();
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(header.getText().toString().isEmpty() || subject.getText().toString().isEmpty() ||
                        details.getText().toString().isEmpty() || sign.getText().toString().isEmpty())
                {
                    showMessage("Oops!!", "enter all details");
                }
                else
                {
                    DatabaseHelper.getInstance(getApplicationContext());
                    DatabaseHelper.open();
                    ContentValues cv= new ContentValues();
                    cv.put(FieldNames.C_HEADER,header.getText().toString());
                    cv.put(FieldNames.C_SUBJECT, subject.getText().toString());
                    cv.put(FieldNames.C_DETAILS, details.getText().toString());
                    cv.put(FieldNames.C_SIGN, sign.getText().toString());
                    DatabaseHelper.sql.insert(FieldNames.TBL_NOTICE,null,cv);
                    showMessage("Success", "Record added successfully");
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
