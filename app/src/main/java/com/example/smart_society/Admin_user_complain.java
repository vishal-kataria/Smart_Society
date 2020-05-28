package com.example.smart_society;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_society.javaClass.DatabaseHelper;

import java.util.ArrayList;

public class Admin_user_complain extends AppCompatActivity {

    ListView list;
    ArrayList<String> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_complain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("User Complaints");
        list=findViewById(R.id.list2);

        DatabaseHelper.getInstance(getApplicationContext());
        DatabaseHelper.open();
        Cursor c= DatabaseHelper.sql.rawQuery("SELECT * FROM complaint", null);
        if(c.getCount()==0)
        {
            showMessage("Oops!!", "No Complaints");
        }
        StringBuffer buffer=new StringBuffer();
        while (c.moveToNext())
        {
            buffer.append("Flat-no: "+c.getString(0)+"\n");
            buffer.append("Complaint: "+c.getString(1)+"\n");
            buffer.append("----------------------------------------------------------------------------------"+"\n");
        }
        String i=buffer.toString();

        al=new ArrayList<>();
        al.add(0,i);
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(Admin_user_complain.this,android.R.layout.simple_list_item_1,al);
        list.setAdapter(adapter);
        DatabaseHelper.close();

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
