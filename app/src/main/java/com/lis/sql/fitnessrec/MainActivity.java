package com.lis.sql.fitnessrec;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FitnessDataBase mydb = new FitnessDataBase(getBaseContext());
        try {
            SQLiteDatabase db = mydb.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
