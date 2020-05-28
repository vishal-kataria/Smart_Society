package com.example.smart_society;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_login extends AppCompatActivity {

    EditText editTextEmailadmin,editTextpasswordadmin;
    Button btnLoginadmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_lightbulb_outline_black_24dp);

        editTextEmailadmin=findViewById(R.id.editTextEmailadmin);
        editTextpasswordadmin=findViewById(R.id.editTextpasswordadmin);
        btnLoginadmin=findViewById(R.id.btnLoginadmin);

        btnLoginadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editTextEmailadmin.getText().toString().trim();
                String pass=editTextpasswordadmin.getText().toString().trim();
                if (email.equals("")||pass.equals("")){
                    Toast.makeText(Admin_login.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                    if (email.equals("")){
                        editTextEmailadmin.setError("Enter a valid Admin ID");
                    }
                    if (pass.equals("")){
                        editTextpasswordadmin.setError("Enter a valid Admin Password");
                    }
                }else{
                    if (email.equals("admin")&&pass.equals("123")){
                        Intent i=new Intent(Admin_login.this,Admin_Homepage.class);
                        startActivity(i);
                        finish();
                    }
                    else{
                        Toast.makeText(Admin_login.this, "Invalid Login Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.adminlogin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            onBackPressed();
        }
        if (id==R.id.secretary1){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 123);

                else
                {
                    Intent i=new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:7021728267"));
                    startActivity(i);
                }
            }

            else
            {
                Intent i=new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:8879474951"));
                startActivity(i);
            }


        }
        if (id==R.id.secretary2){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 123);

                else
                {
                    Intent i=new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:8080899456"));
                    startActivity(i);
                }
            }

            else
            {
                Intent i=new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:8080899456"));
                startActivity(i);
            }



        }
        if (id==R.id.secretary3){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 123);

                else
                {
                    Intent i=new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:7303342255"));
                    startActivity(i);
                }
            }

            else
            {
                Intent i=new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:7303342255"));
                startActivity(i);
            }




        }

        return super.onOptionsItemSelected(item);
    }
}
