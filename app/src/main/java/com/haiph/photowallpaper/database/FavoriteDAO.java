package com.haiph.photowallpaper.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.haiph.photowallpaper.model.Favorite;

import java.util.ArrayList;
import java.util.List;

public class FavoriteDAO {
    private SQLiteDatabase db;
    public DatabaseSQL databaseSQL;
    public static final String TABLE_NAME = "Favorite";
    public static final String COLUMN_URL = "url";

    public static final String CREATE_DATABASE_FAVORITE = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_URL +
            " TEXT PRIMARY KEY);";
    public static final String TAG = "FavoriteDAO";

    public FavoriteDAO(Context context) {
        databaseSQL = new DatabaseSQL(context);
        db = databaseSQL.getWritableDatabase();
    }

    public int insertURL(Favorite favorite) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_URL, favorite.getUrl());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return 1;


    }

    public int updateURL(String url) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_URL, COLUMN_URL);
        int result = db.update(TABLE_NAME, values, COLUMN_URL+"=?", new String[]{COLUMN_URL});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
    public int deleteURL(String url) {
        int result = db.delete(TABLE_NAME, url+"=?", new String[]{url});
        if (result == 0)
            return -1;
        return 1;
    }

    public List<Favorite> getAllFavorite() {
        List<Favorite> listFavorite = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Favorite f = new Favorite();
            f.setUrl(c.getString(0));
            listFavorite.add(f);
            c.moveToNext();
            Log.e("======", f.toString());
        }
        c.close();
        return listFavorite;
    }


}
