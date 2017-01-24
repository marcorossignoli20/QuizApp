package com.example.marco.quiz;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Marco on 23/01/2017.
 */

public class DBManager extends SQLiteOpenHelper{
    private static final String DB_NAME = "quizDB";
    private final Context myContext;
    private SQLiteDatabase myDataBase;

    public DBManager(Context context){
        super(context,DB_NAME,null,1);
        this.myContext = context;
    }

    public void createDataBase() throws IOException{
        boolean dbExist = checkDataBase();

        if(!dbExist){
            this.getReadableDatabase();
            try{
                copyDataBase();
            } catch (IOException e){
                throw new Error("Error copying database");
            }
        }
    }

    public boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
            String myPath = myContext.getDatabasePath(DB_NAME).toString();
        try {
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch(SQLException s){

        }
        if(checkDB != null){
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    public void copyDataBase() throws IOException{
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = myContext.getDatabasePath(DB_NAME).toString();
        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while((length = myInput.read(buffer)) > 0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDatabase() throws SQLException{
        String myPath = myContext.getDatabasePath(DB_NAME).toString();
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    public synchronized void close(){
        if(myDataBase != null){
            myDataBase.close();
        }
        super.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void onCreate(SQLiteDatabase db) {

    }

    public ArrayList getAllQuestions(String topic){
        ArrayList questionsObj = new ArrayList();
        openDatabase();
        Cursor c = myDataBase.rawQuery("select * from android_metadata where quiz ='" + topic + "'", null);
        if(c.getCount() > 0){
            c.moveToFirst();
            do{
                questionsObj.add(new QuizClass((c.getString(c.getColumnIndex("question"))),(c.getString(c.getColumnIndex("answer")))));
            } while (c.moveToNext());
            c.close();
        }
        return questionsObj;

    }
}
