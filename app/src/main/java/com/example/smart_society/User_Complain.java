package com.example.smart_society;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_society.javaClass.DatabaseHelper;
import com.example.smart_society.javaClass.SharedPreference;

public class User_Complain extends AppCompatActivity {

    EditText complain;
    Button btnSubmit;
    String flatno="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__complain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Complains ");


        complain = findViewById(R.id.complain);
        btnSubmit = findViewById(R.id.btnSubmit);

        try {
            if (SharedPreference.contains("flat"))
                flatno=SharedPreference.get("flat");

        }catch (Exception e){
            e.printStackTrace();
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(complain.getText().toString().isEmpty())
                {
                    showMessage("Oops!!","Please enter a complain");
                    return;
                }
                else
                {
                    DatabaseHelper.getInstance(getApplicationContext());
                    DatabaseHelper.open();
                    DatabaseHelper.sql.execSQL("Insert into complaint values ('"+flatno+"','"+complain.getText().toString()+"');");
                    showMessage("Success", "Complain Submitted");
                    clearText();
                    DatabaseHelper.close();
                }
            }
        });

    }
    private void clearText(){
        complain.setText("");
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
