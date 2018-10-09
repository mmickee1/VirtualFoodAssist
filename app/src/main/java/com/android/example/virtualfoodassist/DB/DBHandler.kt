package com.android.example.virtualfoodassist.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_NAME = "MyDB"
val TABLE_NAME = "Food"
val COL_NAME = "name"
val COL_ID = "id"

@Suppress("RedundantSemicolon")
class DBHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " +
                TABLE_NAME +
                " (" +
                COL_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME +
                " VARCHAR(256))";

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun insertData(food: Food) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NAME, food.name)
        db.insert(TABLE_NAME, null, cv)
    }

    fun readData(): MutableList<Food> {
        val list: MutableList<Food> = ArrayList()

        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                val food = Food()
                food.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                food.name = result.getString(result.getColumnIndex(COL_NAME))
                list.add(food)
            } while (result.moveToNext())
        }

        result.close()
        db.close()

        return list
    }

    fun deleteData() {
        val db = this.writableDatabase
        //db.delete(TABLE_NAME, COL_ID + "=?", arrayOf(1.toString()))
        //to delete all
        db.delete(TABLE_NAME,null,null )
        db.close()
    }


    //currently not using sql updater..
   /* fun updateData() {
        val db = this.writableDatabase
        val query = "SELECT * FROM " + TABLE_NAME
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                val cv = ContentValues()
                db.update(TABLE_NAME, cv, COL_ID + "=? AND " + COL_NAME + "=?",
                        arrayOf(result.getString(result.getColumnIndex(COL_ID)), result.getString(result.getColumnIndex(COL_NAME))))
            } while (result.moveToNext())
        }
        result.close()
        db.close()

    }*/
}