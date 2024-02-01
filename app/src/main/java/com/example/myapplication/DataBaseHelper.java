package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH = "";
    private static final String DB_NAME = "database.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private SQLiteOpenHelper sqLiteOpenHelper;

    public DataBaseHelper(Context myContext) {
        super(myContext, DB_NAME, null, 1);
        this.myContext = myContext;
        DB_PATH = myContext.getDatabasePath(DB_NAME).toString();

    }
    public void onCreate(SQLiteDatabase db) {
       createDatabase();
    }
    public void createDatabase()
    {
       sqLiteOpenHelper = new DataBaseHelper(myContext);
       SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        boolean check;
        check = checkDataBase();
        if (check) {
           // Toast.makeText(myContext, "Database exist", Toast.LENGTH_SHORT).show();
        } else {
            this.getWritableDatabase();
            try {
                String query = "CREATE TABLE INFLATION (YEAR INTEGER PRIMARY KEY AUTOINCREMENT, FUTURE_COST FLOAT, CURRENT_INV_VALUE FLOAT, ANNUAL_SAVING_REQ FLOAT );";
                db.execSQL(query);
                query = "INSERT INTO INFLATION VALUES (1, 1.06,1.08,1.08),(2,1.12,1.17,2.25),(3,1.19,1.26,3.51),(4,1.26,1.36,4.87),(5,1.34,1.47,6.34),(6,1.42,1.59,7.92),(7,1.50,1.71,9.64),(8,1.59,1.85,11.49),(9,1.69,1.99,13.49),(10,1.79,2.16,15.65),(11,1.90,2.33,17.98),(12,2.01,2.52,20.50),(13,2.13,2.72,23.22),(14,2.26,2.49,26.15),(15,2.40,3.17,29.32),(16,2.54,3.43,32.75),(17,2.69,3.70,36.45),(18,2.85,3.99,40.45),(19,3.03,4.32,44.76),(20,3.21,4.66,49.42),(21,3.40,5.03,54.46),(22,3.60,5.44,59.89),(23,3.82,5.87,65.77),(24,4.05,6.34,72.11),(25,4.29,6.85,78.95),(26,4.55,7.39,86.35),(27,4.82,7.99,94.35),(28,5.11,8.63,102.97),(29,5.42,9.32,112.28),(30,5.74,10.06,122.35)";
                db.execSQL(query);
                db.close();
            } catch (Exception e) {
                //throw new Error("Error copying database");
                Toast.makeText(myContext,e.getMessage(),Toast.LENGTH_LONG).show();

            }
        }
    }
    public boolean doesTableExist( String tableName) {
        try{Cursor cursor = this.getWritableDatabase().rawQuery("SELECT * FROM INFLATION WHERE YEAR = 1", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                //Toast.makeText(myContext,"table exist "+cursor.getCount(), Toast.LENGTH_LONG).show();
                cursor.close();
                return true;
            }
            cursor.close();
        }
        }catch (Exception e)
        {
            Toast.makeText(myContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

            if (checkDB != null && doesTableExist("INFLATION")) {
                checkDB.close();
                return true;
            }
        }catch (SQLiteException e) {

                // database doesn't exist yet.
                Log.e("message", "" + e);
            }
        return false;
    }
    public data fvFactor(int year)
    {
        data d = null;
        try{
        Cursor myCursor = this.getReadableDatabase().rawQuery("SELECT * FROM INFLATION WHERE YEAR ="+year+" ", null);
            if(myCursor !=null) {
                myCursor.moveToFirst();
                d = new data(myCursor.getInt(0),myCursor.getFloat(1),myCursor.getFloat(2),myCursor.getFloat(3));
                myCursor.close();
            }
        }catch (Exception e){
            Toast.makeText(myContext,e.getMessage(), Toast.LENGTH_LONG).show();}
        return d;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS INFLATION");
        onCreate(sqLiteDatabase);
    }
}
