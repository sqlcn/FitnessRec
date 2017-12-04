package com.lis.sql.fitnessrec;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.lis.sql.fitnessrec", appContext.getPackageName());
        System.out.println("Test Android");
        FitnessDataBase mydb = new FitnessDataBase(appContext);
//        mydb.creatDadaBase();
//        mydb.getReadableDatabase();

        SQLiteDatabase db = mydb.openDataBase();
        System.out.println(db);
    }
}
