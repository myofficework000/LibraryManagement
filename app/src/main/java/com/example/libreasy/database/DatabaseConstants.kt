package com.example.libreasy.database

object DatabaseConstants {

    const val DATABASE_NAME = "libreasy"
    const val DATABASE_VERSION = 3

    const val TABLE_BOOKS = "books"
    const val COLUMN_BOOK_ID = "bookId"
    const val COLUMN_TITLE = "title"
    const val COLUMN_AUTHOR = "author"
    const val COLUMN_GENRE = "genre"
    const val COLUMN_RATING = "rating"
    const val COLUMN_YEAR = "year"
    const val COLUMN_DESCRIPTION = "description"

    const val TABLE_AUTHORS = "authors"
    const val COLUMN_AUTHOR_ID = "authorId"
    const val COLUMN_AUTHOR_NAME = "name"
    const val COLUMN_AUTHOR_RATING = "rating"
    const val COLUMN_AUTHOR_BIRTH = "birth"

    val CREATE_BOOKS_TABLE = """
        CREATE TABLE $TABLE_BOOKS (
        $COLUMN_BOOK_ID INTEGER PRIMARY KEY AUTOINCREMENT,
        $COLUMN_TITLE TEXT,
        $COLUMN_AUTHOR TEXT,
        $COLUMN_GENRE TEXT,
        $COLUMN_RATING INTEGER,
        $COLUMN_YEAR TEXT,
        $COLUMN_DESCRIPTION TEXT)
        """.trimIndent()

    val CREATE_AUTHORS_TABLE = """
        CREATE TABLE $TABLE_AUTHORS (
        $COLUMN_AUTHOR_ID INTEGER PRIMARY KEY AUTOINCREMENT,
        $COLUMN_AUTHOR_NAME TEXT,
        $COLUMN_AUTHOR_RATING INTEGER,
        $COLUMN_AUTHOR_BIRTH TEXT)
        """.trimIndent()
}