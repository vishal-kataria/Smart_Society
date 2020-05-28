package com.example.smart_society;

import android.app.AlertDialog;
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

public class Admin_meeting extends AppCompatActivity {

    Button btnarrange,view,delete;
    EditText meetingtxt,meetingtxtname ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_meeting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Arrange Meeting");
        btnarrange=findViewById(R.id.btnarrange);
        meetingtxt=findViewById(R.id.meetingtxt);
        meetingtxtname=findViewById(R.id.meetingtxtname);
        view=findViewById(R.id.view);
        delete=findViewById(R.id.delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper.getInstance(getApplicationContext());
                DatabaseHelper.open();
                Cursor c=DatabaseHelper.sql.rawQuery("SELECT * FROM meeting ", null);
                if(c.getCount()==0)
                {
                    showMessage("Oops!!", "No records found");
                    return;
                }
                if(c.moveToFirst())
                {
                    DatabaseHelper.sql.execSQL("DELETE FROM meeting ");
                    showMessage("Success", "Record Deleted");
                }
                clearText();
                DatabaseHelper.close();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper.getInstance(getApplicationContext());
                DatabaseHelper.open();
                Cursor c=DatabaseHelper.sql.rawQuery("SELECT * FROM meeting", null);
                if(c.getCount()==0)
                {
                    showMessage("Oops!!", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Meeting Name: "+c.getString(0)+"\n");
                    buffer.append("Description: "+c.getString(1)+"\n");
                    buffer.append("-------------------------------------------------------------------"+"\n");
                }
                showMessage("Meetings", buffer.toString());
                DatabaseHelper.close();
            }
        });

        btnarrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(meetingtxtname.getText().toString().trim().length()==0||
                        meetingtxt.getText().toString().trim().length()==0)
                {
                    showMessage("Oops!!", "Please enter all details");
                    return;
                }
                DatabaseHelper.getInstance(getApplicationContext());
                DatabaseHelper.open();
                DatabaseHelper.sql.execSQL("INSERT INTO meeting VALUES('"+meetingtxtname.getText()+"','"+meetingtxt.getText()+ "');");
                showMessage("Success", "Record added successfully");
                clearText();
                DatabaseHelper.close();
            }
        });
    }
    public void clearText()
    {
        meetingtxtname.setText("");
        meetingtxt.setText("");
        meetingtxtname.requestFocus();
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
