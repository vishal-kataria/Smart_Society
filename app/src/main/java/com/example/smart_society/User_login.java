package com.example.smart_society;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_society.javaClass.DatabaseHelper;
import com.example.smart_society.javaClass.FieldNames;
import com.example.smart_society.javaClass.SharedPreference;

public class User_login extends AppCompatActivity {

    EditText editTextEmail,editTextpassword;
    TextView btnSign;
    Button btnLogin;
    CheckBox chkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_lightbulb_outline_black_24dp);

        //login
        SharedPreferences sp1;
        final SharedPreferences.Editor edit;
        sp1 = getSharedPreferences("APP NAME", MODE_PRIVATE);
        edit = sp1.edit();

        editTextEmail=findViewById(R.id.editTextEmail);
        editTextpassword=findViewById(R.id.editTextpassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnSign=findViewById(R.id.btnSign);
        chkBox=findViewById(R.id.chkBox);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String flatno = editTextEmail.getText().toString().trim();
                String pass = editTextpassword.getText().toString().trim();
                if(flatno.equals("") || pass.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Fill all the fields", Toast.LENGTH_SHORT).show();
                    if(flatno.equals("")) {
                        editTextEmail.setError("Enter a valid flatno");
                    }
                    if(pass.equals("")) {
                        editTextpassword.setError("Enter a password");
                    }
                }
                else
                {
                    DatabaseHelper.getInstance(getApplicationContext());
                    DatabaseHelper.open();
                    Cursor c=DatabaseHelper.sql.rawQuery("SELECT * FROM Registration WHERE s_flatno='"+flatno+"'", null);
                    if(c.moveToFirst())
                    {

                        String a=c.getString(1);
                        SharedPreference.initialize(getApplicationContext());
                        SharedPreference.save("Username",a);

                    }

                    Cursor cursor = DatabaseHelper.sql.rawQuery("SELECT * FROM " + FieldNames.TBL_NAME + " WHERE " + FieldNames.C_FLATNO + "=? AND " + FieldNames.C_PASS + "=?", new String[]{flatno, pass});
                    if (cursor != null) {
                        cursor.moveToNext();
                        if (cursor.getCount() > 0) {
                            Toast.makeText(getApplicationContext(), "Successfully LoggedIn", Toast.LENGTH_SHORT).show();
                            Intent i= new Intent(User_login.this,User_Homepage.class);
                            SharedPreference.initialize(getApplicationContext());
                            SharedPreference.save("flat",flatno);


                            if (chkBox.isChecked())
                            {
                                edit.putString("flatno", flatno);
                                edit.putString("pass", pass);
                                edit.commit();
                            }
                            else {
                                edit.remove("flatno");
                                edit.remove("pass");
                                edit.commit();

                            }
                            startActivity(i);
                            finish();

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Invalid Login Credentials", Toast.LENGTH_SHORT).show();
                            edit.remove("flatno");
                            edit.remove("pass");
                            edit.commit();

                        } }


                    DatabaseHelper.close();

                }
            }
        });
        if (sp1.contains("flatno")) ;
        editTextEmail.setText(sp1.getString("flatno", ""));

        if (sp1.contains("pass")) ;
        editTextpassword.setText(sp1.getString("pass", ""));



        //sign up
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),User_registration.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode)
        {
            case 123:
                if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(getApplicationContext(),"Permission Granted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_LONG).show();
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.adminmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.adminmenu) {
            Intent i=new Intent(User_login.this,Admin_login.class);
            startActivity(i);
            // your code
            return true;
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
