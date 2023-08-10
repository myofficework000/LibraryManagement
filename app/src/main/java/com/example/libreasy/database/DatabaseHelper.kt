package com.example.libreasy.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.libreasy.database.DatabaseConstants.CREATE_AUTHORS_TABLE
import com.example.libreasy.database.DatabaseConstants.TABLE_BOOKS
import com.example.libreasy.database.DatabaseConstants.TABLE_AUTHORS
import com.example.libreasy.database.DatabaseConstants.CREATE_BOOKS_TABLE
import com.example.libreasy.database.DatabaseConstants.DATABASE_NAME
import com.example.libreasy.database.DatabaseConstants.DATABASE_VERSION

class DatabaseHelper(private val context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_BOOKS_TABLE)
        db?.execSQL(CREATE_AUTHORS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 3 && newVersion >=3) {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_BOOKS")
            //db?.execSQL("DROP TABLE IF EXISTS $TABLE_AUTHORS")
            db?.execSQL(CREATE_BOOKS_TABLE)
            //db?.execSQL(CREATE_AUTHORS_TABLE)
        }
    }
}