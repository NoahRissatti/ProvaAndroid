package com.example.provaandroid.Repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.provaandroid.Item;
import com.example.provaandroid.Repository.DatabaseContract;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "seu_banco_de_dados.db";
    private static final int DATABASE_VERSION = 1;

    public static final String CREATE_TABLE = "CREATE TABLE " + DatabaseContract.ItemEntry.TABLE_NAME + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DatabaseContract.ItemEntry.COLUMN_TEXT + " TEXT, " +
            DatabaseContract.ItemEntry.COLUMN_OPTION + " TEXT);";


    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + DatabaseContract.ItemEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                DatabaseContract.ItemEntry._ID,
                DatabaseContract.ItemEntry.COLUMN_TEXT,
                DatabaseContract.ItemEntry.COLUMN_OPTION
        };

        Cursor cursor = db.query(
                DatabaseContract.ItemEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ItemEntry._ID));
            String text = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ItemEntry.COLUMN_TEXT));
            String option = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ItemEntry.COLUMN_OPTION));
            Item item = new Item(id, text, option);
            itemList.add(item);
        }

        cursor.close();
        db.close();

        return itemList;
    }

    public void deleteItem(String itemId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                DatabaseContract.ItemEntry.TABLE_NAME,
                DatabaseContract.ItemEntry._ID + " = ?",
                new String[]{String.valueOf(itemId)}
        );
        db.close();
    }

}
