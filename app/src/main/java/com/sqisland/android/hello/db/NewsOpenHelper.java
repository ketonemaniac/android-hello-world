package com.sqisland.android.hello.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 3/13/2017.
 */

public class NewsOpenHelper extends SQLiteOpenHelper {

    public NewsOpenHelper(Context context) {
        super(context, "headlines.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + NewsDatabaseHelper.HEADLINE_TABLE + " ( "
                    + " id integer primary key, "
                    +  "headline text, "
                    + " creationTime datetime )");   // always treat time as int rather than messing with datetime
    }

    /**
     * only called when version number is different
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists headlines");
        onCreate(db);
    }
}
