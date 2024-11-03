package com.oscar.a281_proy.database

import android.content.Context
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLite(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "my_database.db"
        private const val DATABASE_VERSION = 1


        const val TABLE_USERS = "users"


        const val COLUMN_ID = "id"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_ROLE = "role"


        private const val SQL_CREATE_USERS_TABLE = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_EMAIL TEXT NOT NULL UNIQUE,
                $COLUMN_PASSWORD TEXT NOT NULL,
                $COLUMN_ROLE TEXT NOT NULL
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(SQL_CREATE_USERS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }
    fun addUser(email: String, password: String, role: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("email", email)
            put("password", password)
            put("role", role)
        }


        val result = db.insert("users", null, values)
        if (result == -1L) {
            throw Exception("Error al insertar los datos del usuario")
        }

        db.close()
    }

    fun checkUser(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USERS WHERE $COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?"
        val cursor: Cursor = db.rawQuery(query, arrayOf(email, password))

        val userExists = cursor.count > 0
        cursor.close()
        db.close()
        return userExists
    }
}