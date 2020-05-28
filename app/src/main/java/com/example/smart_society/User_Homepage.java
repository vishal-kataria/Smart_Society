package com.example.smart_society;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_society.javaClass.DatabaseHelper;
import com.example.smart_society.javaClass.SharedPreference;

public class User_Homepage extends AppCompatActivity {
    Button btndirectory,btnnotice,btnmeeting,btnbil,btncomplaint;
    TextView textView;
    TextView textCartItemCount;
    int mCartItemCount = 0;
    String name="";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__homepage);
        DatabaseHelper.getInstance(getApplicationContext());
        DatabaseHelper.open();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Home");
        btndirectory=findViewById(R.id.d1);
        btnnotice=findViewById(R.id.p1);
        btnmeeting=findViewById(R.id.s1);
        btnbil=findViewById(R.id.m1);
        btncomplaint=findViewById(R.id.s2);

        try {
            if (SharedPreference.contains("Username"))
                name=SharedPreference.get("Username");
            textView.setText((SharedPreference.get("Username")));
        }catch (Exception e) {
            e.printStackTrace();
        }

        btncomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(User_Homepage.this,User_Complain.class);
                startActivity(i);
            }
        });

        btnnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(User_Homepage.this,User_Notice.class);
                startActivity(i);
            }
        });


        btnbil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(User_Homepage.this,User_Maintenance.class);
                startActivity(i);
            }
        });

        btndirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(User_Homepage.this,User_directory.class);
                startActivity(i);
            }
        });

        btnmeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHelper.getInstance(getApplicationContext());
                DatabaseHelper.open();
                Cursor c=DatabaseHelper.sql.rawQuery("SELECT * FROM meeting", null);
                if(c.getCount()==0)
                {
                    showMessage("Oops!!", "No meetings");
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
            //Intent i = new Intent(getApplicationContext(),User_Homepage.class);
        }
        else if (id == R.id.nav_logout)
        {
            SharedPreference.removeKey("flat");
            SharedPreference.removeKey("Username");
            Intent i = new Intent(User_Homepage.this,User_login.class);
            startActivity(i);
            finish();
        }
        else if (id == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    private void showMessage(String title, String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
