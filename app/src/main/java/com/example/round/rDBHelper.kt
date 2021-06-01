package com.example.round

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class rDBHelper(val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object{
        val DB_NAME = "mydb.db"
        val DB_VERSION = 1
        val TABLE_NAME = "routineData"
        val RID = "routineID"
        val RNAME = "routineName"
        val RTERM = "routineTerm"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val create_table = "create table if not exists $TABLE_NAME ("+
                "$RID integer primary key,"+
                "$RNAME string," +
                "$RTERM integer);"
        db!!.execSQL(create_table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val drop_table = "drop table if exists $TABLE_NAME;"
        db!!.execSQL(drop_table)
        onCreate(db)
    }

    fun insertRoutine(routine:routineData):Boolean{
        val values = ContentValues()
        //RID값은 자동 증가
        values.put(RID, routine.routineID)
        values.put(RNAME, routine.routineName)
        values.put(RTERM, routine.routineTerm)//기간 24시간 고정.
        val db = writableDatabase
        val flag = db.insert(TABLE_NAME, null, values)>0
        db.close()
        return flag
    }
}