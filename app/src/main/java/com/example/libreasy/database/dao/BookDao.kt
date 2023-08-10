package com.example.libreasy.database.dao

import android.content.ContentValues
import android.database.Cursor
import com.example.libreasy.database.DatabaseConstants
import com.example.libreasy.database.DatabaseConstants.COLUMN_AUTHOR
import com.example.libreasy.database.DatabaseConstants.COLUMN_BOOK_ID
import com.example.libreasy.database.DatabaseConstants.COLUMN_DESCRIPTION
import com.example.libreasy.database.DatabaseConstants.COLUMN_GENRE
import com.example.libreasy.database.DatabaseConstants.COLUMN_RATING
import com.example.libreasy.database.DatabaseConstants.COLUMN_TITLE
import com.example.libreasy.database.DatabaseConstants.COLUMN_YEAR
import com.example.libreasy.database.DatabaseConstants.TABLE_BOOKS
import com.example.libreasy.database.DatabaseHelper
import com.example.libreasy.database.entity.Author
import com.example.libreasy.database.entity.Book

class BookDao(dbHelper: DatabaseHelper) {
    private var dbHelper = dbHelper

    fun addBook(book: Book): Long{
        val contentValue = ContentValues().apply {
            put(COLUMN_TITLE, book.title)
            put(COLUMN_AUTHOR, book.author)
            put(COLUMN_GENRE, book.genre)
            put(COLUMN_RATING, book.rating)
            put(COLUMN_YEAR, book.year)
            put(COLUMN_DESCRIPTION, book.description)
        }
        return dbHelper.writableDatabase.insert(TABLE_BOOKS, null, contentValue)
    }

    fun getBooks(): List<Book>{
        val books = mutableListOf<Book>()
        val cursor: Cursor = dbHelper.readableDatabase.query(TABLE_BOOKS, null, null, null, null, null, null)
        with(cursor){
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(COLUMN_BOOK_ID))
                val title = getString(getColumnIndexOrThrow(COLUMN_TITLE))
                val author = getString(getColumnIndexOrThrow(COLUMN_AUTHOR))
                val genre = getString(getColumnIndexOrThrow(COLUMN_GENRE))
                val rating = getInt(getColumnIndexOrThrow(COLUMN_RATING))
                val year = getString(getColumnIndexOrThrow(COLUMN_YEAR))
                val description = getString(getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                books.add(Book(id, title, author, genre, rating, year, description))
            }
        }
        cursor.close()
        return books
    }

    fun getAllGenres(): List<String> {
        val genres = mutableListOf<String>()
        val cursor: Cursor = dbHelper.readableDatabase.query(TABLE_BOOKS, null, null, null, null, null, null)
        with(cursor){
            while (moveToNext()) {
                val genreName = getString(cursor.getColumnIndexOrThrow(COLUMN_GENRE))
                genres.add(genreName)
            }
        }
        cursor.close()
        return genres.distinct()
    }

    fun getBooksByGenre(genre: String): List<Book> {
        if (genre == ""){
            return getBooks()
        }
        val books = mutableListOf<Book>()
        val cursor: Cursor = dbHelper.readableDatabase.query(TABLE_BOOKS, null, "$COLUMN_GENRE = ?", arrayOf(genre), null, null, null)
        with(cursor){
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(COLUMN_BOOK_ID))
                val title = getString(getColumnIndexOrThrow(COLUMN_TITLE))
                val author = getString(getColumnIndexOrThrow(COLUMN_AUTHOR))
                val genre = getString(getColumnIndexOrThrow(COLUMN_GENRE))
                val rating = getInt(getColumnIndexOrThrow(COLUMN_RATING))
                val year = getString(getColumnIndexOrThrow(COLUMN_YEAR))
                val description = getString(getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                books.add(Book(id, title, author, genre, rating, year, description))
            }
        }
        cursor.close()
        return books
    }

    fun getBooksByAuthor(author: String): List<Book> {
        if (author == ""){
            return getBooks()
        }
        val books = mutableListOf<Book>()
        val cursor: Cursor = dbHelper.readableDatabase.query(TABLE_BOOKS, null, "$COLUMN_AUTHOR = ?", arrayOf(author), null, null, null)
        with(cursor){
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(COLUMN_BOOK_ID))
                val title = getString(getColumnIndexOrThrow(COLUMN_TITLE))
                val author = getString(getColumnIndexOrThrow(COLUMN_AUTHOR))
                val genre = getString(getColumnIndexOrThrow(COLUMN_GENRE))
                val rating = getInt(getColumnIndexOrThrow(COLUMN_RATING))
                val year = getString(getColumnIndexOrThrow(COLUMN_YEAR))
                val description = getString(getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                books.add(Book(id, title, author, genre, rating, year, description))
            }
        }
        cursor.close()
        return books
    }

    fun getBookByTitle(title:String): Book {
        var book: Book
        val cursor: Cursor = dbHelper.readableDatabase.query(DatabaseConstants.TABLE_BOOKS, null, "$COLUMN_TITLE = ?", arrayOf(title), null, null, null)
        with(cursor) {
            moveToFirst()
            val id = getLong(getColumnIndexOrThrow(COLUMN_BOOK_ID))
            val title = getString(getColumnIndexOrThrow(COLUMN_TITLE))
            val author = getString(getColumnIndexOrThrow(COLUMN_AUTHOR))
            val genre = getString(getColumnIndexOrThrow(COLUMN_GENRE))
            val rating = getInt(getColumnIndexOrThrow(COLUMN_RATING))
            val year = getString(getColumnIndexOrThrow(COLUMN_YEAR))
            val description = getString(getColumnIndexOrThrow(COLUMN_DESCRIPTION))
            book = Book(id, title, author, genre, rating, year, description)
        }
        cursor.close()
        return book
    }

    fun getBooksSortedByYear(): List<Book>{
        val books = mutableListOf<Book>()
        val cursor: Cursor = dbHelper.readableDatabase.query(TABLE_BOOKS, null, null, null, null, null, "$COLUMN_YEAR DESC")
        with(cursor){
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(COLUMN_BOOK_ID))
                val title = getString(getColumnIndexOrThrow(COLUMN_TITLE))
                val author = getString(getColumnIndexOrThrow(COLUMN_AUTHOR))
                val genre = getString(getColumnIndexOrThrow(COLUMN_GENRE))
                val rating = getInt(getColumnIndexOrThrow(COLUMN_RATING))
                val year = getString(getColumnIndexOrThrow(COLUMN_YEAR))
                val description = getString(getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                books.add(Book(id, title, author, genre, rating, year, description))
            }
        }
        cursor.close()
        return books
    }

    fun getGenresByAuthor(author: String): String {
        val genres = mutableListOf<String>()
        val cursor: Cursor = dbHelper.readableDatabase.query(TABLE_BOOKS, null, "$COLUMN_AUTHOR = ?", arrayOf(author), null, null, null)
        with(cursor){
            while (moveToNext()) {
                val genreName = getString(cursor.getColumnIndexOrThrow(COLUMN_GENRE))
                genres.add(genreName)
            }
        }
        cursor.close()
        return genres.distinct().joinToString(", ")
    }

    fun deleteBookById(bookId: Long) {
        val selection = "$COLUMN_BOOK_ID = ?"
        val selectionArgs = arrayOf(bookId.toString())
        val deletedRows = dbHelper.writableDatabase.delete(TABLE_BOOKS, selection, selectionArgs)
        if (deletedRows == 0) {
            // The book with the given ID did not exist in the database
            // Do nothing
        }
    }

}

