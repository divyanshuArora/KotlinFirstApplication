package com.example.kotlinfirstapplication.Utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.kotlinfirstapplication.UserModel
import java.time.chrono.MinguoDate

class Sqlite_Database(context: Context) : SQLiteOpenHelper(context, dbName, null, 1) {

    var helper: SQLiteDatabase? = null
    var sqLiteDatabase: SQLiteDatabase? = null


    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val CREATE_USER_TABLE =
            ("create table IF NOT EXISTS " + USER_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + NAME + " TEXT,"
                    + EMAIL + " TEXT,"
                    + PASSWORD + " TEXT"
                    + ")")
        Log.d("Sqlite","createTable"+CREATE_USER_TABLE)
        sqLiteDatabase.execSQL(CREATE_USER_TABLE)

    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {

    }


     fun addUser(userModel: UserModel) {

        val sqLiteDatabase = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(NAME, userModel.name)
        contentValues.put(EMAIL, userModel.email)
        contentValues.put(PASSWORD, userModel.password)

        sqLiteDatabase.insert(USER_TABLE, null, contentValues)
        sqLiteDatabase.close()
    }



    companion object {

        private val dbName = "Kotlin"
        private val USER_TABLE = "users"
        private val ID = "id"
        private val NAME = "user_name"
        private val EMAIL = "user_email"
        private val PASSWORD = "user_password"
    }


}
