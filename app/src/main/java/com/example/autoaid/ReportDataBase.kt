package com.example.autoaid

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper


class ReportDataBase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val OLD_VERSION = 0
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "VehicleDatabase"
        private const val TABLE_CONTACTS = "VehicleTable"
        // 40.38
        private const val KEY_ID = "_id"
        private const val KEY_MAKE = "make"
        private const val KEY_MODEL = "model"
        private const val KEY_YEAR = "year"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        // creating table with fields
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS
                + "(" + KEY_ID + " TEXT PRIMARY KEY," + KEY_MAKE + " TEXT,"
                + KEY_MODEL + " TEXT,"
                + KEY_YEAR + " INTEGER" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion:Int , newVersion:Int) {
        db!!.execSQL("DROP TABLE IF EXISTS" + TABLE_CONTACTS)
        onCreate(db)
    }

    // Function to insert data

    fun addVehicle(veh: VehModelClass): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_ID, veh.id) // VehModelClass id
        contentValues.put(KEY_MAKE, veh.make) // VehModelClass make
        contentValues.put(KEY_MODEL, veh.model) // VehModelClass model
        contentValues.put(KEY_YEAR, veh.year) // VehModelClass year

        // Insert Row
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        // 2nd argument is String containing nullColumnHack


        db.close() // Closing Database connection
        return success

    }


    // method to read data
    fun viewVehical(): ArrayList<VehModelClass> {

        val vehList: ArrayList<VehModelClass> = ArrayList<VehModelClass>()

        val selectQuery = "SELECT * FROM $TABLE_CONTACTS"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)


        }catch(e: SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id : String
        var make: String
        var model: String
        var year: Int

        if(cursor.moveToFirst()){
            do {
                id = cursor.getString(cursor.getColumnIndexOrThrow(KEY_ID))
                make = cursor.getString(cursor.getColumnIndexOrThrow(KEY_MAKE))
                model = cursor.getString(cursor.getColumnIndexOrThrow(KEY_MODEL))
                year = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_YEAR))

                val veh = VehModelClass(id = id, make = make, model= model, year = year)
                vehList.add(veh)
            }while(cursor.moveToNext())
        }
        return vehList
    }

    // Function to update record
    fun updateVehical(veh: VehModelClass):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_MAKE, veh.make) // VehicalModelClass Make
        contentValues.put(KEY_MODEL, veh.model) // VehicalModelClass Model
        contentValues.put(KEY_YEAR, veh.year) // VehicalModelClass Year

        // Update Row
        val success = db.update(TABLE_CONTACTS, contentValues, KEY_ID + "=" + veh.id, null )
        // 2nd argument string containing nullColumnHack

        db.close() // Closing database connection
        return success

    }

    // Function to delete record
    fun deleteEmployee(veh: VehModelClass): Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, veh.id) // EmpModelClass id
        // Deleting Row
        val success = db.delete(TABLE_CONTACTS, KEY_ID + "=" + veh.id, null)
        // 2nd argument is String containing nullColumnHack
        db.close() // CLosing database connection
        return success

    }


}