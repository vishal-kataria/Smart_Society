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
import com.example.smart_society.javaClass.SharedPreference;

import java.util.ArrayList;

public class User_Maintenance extends AppCompatActivity {

    String name="";
    ListView list;
    ArrayList<String> al;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__maintenance);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Maintenance Bill");
        list=findViewById(R.id.list1);
        if (SharedPreference.contains("Username"))
            name=SharedPreference.get("Username");
        DatabaseHelper.getInstance(getApplicationContext());

        DatabaseHelper.open();
        Cursor c= DatabaseHelper.sql.rawQuery("SELECT * FROM maintenance  WHERE s_name='"+name+"'", null);
        if(c.getCount()==0)
        {
            showMessage("Oops!!", "No records found");

        }
        StringBuffer buffer=new StringBuffer();
        while (c.moveToNext())
        {
            buffer.append("Flat-no: "+c.getString(2)+"\n");
            buffer.append("Maintenance Amount: "+c.getString(4)+"\n");
            buffer.append("Maintenance month: "+c.getString(5)+"\n");
            buffer.append("Penalty: "+c.getString(6)+"\n");
            buffer.append("Paid: "+c.getString(7)+"\n");
            buffer.append("----------------------------------------------------------------------------------"+"\n");

        }
        String i=buffer.toString();
        al=new ArrayList<String>();
        al.add(0,i);
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(User_Maintenance.this,android.R.layout.simple_list_item_1,al);
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
        getMenuInflater().inflate(R.menu.usermenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.usermenuu)
        {
            Intent i = new Intent(getApplicationContext(),User_Homepage.class);
        }
        else if (id == R.id.nav_logout)
        {
            SharedPreference.removeKey("flat");
            SharedPreference.removeKey("Username");
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
