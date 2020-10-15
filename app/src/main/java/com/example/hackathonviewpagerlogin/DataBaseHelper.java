package com.example.hackathonviewpagerlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String databaseName="player.db";
    public static final String tableName="doctor_paitent_info";
    public static final String ID="_id";
    public static final String doctorName="doctor_name";
    public static final String doctorDepartment="doctor_department";
    public static final String patientName="patient_name";
    public static final String patientPhone="patient_phone";
    public static final String patientAge="patient_age";
    public static final String patientLocation="patient_location";
    public static final String patientProblem="patient_problem";
    public static final String createTableQuery=" CREATE TABLE  "+tableName+" ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "" +doctorName+" VARCHAR(30), "+doctorDepartment+" VARCHAR(30), "+patientName+" VARCHAR(20), "+patientPhone+" VARCHAR(20)," +
            " " +patientAge+" VARCHAR(3)," +patientLocation+" VARCHAR(30),"+patientProblem+" VARCHAR(200) ); ";
    public static final String upgradeTableQuery = "DROP TABLE IF EXISTS "+tableName;
    public static final String displayTableQuery = "SELECT * FROM "+tableName;
    public static final int versionNo=1;
    private Context context;

    public DataBaseHelper(@Nullable Context context) {
        super(context, databaseName, null, versionNo);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(createTableQuery);

        }catch (Exception e){
            Toast.makeText(context, "Table is not created", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL(upgradeTableQuery);
            onCreate(db);
            Toast.makeText(context, "table is upgraded", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "table is upgraded", Toast.LENGTH_SHORT).show();
        }

    }

    public long insert(String dName, String dDepartment, String pName,String pPhone,
                       String pAge,String pLocation,String pProblem ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(doctorName,dName);
        contentValues.put(doctorDepartment,dDepartment);
        contentValues.put(patientName,pName);
        contentValues.put(patientPhone,pPhone);
        contentValues.put(patientAge,pAge);
        contentValues.put(patientLocation,pLocation);
        contentValues.put(patientProblem,pProblem);

        long value = sqLiteDatabase.insert(tableName,null,contentValues);
        return value;

    }
    public Cursor show(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(displayTableQuery,null);
        return cursor;
    }
    public int delete(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int value = sqLiteDatabase.delete(tableName,ID+" =?",new String[]{id});
        return value;
    }
    public long update(String id,String name, String type, String code){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long value = sqLiteDatabase.update(tableName,contentValues,ID+" =?",new String[]{id});
        return value;
    }
}
