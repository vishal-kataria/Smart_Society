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

public class User_Notice extends AppCompatActivity {

    ListView list;
    ArrayList<String> al;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__notice);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Notices ");
        list=findViewById(R.id.list123);

        DatabaseHelper.getInstance(getApplicationContext());
        DatabaseHelper.open();
        Cursor c;
        c= DatabaseHelper.sql.rawQuery("SELECT * FROM notice", null);
        if(c.getCount()==0)
        {
            showMessage("Oops!!", "No Notices");
        }
        StringBuffer buffer=new StringBuffer();
        while (c.moveToNext())
        {
            buffer.append("Header : "+c.getString(0)+"\n");
            buffer.append("Subject : "+c.getString(1)+"\n");
            buffer.append("Details : "+c.getString(2)+"\n");
            buffer.append("Sign by : "+c.getString(3)+"\n");
            buffer.append("----------------------------------------------------------------------------------"+"\n");
        }
        String i=buffer.toString();
        al=new ArrayList<>();
        al.add(0,i);
        final ArrayAdapter<String> adapter=new ArrayAdapter<>(User_Notice.this,android.R.layout.simple_list_item_1,al);

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
