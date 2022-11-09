package com.example.app_graphs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /* Open database connection */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /*Close connection*/
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /*gets toiletries*/
    public int getToiletries() {
        Cursor cursor = database.rawQuery("SELECT * FROM productlist WHERE CATEGORY LIKE '%Toiletries%'", null);
        cursor.moveToFirst();
        int price = 0;
        while(cursor.isAfterLast()==false){
            price = price + cursor.getInt(5);
            cursor.moveToNext();
        }
        cursor.close();
        return price;
    }

    /*gets spreads*/
    public int getSpreads() {
        Cursor cursor = database.rawQuery("SELECT * FROM productlist WHERE CATEGORY LIKE '%Spreads%'", null);
        cursor.moveToFirst();
        int price = 0;
        while(cursor.isAfterLast()==false){
            price = price + cursor.getInt(5);
            cursor.moveToNext();
        }
        cursor.close();
        return price;
    }

    /*gets snacks*/
    public int getSnacks() {
        Cursor cursor = database.rawQuery("SELECT * FROM productlist WHERE CATEGORY LIKE '%Snacks%'", null);
        cursor.moveToFirst();
        int price = 0;
        while(cursor.isAfterLast()==false){
            price = price + cursor.getInt(5);
            cursor.moveToNext();
        }
        cursor.close();
        return price;
    }

    /*gets personal care*/
    public int getPersonalCare() {
        Cursor cursor = database.rawQuery("SELECT * FROM productlist WHERE CATEGORY LIKE '%Personal Care%'", null);
        cursor.moveToFirst();
        int price = 0;
        while(cursor.isAfterLast()==false){
            price = price + cursor.getInt(5);
            cursor.moveToNext();
        }
        cursor.close();
        return price;
    }

    /*gets noodles*/
    public int getNoodles() {
        Cursor cursor = database.rawQuery("SELECT * FROM productlist WHERE CATEGORY LIKE '%Noodles%'", null);
        cursor.moveToFirst();
        int price = 0;
        while(cursor.isAfterLast()==false){
            price = price + cursor.getInt(5);
            cursor.moveToNext();
        }
        cursor.close();
        return price;
    }

    /*gets dairy*/
    public int getDairy() {
        Cursor cursor = database.rawQuery("SELECT * FROM productlist WHERE CATEGORY LIKE '%Dairy%'", null);
        cursor.moveToFirst();
        int price = 0;
        while(cursor.isAfterLast()==false){
            price = price + cursor.getInt(5);
            cursor.moveToNext();
        }
        cursor.close();
        return price;
    }

    /*gets condiments*/
    public int getCondiments() {
        Cursor cursor = database.rawQuery("SELECT * FROM productlist WHERE CATEGORY LIKE '%Condiments%'", null);
        cursor.moveToFirst();
        int price = 0;
        while(cursor.isAfterLast()==false){
            price = price + cursor.getInt(5);
            cursor.moveToNext();
        }
        cursor.close();
        return price;
    }

    /*gets coffee*/
    public int getCoffee() {
        Cursor cursor = database.rawQuery("SELECT * FROM productlist WHERE CATEGORY LIKE '%Coffee%'", null);
        cursor.moveToFirst();
        int price = 0;
        while(cursor.isAfterLast()==false){
            price = price + cursor.getInt(5);
            cursor.moveToNext();
        }
        cursor.close();
        return price;
    }

    /*gets Cleaning Agents*/
    public int getCleaningAgents() {
        Cursor cursor = database.rawQuery("SELECT * FROM productlist WHERE CATEGORY LIKE '%Cleaning Agents%'", null);
        cursor.moveToFirst();
        int price = 0;
        while(cursor.isAfterLast()==false){
            price = price + cursor.getInt(5);
            cursor.moveToNext();
        }
        cursor.close();
        return price;
    }

    /*gets cereal*/
    public int getCereal() {
        Cursor cursor = database.rawQuery("SELECT * FROM productlist WHERE CATEGORY LIKE '%Cereal%'", null);
        cursor.moveToFirst();
        int price = 0;
        while(cursor.isAfterLast()==false){
            price = price + cursor.getInt(5);
            cursor.moveToNext();
        }
        cursor.close();
        return price;
    }

    /*gets canned goods*/
    public int getCannedGoods() {
        Cursor cursor = database.rawQuery("SELECT * FROM productlist WHERE CATEGORY LIKE '%Canned Goods%'", null);
        cursor.moveToFirst();
        int price = 0;
        while(cursor.isAfterLast()==false){
            price = price + cursor.getInt(5);
            cursor.moveToNext();
        }
        cursor.close();
        return price;
    }


}
