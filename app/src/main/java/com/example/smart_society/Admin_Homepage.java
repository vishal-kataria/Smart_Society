package com.example.smart_society;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_society.javaClass.DatabaseHelper;

public class Admin_Homepage extends AppCompatActivity {

    Button btnusers,btnnotice,btnmeeting,btnmain,btncomplaint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__homepage);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DatabaseHelper.getInstance(getApplicationContext());
        DatabaseHelper.open();
        getSupportActionBar().setTitle("Home");
        btnusers=findViewById(R.id.d1);
        btnnotice=findViewById(R.id.p1);
        btnmeeting=findViewById(R.id.s1);
        btnmain=findViewById(R.id.m1);
        btncomplaint=findViewById(R.id.s2);

        btnusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Admin_Homepage.this,Admin_user_details.class);
                startActivity(i);
            }
        });

        btnnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Admin_Homepage.this,Admin_notice.class);
                startActivity(i);
            }
        });

        btnmeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Admin_Homepage.this,Admin_meeting.class);
                startActivity(i);
            }
        });

        btnmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Admin_Homepage.this,Admin_Maintenance.class);
                startActivity(i);
            }
        });

        btncomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Admin_Homepage.this, Admin_user_complain.class);
                startActivity(i);
            }
        });
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
