package com.lis.sql.fitnessrec;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/12/4.
 */

public class FitnessDataBase extends SQLiteOpenHelper {

//    private static String db_path = "/data/data/com.lis.sql.fitnessrec/databases/";
    private static String db_path = null;
    private static final String db_name = "FitnessRecorder.db";
    private static final int db_version = 1;

    private final Context myContext;

    public FitnessDataBase(Context context) {
        super(context, db_name, null, db_version);
        this.myContext = context;
        this.db_path=context.getFilesDir().getPath()+"/databases/";
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        boolean dbExist = checkDataBase();
//        boolean dbExist = false;
//        if (!dbExist) {
//            this.getReadableDatabase();
//            try {
//                copyDataBase();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//            String sql = "create table ActionType(_id integer primary key autoincrement, ActionName ,Part)";
//            sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public SQLiteDatabase openDataBase() throws IOException {
        String outFileName = db_path + db_name;

        if (!(new File(outFileName).exists())) {
            InputStream myInput = myContext.getAssets().open(db_name);
            OutputStream myOutput = new FileOutputStream(outFileName);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
        }
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(db_path + db_name, null);
        return db;
    }

    public void creatDadaBase() {
        boolean dbExist = checkDataBase();
        if (!dbExist) {
//            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;
        String myPath = db_path + db_name;

        try {
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(db_name);

        // Path to the just created empty db
        String outFileName = db_path + db_name;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
        System.out.println("Copy OK !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

    }
}
