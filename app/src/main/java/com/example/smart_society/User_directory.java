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
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_society.javaClass.MyAdapter;
import com.example.smart_society.javaClass.SharedPreference;

public class User_directory extends AppCompatActivity {

    ListView lv1;
    String titles[]={"Chairman","Secretary","Treasurer","Liftman","Plumber","Gardener","Watchman",
            "Electrician","Laundry","Mechanics"};
    String description[]={"Oranizing and co-ordinating board activities","Reception,Filing,Office Task","Liquidity," +
            "Investments,Risk Management","Testing lift equipment","Installs pipes and fixing sinks,toilet etc",
            "Maintaining outdoor grounds and gardens","Guard duty by24x7","Inspecting short circuit and transformers",
            "Dry cleaning clothes and household articles","Reparing and servicing of vehicles"};
    int imgs[]={R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image6,R.drawable.image7,R.drawable.image8,R.drawable.image9,R.drawable.image10};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_directory);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Click to Call");

        lv1=findViewById(R.id.lv1);
        MyAdapter myAdapter =new MyAdapter(this,titles,imgs,description);
        lv1.setAdapter(myAdapter);


        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0) {
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
                else if (position==1){
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
                else if (position==2){
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
                        i.setData(Uri.parse("tel:7021728267"));
                        startActivity(i);
                    }


                }
                else if (position==3){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 123);

                        else
                        {
                            Intent i=new Intent(Intent.ACTION_CALL);
                            i.setData(Uri.parse("tel:9324528094"));
                            startActivity(i);
                        }
                    }

                    else
                    {
                        Intent i=new Intent(Intent.ACTION_CALL);
                        i.setData(Uri.parse("tel:9324528094"));
                        startActivity(i);
                    }


                }
                else if (position==4){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 123);

                        else
                        {
                            Intent i=new Intent(Intent.ACTION_CALL);
                            i.setData(Uri.parse("tel:8452816574"));
                            startActivity(i);
                        }
                    }

                    else
                    {
                        Intent i=new Intent(Intent.ACTION_CALL);
                        i.setData(Uri.parse("tel:8452816574"));
                        startActivity(i);
                    }


                }
                else if (position==5){
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
                else if (position==6){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 123);

                        else
                        {
                            Intent i=new Intent(Intent.ACTION_CALL);
                            i.setData(Uri.parse("tel:8452816574"));
                            startActivity(i);
                        }
                    }

                    else
                    {
                        Intent i=new Intent(Intent.ACTION_CALL);
                        i.setData(Uri.parse("tel:8452816574"));
                        startActivity(i);
                    }


                }
                else if (position==7){
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
                        i.setData(Uri.parse("tel:7021728267"));
                        startActivity(i);
                    }


                }
                else if (position==8){
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
                else if (position==9){
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
                        i.setData(Uri.parse("tel:7021728267"));
                        startActivity(i);
                    }


                }
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
