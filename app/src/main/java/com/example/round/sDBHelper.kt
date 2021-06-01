package com.example.round

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class sDBHelper(val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object{
        val DB_NAME = "mydb.db"
        val DB_VERSION = 1
        val TABLE_NAME = "scheduleData"
        val RID = "routineID"
        val SID = "scheduleID"
        val SNAME = "scheduleName"
        val SSTART= "startTime"
        val SEND = "endTime"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val create_table = "create table if not exists $TABLE_NAME ("+
                "$RID integer,"+
                "$SID integer primary key autoincrement,"+
                "$SNAME string," +
                "$SSTART integer,"+
                "$SEND integer);"
        db!!.execSQL(create_table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val drop_table = "drop table if exists $TABLE_NAME;"
        db!!.execSQL(drop_table)
        onCreate(db)
    }

    fun insertSchedule(schedule:scheduleData):Boolean{
        val values = ContentValues()
        values.put(SNAME, schedule.scheduleName)
        values.put(SSTART, schedule.startTime)
        values.put(SEND, schedule.endTime)
        val db = writableDatabase
        val flag = db.insert(TABLE_NAME, null, values)>0
        db.close()
        return flag
    }
}
