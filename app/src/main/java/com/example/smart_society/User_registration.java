package com.example.smart_society;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_society.javaClass.DatabaseHelper;

import java.util.Calendar;
import java.util.regex.Pattern;
public class User_registration extends AppCompatActivity {

    EditText editTextDatepicker,editTextNameReg,editTextPhoneReg,editTextFlatnoReg,editTextPasswordReg,editTextconfpassReg;
    Button buttonSignupReg;
    int mYear, mMonth, mDay;
    DatePickerDialog mDatePicker;
    String name, flatno,pass,confpass, phone,securitydate="";
    String parking="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Registration");
        editTextDatepicker=findViewById(R.id.edittextdatepick);
        editTextNameReg=findViewById(R.id.editTextNameReg);
        editTextFlatnoReg=findViewById(R.id.editTextFlatnoReg);
        editTextPhoneReg=findViewById(R.id.editTextPhoneReg);
        editTextconfpassReg=findViewById(R.id.editTextconfpassReg);
        editTextPasswordReg=findViewById(R.id.editTextPasswordReg);
        buttonSignupReg=findViewById(R.id.buttonSignupReg);
        editTextDatepicker.setFocusable(false);
        editTextDatepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentDate=Calendar.getInstance();
                mYear=mcurrentDate.get(Calendar.YEAR);
                mMonth=mcurrentDate.get(Calendar.MONTH);
                mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                mDatePicker=new DatePickerDialog(User_registration.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        String year1 = String.valueOf(selectedyear);
                        String month1 = String.valueOf(selectedmonth + 1);
                        String day1 = String.valueOf(selectedday);
                        editTextDatepicker.setText(day1 + "/" + month1 + "/" + year1);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();  }
        });
        buttonSignupReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }
    private void register() {
        name = editTextNameReg.getText().toString().trim();
        flatno = editTextFlatnoReg.getText().toString().trim();
        phone =editTextPhoneReg.getText().toString().trim();
        pass = editTextPasswordReg.getText().toString().trim();
        confpass = editTextconfpassReg.getText().toString().trim();
        securitydate=editTextDatepicker.getText().toString().trim();
        if (!validate()){
            Toast.makeText(getApplicationContext(), "Signup has failed", Toast.LENGTH_SHORT).show();
        }
        else{
            onSignupSuccess();
        }

    }
    private boolean validate()
    {
        boolean valid=true;

        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*+=?-]).{8,12}$";
        String flat="^(?=.*[0-9])(?=.*[A-Z])(?=.*[-]).{5,5}$";

        if(name.isEmpty()||name.length()>32){
            editTextNameReg.setError("Enter valid name");
            valid=false;
        }
        else if((phone.isEmpty())){
            editTextPhoneReg.setError("Enter a valid Mobile No");
            valid=false;
        }
        else if ((phone.length()!=10 )){
            editTextPhoneReg.setError("Enter a valid Mobile No");
            valid=false;
        }
        else if(!Pattern.matches("^[7-9][0-9]{9}$+",phone)){
            editTextPhoneReg.setError("Enter a valid Mobile No");
            valid=false;
        }
        else if ((flatno.isEmpty()||flatno.length()!=5) ){
            editTextFlatnoReg.setError("Enter valid flatno,Ex:A-001");
            valid=false;
        }
        else if ( (checkUser(flatno))){
            editTextFlatnoReg.setError("Flatno is already registered");
            valid=false;
        }
        else if (!Pattern.matches(flat,flatno)){
            editTextFlatnoReg.setError("Enter valid flatno,Ex:A-001");
            valid=false;
        }
        else if(pass.isEmpty()||pass.length() <8 || pass.length()>12){
            editTextPasswordReg.setError("Enter atleast 8 or maximum 12 character password");
            valid=false;
        }
        else if (!Pattern.matches(pattern,pass)){
            editTextPasswordReg.setError("Password Should contain a digit & lowercase letter[a-z] & special character[!@#$%^&*+=?-] ");
            valid=false;
        }
        else if (!confpass.equals(pass)){
            editTextconfpassReg.setError("Password not matched");
            valid=false;
        }
        else if (securitydate.isEmpty()){
            editTextDatepicker.setError("Need a Answer");
            showmessage("Error","Please Contact admin from above call symbol , for correct Answer");
            valid=false;
        }
        else if (!securitydate.equals("13/12/1997")){
            showmessage("Error","Please Contact admin from above call symbol , for correct Answer");
            valid=false;
        }
        return valid;
    }
    private void showmessage(String title,String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    private boolean checkUser(String flatno)
    {
        DatabaseHelper.getInstance(this);
        DatabaseHelper.open();
        boolean a = DatabaseHelper.checkFlatNo(flatno);
        DatabaseHelper.close();
        Log.i("value of ", String.valueOf(a));
        if(a) return true;
        else return false;
    }

    private void onSignupSuccess(){
        DatabaseHelper.getInstance(this);
        DatabaseHelper.open();
        DatabaseHelper.add(name,flatno,phone,pass,parking);
        Toast.makeText(getApplicationContext(),"Records successfully inserted",Toast.LENGTH_SHORT).show();
        DatabaseHelper.close();
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.registrationmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.secretary1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 123);

                else {
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:7021728267"));
                    startActivity(i);
                }
            } else {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:8879474951"));
                startActivity(i);
            }


        }
        if (id == R.id.secretary2) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 123);

                else {
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:8080899456"));
                    startActivity(i);
                }
            } else {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:8080899456"));
                startActivity(i);
            }


        }
        if (id == R.id.secretary3) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 123);

                else {
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:7303342255"));
                    startActivity(i);
                }
            } else {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:7303342255"));
                startActivity(i);
            }
        }
        if(id == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
