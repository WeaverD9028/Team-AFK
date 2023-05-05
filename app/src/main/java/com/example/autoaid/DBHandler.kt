package com.example.autoaid

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler  // creating a constructor for our database handler.
    (context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    // below method is for creating a database by running a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VIN_COL + " TEXT)")

        val carquery = ("CREATE TABLE " + TABLE_NAME2 + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MAKE_COL + " TEXT,"
                + MODEL_COL + " TEXT,"
                + YEAR_COL + " TEXT,"
                + ENGINE_COL + " TEXT)")

        val symptomquery = ("CREATE TABLE " + TABLE_NAME3 + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VIN_COL + " TEXT,"
                + SYM_COL + " TEXT,"
                + CODE_COL + " TEXT)")

        val diagnosticquery = ("CREATE TABLE " + TABLE_NAME4 + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VIN_COL + " TEXT,"
                + SYM_COL + " TEXT,"
                + CODE_COL + " TEXT,"
                + COST_COL + " TEXT)")

        val savedreportquery = ("CREATE TABLE " + TABLE_NAME5 + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DATE_COL + " TEXT,"
                + TIME_COL + " TEXT,"
                + MAKE_COL + " TEXT,"
                + MODEL_COL + " TEXT,"
                + YEAR_COL + " TEXT,"
                + VIN_COL + " TEXT,"
                + SYM_COL + " TEXT,"
                + CODE_COL + " TEXT,"
                + COST_COL + " TEXT)")


        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query)
        db.execSQL(carquery)
        db.execSQL(symptomquery)
        db.execSQL(diagnosticquery)
        db.execSQL(savedreportquery)
    }

    fun readMaster(): ArrayList<AllDataModel>{
        // on below line we are creating a
        // database for reading our database.
        val db = this.readableDatabase

        val masterquery = "SELECT myvin.id, myvin.vin, mycar.make, mycar.modal, mycar.year, mycar.engine FROM myvin INNER JOIN mycar ON myvin.id=mycar.id"

        // on below line we are creating a cursor with query to read data from database.
        val cursorMaster = db.rawQuery(masterquery, null)

        // on below line we are creating a new array list.
        val dataModalArrayList = ArrayList<AllDataModel>()

        // moving our cursor to first position.
        if (cursorMaster.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                dataModalArrayList.add(
                    AllDataModel(
                        cursorMaster.getString(1),
                        cursorMaster.getString(2),
                        cursorMaster.getString(3),
                        cursorMaster.getString(4),
                        cursorMaster.getString(5),
                    )
                )
            } while (cursorMaster.moveToNext())
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorMaster.close()
        return dataModalArrayList
    }

    // we have created a new method for reading all the courses.
    fun readSym(): ArrayList<RepairModel> {
        // on below line we are creating a
        // database for reading our database.
        val db = this.readableDatabase

        // on below line we are creating a cursor with query to read data from database.
        val cursorInfos = db.rawQuery("SELECT * FROM $TABLE_NAME3", null)

        // on below line we are creating a new array list.
        val symModalArrayList = ArrayList<RepairModel>()

        // moving our cursor to first position.
        if (cursorInfos.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                symModalArrayList.add(
                    RepairModel(
                        cursorInfos.getString(1),
                        cursorInfos.getString(2),
                        cursorInfos.getString(3),
                    )
                )
            } while (cursorInfos.moveToNext())
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorInfos.close()
        return symModalArrayList
    }

    // we have created a new method for reading certain symptoms.
    fun readSpecficSym(carVin: String?): ArrayList<RepairModel> {
        // on below line we are creating a
        // database for reading our database.
        val db = this.readableDatabase

        val col_vin = "vin"

        val VIN = '"' + "$carVin" + '"'

        // on below line we are creating a cursor with query to read data from database.
        val cursorInfos = db.rawQuery("SELECT * FROM $TABLE_NAME3 WHERE $col_vin = $VIN", null)

        // on below line we are creating a new array list.
        val symModalArrayList = ArrayList<RepairModel>()

        // moving our cursor to first position.
        if (cursorInfos.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                symModalArrayList.add(
                    RepairModel(
                        cursorInfos.getString(1),
                        cursorInfos.getString(2),
                        cursorInfos.getString(3),
                    )
                )
            } while (cursorInfos.moveToNext())
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorInfos.close()
        println(carVin)
        return symModalArrayList
    }

    fun addNewSym(carVin: String?,
                  symDescription: String?,
                  symCode: String?,
    ){

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        val db = this.writableDatabase

        // on below line we are creating a
        // variable for content values.
        val values = ContentValues()

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(VIN_COL, carVin)
        values.put(SYM_COL, symDescription)
        values.put(CODE_COL, symCode)



        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME3, null, values)

        // at last we are closing our
        // database after adding database.
        db.close()
    }

    fun addNewCar(carMake: String?,
                  carModel: String?,
                  carYear: String?,
                  carEngine: String?
    ){

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        val db = this.writableDatabase

        // on below line we are creating a
        // variable for content values.
        val values = ContentValues()

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(MAKE_COL, carMake)
        values.put(MODEL_COL, carModel)
        values.put(YEAR_COL, carYear)
        values.put(ENGINE_COL, carEngine)


        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME2, null, values)

        // at last we are closing our
        // database after adding database.
        db.close()
    }

    // we have created a new method for reading all cars.
    fun readCar(): ArrayList<InfoModel> {
        // on below line we are creating a
        // database for reading our database.
        val db = this.readableDatabase

        // on below line we are creating a cursor with query to read data from database.
        val cursorInfos = db.rawQuery("SELECT * FROM " + TABLE_NAME2, null)

        // on below line we are creating a new array list.
        val infoModalArrayList = ArrayList<InfoModel>()

        // moving our cursor to first position.
        if (cursorInfos.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                infoModalArrayList.add(
                    InfoModel(
                        cursorInfos.getString(1),
                        cursorInfos.getString(2),
                        cursorInfos.getString(3),
                        cursorInfos.getString(4),
                    )
                )
            } while (cursorInfos.moveToNext())
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorInfos.close()
        return infoModalArrayList
    }

    fun addSavedReport(
                        date : String?,
                        time : String?,
                        carMake: String?,
                       carModel: String?,
                       carYear: String?,
                       carVin: String?,
                       carSym: String?,
                       carCode: String?,
                       carCost: String?,
    ){

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        val db = this.writableDatabase

        // on below line we are creating a
        // variable for content values.
        val values = ContentValues()

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(DATE_COL, date)
        values.put(TIME_COL, time)
        values.put(MAKE_COL, carMake)
        values.put(MODEL_COL, carModel)
        values.put(YEAR_COL, carYear)
        values.put(VIN_COL, carVin)
        values.put(SYM_COL, carSym)
        values.put(CODE_COL, carCode)
        values.put(COST_COL, carCost)



        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME5, null, values)

        // at last we are closing our
        // database after adding database.
        db.close()
    }

    // we have created a new method for reading all the saved reports.
    fun readReports(): ArrayList<SavedReportModel> {
        // on below line we are creating a
        // database for reading our database.
        val db = this.readableDatabase

        // on below line we are creating a cursor with query to read data from database.
        val cursorInfos = db.rawQuery("SELECT * FROM " + TABLE_NAME5, null)

        // on below line we are creating a new array list.
        val savedReportModalArrayList = ArrayList<SavedReportModel>()

        // moving our cursor to first position.
        if (cursorInfos.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                savedReportModalArrayList.add(
                    SavedReportModel(
                        cursorInfos.getString(1),
                        cursorInfos.getString(2),
                        cursorInfos.getString(3),
                        cursorInfos.getString(4),
                        cursorInfos.getString(5),
                        cursorInfos.getString(6),
                        cursorInfos.getString(7),
                        cursorInfos.getString(8),
                        cursorInfos.getString(9),
                    )
                )
            } while (cursorInfos.moveToNext())
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorInfos.close()
        return savedReportModalArrayList
    }

    // this method is use to add new vin to our sqlite database.
    fun addNewVin(
        carVin: String?,
    ) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        val db = this.writableDatabase

        // on below line we are creating a
        // variable for content values.
        val values = ContentValues()

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(VIN_COL, carVin)


        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values)

        // at last we are closing our
        // database after adding database.
        db.close()
    }



    // we have created a new method for reading all the courses.
    fun readVin(): ArrayList<CarModel> {
        // on below line we are creating a
        // database for reading our database.
        val db = this.readableDatabase

        // on below line we are creating a cursor with query to read data from database.
        val cursorCars = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

        // on below line we are creating a new array list.
        val carModalArrayList = ArrayList<CarModel>()

        // moving our cursor to first position.
        if (cursorCars.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                carModalArrayList.add(
                    CarModel(
                        cursorCars.getString(1),
                    )
                )
            } while (cursorCars.moveToNext())
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCars.close()
        return carModalArrayList
    }

    // below is the method for updating our courses
    fun updateVin(
        originalCarVin: String, carVin: String?
    ) {

        // calling a method to get writable database.
        val db = this.writableDatabase
        val values = ContentValues()

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(VIN_COL, carVin)


        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "vin=?", arrayOf(originalCarVin))
        db.close()
    }

    // this method is use to add new vin to our sqlite database.
    fun addNewDiagnotic(
        carVin: String?,
        carSym: String?,
        carCode: String?,
        carCost: String?,
    ) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        val db = this.writableDatabase

        // on below line we are creating a
        // variable for content values.
        val values = ContentValues()

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(VIN_COL, carVin)
        values.put(SYM_COL, carSym)
        values.put(CODE_COL, carCode)
        values.put(COST_COL, carCost)


        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME4, null, values)

        // at last we are closing our
        // database after adding database.
        db.close()
        print("ADDED TO DIAGNOITICS TO DATABASE")
    }

    // we have created a new method for reading certain symptoms.
    fun readSpecficDia(carCode: String?): ArrayList<diagnoticModel> {
        // on below line we are creating a
        // database for reading our database.
        val db = this.readableDatabase

        val col_code = "code"

        val CODE =  '"' + "$carCode" + '"'



        // on below line we are creating a cursor with query to read data from database.
        val cursorInfos = db.rawQuery("SELECT * FROM $TABLE_NAME4 WHERE $col_code = $CODE", null)

        // on below line we are creating a new array list.
        val diagModalArrayList = ArrayList<diagnoticModel>()

        // moving our cursor to first position.
        if (cursorInfos.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                diagModalArrayList.add(
                    diagnoticModel(
                        cursorInfos.getString(1),
                        cursorInfos.getString(2),
                        cursorInfos.getString(3),
                        cursorInfos.getString(4),
                    )
                )
            } while (cursorInfos.moveToNext())
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorInfos.close()
        println(carCode)
        return diagModalArrayList
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME4)
        onCreate(db)
    }

    companion object {
        // creating a constant variables for our database.
        // below variable is for our database name.
        private const val DB_NAME = "vin.db"

        // below int is our database version
        private const val DB_VERSION = 4

        // below variable is for our table name.
        private const val TABLE_NAME = "myvin"

        // below variable is for our table name.
        private const val TABLE_NAME2 = "mycar"

        // below variable is for our table name.
        private const val TABLE_NAME3 = "mysymptom"

        // below variable is for our table name.
        private const val TABLE_NAME4 = "mydiagnostic"

        // below variable is for our table name.
        private const val TABLE_NAME5 = "mysavedreports"

        // below variable is for our id column.
        private const val ID_COL = "id"

        // below variable is for our car vin column
        private const val VIN_COL = "vin"

        // below variable is for our car make column
        private const val MAKE_COL = "make"

        // below variable is for date
        private const val DATE_COL = "date"

        // below variable is for time
        private const val TIME_COL = "time"

        // below variable is for our car modal column
        private const val MODEL_COL = "modal"

        // below variable is for our car year column
        private const val YEAR_COL = "year"

        // below variable is for our car year column
        private const val ENGINE_COL = "engine"

        // below variable is for our description of the repair column
        private const val SYM_COL = "symptom"

        // below variable is for our code of the repair column
        private const val CODE_COL = "code"

        // below variable is for our code of the repair column
        private const val COST_COL = "cost"

    }
}