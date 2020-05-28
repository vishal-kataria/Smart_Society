package com.example.smart_society.javaClass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Tables extends SQLiteOpenHelper {

    String create_table = "create table " + FieldNames.TBL_NAME + "(" + FieldNames.C_ID + " integer primary key autoincrement, " +
            FieldNames.C_NAME + " text not null, " + FieldNames.C_FLATNO + " text not null, " + FieldNames.C_PHONE + " text not null, " +
            FieldNames.C_PASS + " text not null,"+ FieldNames.C_PARKING + " text not null);";

    String create_table1 = "create table " + FieldNames.TBL_MAINTENANCE + "(" + FieldNames.C_ID + " text not null, " +
            FieldNames.C_NAME + " text not null, " + FieldNames.C_FLATNO + " text not null, " + FieldNames.C_PHONE + " text not null, "
            + FieldNames.C_MAINTENANCEAMOUNT + " text not null,"+ FieldNames.C_MAINTENANCEMONTH+ " text not null,"+
            FieldNames.C_PENALTY+ " text not null,"+ FieldNames.C_PAID + " text not null);";

    String create_table2="create table " + FieldNames.TBL_MEETING + "(" + FieldNames.C_MEETINGNAME + "text not null, "
            + FieldNames.C_MEETINGS + " text not null);";

    String create_table3="create table " + FieldNames.TBL_NOTICE + "(" + FieldNames.C_HEADER + " text not null , " + FieldNames.C_SUBJECT +
            " text not null,"+ FieldNames.C_DETAILS +" text not null, "+ FieldNames.C_SIGN +" text not null);";

    String create_table4="create table " + FieldNames.TBL_COMPLAINT + "( " + FieldNames.C_COMPLAINTFLAT + " text not null, "
            + FieldNames.C_COMPLAINT + " text not null);";


    public Tables(Context context) {
        super(context,FieldNames.DB_NAME,null,FieldNames.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_table);
        sqLiteDatabase.execSQL(create_table1);
        sqLiteDatabase.execSQL(create_table2);
        sqLiteDatabase.execSQL(create_table3);
        sqLiteDatabase.execSQL(create_table4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
