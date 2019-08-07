package com.haiph.assignmentandroidnw.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseSQL extends SQLiteOpenHelper {
public static final String DATABASE_NAME= "FavoriteFragment";
public static final int VERSION=  1;
    public DatabaseSQL (Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(FavoriteDAO.CREATE_DATABASE_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + FavoriteDAO.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
