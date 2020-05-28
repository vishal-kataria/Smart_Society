package com.example.smart_society.javaClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

public class DatabaseHelper {
    public static SQLiteDatabase sql;
    public static Tables tables;

    public static Tables getInstance(Context context){
        if(tables == null)
            tables = new Tables(context);
        return tables;
    }
    public static void open(){
        sql=tables.getWritableDatabase();
    }
    public static void close(){
        sql.close();
    }

    public static void add(String name, String flatno, String phone, String pass,String parking){
        ContentValues cv= new ContentValues();
        cv.put(FieldNames.C_NAME,name);
        cv.put(FieldNames.C_FLATNO, flatno);
        cv.put(FieldNames.C_PHONE, phone);
        cv.put(FieldNames.C_PASS, pass);
        cv.put(FieldNames.C_PARKING, parking);
        sql.insertOrThrow(FieldNames.TBL_NAME,null,cv);
    }

    public static boolean checkFlatNo(String value) {
        if (!sql.isOpen())
            open();
        String sql1 = "SELECT * FROM " + FieldNames.TBL_NAME;
        Cursor cursor = sql.rawQuery(sql1, null);
        ArrayList<String> alflat=new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            new ArrayList(Collections.singleton(((cursor.getString(cursor.getColumnIndex(FieldNames.C_FLATNO))))));
            alflat.add((cursor.getString(cursor.getColumnIndex(FieldNames.C_FLATNO))));
            cursor.moveToNext();
        }
        Log.i("value",alflat.toString());
        boolean ans = alflat.contains(value);
        return ans;

    }

    public static ArrayList<Integer> getId(){
        if (!sql.isOpen())
            open();
        String sql1= "SELECT * FROM "+ FieldNames.TBL_NAME;
        Cursor c = sql.rawQuery(sql1, null);
        ArrayList<Integer> ids = new ArrayList<Integer>();
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            ids.add(Integer.parseInt(c.getString(c.getColumnIndex(FieldNames.C_ID))));
            c.moveToNext();
        }
        return ids;

    }

    public static ArrayList<String> getData(){
        if (!sql.isOpen())
            open();
        String sql1= "SELECT * FROM "+ FieldNames.TBL_NAME;
        Cursor c = sql.rawQuery(sql1, null);
        ArrayList<String> names = new ArrayList<String>();
        c.moveToFirst();
        while(!c.isAfterLast()) {
            names.add((c.getString(c.getColumnIndex(FieldNames.C_NAME))));
            c.moveToNext();
        }
        return names;
    }

    public static ArrayList<String> getflatno(){
        if (!sql.isOpen())
            open();
        String sql1= "SELECT * FROM "+ FieldNames.TBL_NAME;
        Cursor c = sql.rawQuery(sql1, null);
        ArrayList<String> names = new ArrayList<String>();
        c.moveToFirst();
        while(!c.isAfterLast()) {
            names.add((c.getString(c.getColumnIndex(FieldNames.C_FLATNO))));
            c.moveToNext();
        }
        return names;
    }

    public static ArrayList<String> getphone(){
        if (!sql.isOpen())
            open();
        String sql1= "SELECT * FROM "+ FieldNames.TBL_NAME;
        Cursor c = sql.rawQuery(sql1, null);
        ArrayList<String> names = new ArrayList<String>();
        c.moveToFirst();
        while(!c.isAfterLast()) {
            names.add((c.getString(c.getColumnIndex(FieldNames.C_PHONE))));
            c.moveToNext();
        }
        return names;
    }

    public static ArrayList<String> getparking(){
        if (!sql.isOpen())
            open();
        String sql1= "SELECT * FROM "+ FieldNames.TBL_NAME;
        Cursor c = sql.rawQuery(sql1, null);
        ArrayList<String> names = new ArrayList<String>();
        c.moveToFirst();
        while(!c.isAfterLast()) {
            names.add((c.getString(c.getColumnIndex(FieldNames.C_PARKING))));
            c.moveToNext();
        }
        return names;
    }

}
