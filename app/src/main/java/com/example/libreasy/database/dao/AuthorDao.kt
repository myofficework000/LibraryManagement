package com.example.libreasy.database.dao

import android.content.ContentValues
import android.database.Cursor
import com.example.libreasy.database.DatabaseConstants.COLUMN_AUTHOR_BIRTH
import com.example.libreasy.database.DatabaseConstants.COLUMN_AUTHOR_ID
import com.example.libreasy.database.DatabaseConstants.COLUMN_AUTHOR_NAME
import com.example.libreasy.database.DatabaseConstants.COLUMN_AUTHOR_RATING
import com.example.libreasy.database.DatabaseConstants.TABLE_AUTHORS
import com.example.libreasy.database.DatabaseHelper
import com.example.libreasy.database.entity.Author

class AuthorDao(dbHelper: DatabaseHelper) {
    private var dbHelper = dbHelper

    fun addAuthor(author: Author): Long{
        val contentValue = ContentValues().apply {
            put(COLUMN_AUTHOR_NAME, author.name)
            put(COLUMN_AUTHOR_RATING, author.rating)
            put(COLUMN_AUTHOR_BIRTH, author.birth)
        }
        return dbHelper.writableDatabase.insert(TABLE_AUTHORS, null, contentValue)
    }

    fun getAuthors(): List<Author>{
        val authors = mutableListOf<Author>()
        val cursor: Cursor = dbHelper.readableDatabase.query(TABLE_AUTHORS, null, null, null, null, null, null)
        with(cursor){
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(COLUMN_AUTHOR_ID))
                val name = getString(getColumnIndexOrThrow(COLUMN_AUTHOR_NAME))
                val rating = getInt(getColumnIndexOrThrow(COLUMN_AUTHOR_RATING))
                val birth = getString(getColumnIndexOrThrow(COLUMN_AUTHOR_BIRTH))
                authors.add(Author(id, name, rating, birth))
            }
        }
        cursor.close()
        return authors
    }

    fun getAuthorByName(name:String): Author {
        var author: Author
        val cursor: Cursor = dbHelper.readableDatabase.query(TABLE_AUTHORS, null, "$COLUMN_AUTHOR_NAME = ?", arrayOf(name), null, null, null)
        with(cursor) {
            moveToFirst()
            val id = getLong(getColumnIndexOrThrow(COLUMN_AUTHOR_ID))
            val name = getString(getColumnIndexOrThrow(COLUMN_AUTHOR_NAME))
            val rating = getInt(getColumnIndexOrThrow(COLUMN_AUTHOR_RATING))
            val birth = getString(getColumnIndexOrThrow(COLUMN_AUTHOR_BIRTH))
            author = Author(id, name, rating, birth)
        }
        cursor.close()
        return author
    }
}