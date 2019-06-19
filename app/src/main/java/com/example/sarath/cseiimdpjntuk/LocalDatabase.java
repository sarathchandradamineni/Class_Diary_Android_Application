package com.example.sarath.cseiimdpjntuk;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalDatabase{
    public static String username="";
    public LocalDatabase()
    {

    }
    public LocalDatabase(String username)
    {
        this.username  = username;
    }
    public static String retrieveName()
    {
        return username;
    }
    public void userLogout()
    {
        username = "";
    }
}
