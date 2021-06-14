package com.example.rupeekchallengee.utils

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*

/**
 * Created by Yash Chaturvedi on 14/06/2021.
 */
class DatabaseHandler(context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    private var db: SQLiteDatabase? = null
    override fun onCreate(db: SQLiteDatabase) {
        this.db = db
        val exe = ("CREATE TABLE IF NOT EXISTS " + LIKED_PLACE_TABLE
                + "(" + COLUMN_ID + " integer primary key"
                + ")")
        db.execSQL(exe)
    }
    fun setLikedPlace(map: HashMap<String, String>): Boolean {
        db = writableDatabase
        val values = ContentValues()
        values.put(
            COLUMN_ID,
            map[COLUMN_ID]
        )
        db!!.insert(LIKED_PLACE_TABLE, null, values)
        return true
    }

    fun isInLiked(id: String?): Boolean {
        db = readableDatabase
        val qry = "Select *  from $LIKED_PLACE_TABLE where $COLUMN_ID = $id"
        val cursor = db!!.rawQuery(qry, null)
        cursor.moveToFirst()
        return cursor.count > 0
    }


    val placeCount: Int
        get() {
            db = readableDatabase
            val qry = "Select *  from $LIKED_PLACE_TABLE"
            val cursor = db!!.rawQuery(qry, null)
            return cursor.count
        }


    fun clearPlaces() {
        db = readableDatabase
        db!!.execSQL("delete from $LIKED_PLACE_TABLE"  )
    }

    fun removePlace(id: String) {
        db = readableDatabase
        db!!.execSQL("delete from $LIKED_PLACE_TABLE where $COLUMN_ID = $id")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    companion object {
        private const val DB_NAME = "rupeek"
        private const val DB_VERSION = 3
        const val LIKED_PLACE_TABLE = "liked_place_table"
        const val COLUMN_ID = "place_id"
    }
}