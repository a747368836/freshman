package com.mredrock.cyxbs.freshman.EssentialToRegister;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EssentialSQLiteHelper extends SQLiteOpenHelper {

    public static final String ESSENTIALS = "create table essentials ("

            +"posi integer primary key autoincrement, "
            + "id integer, "
            + "name text,  "
            + "content text, "
            + "property integer)";

    public EssentialSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public EssentialSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ESSENTIALS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
