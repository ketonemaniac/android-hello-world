package com.sqisland.android.hello.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

/**
 * Created by user on 3/13/2017.
 */

public class NewsDatabaseHelper {

    private SQLiteDatabase db;
    public static final String HEADLINE_TABLE = "headlines";

    public NewsDatabaseHelper(Context context) {
        NewsOpenHelper helper = new NewsOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    public void saveHeadline(String headline) {
        ContentValues params = new ContentValues();
        params.put("headline", headline);
        params.put("creationTime", new Date().getTime());
        try {
            db.insert(HEADLINE_TABLE, null, params);
//            db.execSQL("insert into headlines (headline, creationTime) values ('test', datetime('now'))");
        }catch(Exception e) {
            e.printStackTrace();
        }

    }
}
