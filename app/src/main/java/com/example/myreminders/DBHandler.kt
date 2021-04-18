package com.example.myreminders

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class DBHandler
/**
 * Creates a version of the My Reminders database.
 *
 * @param context reference to the activity that initializes a DBHandler
 * @param factory null
 */
(context: Context?, factory: CursorFactory?) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    /**
     * Creates the tables in the database.
     *
     * @param sqLiteDatabase reference to the My Reminders database
     */
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {

        // define create statement for myreminders table and store it
        // in a String
        val query = "CREATE TABLE " + TABLE_MY_REMINDERS + "(" +
                COLUMN_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LIST_TITLE + " TEXT, " +
                COLUMN_LIST_TEXT + " TEXT, " +
                COLUMN_REMINDER_PRIORITY + " TEXT, " +
                COLUMN_REMINDER_DATE + " TEXT);"

        // execute the statment
        sqLiteDatabase.execSQL(query)
    }

    /**
     * Creates a new version of the My Reminders database.
     *
     * @param sqLiteDatabase reference to My Reminders database
     * @param oldVersion     old version of My Reminders database
     * @param newVersion     new version of My Reminders database
     */
    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        // define drop statement and store it in a String
        val query = "DROP TABLE IF EXISTS $TABLE_MY_REMINDERS"

        // execute the drop statement
        sqLiteDatabase.execSQL(query)

        // call method that creates the tables
        onCreate(sqLiteDatabase)
    }

    /**
     * This method gets called when the add button in the Action Bar of the
     * CreateList Activity gets clicked. It inserts a new row into the my reminders table
     * @param title reminder title
     * @param text  reminder text
     * @param priority  reminder priority
     */
    fun addMyReminders(title: String?, text: String?, priority: String?, date: String?) {

        // get reference to the my reminders database
        val db = writableDatabase

        // initialize a ContentValues object
        val values = ContentValues()

        // put data into ContentValues object
        values.put(COLUMN_LIST_TITLE, title)
        values.put(COLUMN_LIST_TEXT, text)
        values.put(COLUMN_REMINDER_PRIORITY, priority)
        values.put(COLUMN_REMINDER_DATE, date)


        // insert data in ContentValues object into my reminders table
        db.insert(TABLE_MY_REMINDERS, null, values)

        // close database reference
        db.close()
    }// get reference to the my reminders database

    // define select statement and store it in a String

    // execute select statement and return it as a Cursor

    /**
     * This method gets called when the MainActivity is created. It will
     * select and return all of the data in the my reminders table
     *
     * @return Cursor that contains all data in the my reminders table
     */
    val myReminders: Cursor
        get() {

            // get reference to the my reminders database
            val db = writableDatabase

            // define select statement and store it in a String
            val query = "SELECT * FROM $TABLE_MY_REMINDERS"

            // execute select statement and return it as a Cursor
            return db.rawQuery(query, null)
        }

    fun getReminder(_id: Int): Cursor {

        // get reference to the my reminders database
        val db = writableDatabase

        // define select statement and store it in a String
        val query = "SELECT * FROM " + TABLE_MY_REMINDERS +
                " WHERE " + COLUMN_LIST_ID + " = " + _id

        // execute select statement and return it as a Cursor
        return db.rawQuery(query, null)
    }

    /**
     * This method gets called when the ViewHighPriority is started.
     *
     * @param priority id
     * @return myreminders priority
     */
    fun getMyRemindersHighPriority(priority: String): Cursor {
        // get reference to the my reminders database
        val db = writableDatabase

        // declare and initialize String that will be returned
        val name = ""

        // define select statement
        val query = "SELECT * FROM " + TABLE_MY_REMINDERS +
                " WHERE " + COLUMN_REMINDER_PRIORITY + " = '" + priority + "'"

        // execute select statement and store it in a Cursor
        return db.rawQuery(query, null)
    }

    fun getMyRemindersMediumPriority(priority: String): Cursor {
        // get reference to the my reminders database
        val db = writableDatabase

        // declare and initialize String that will be returned
        val name = ""

        // define select statement
        val query = "SELECT * FROM " + TABLE_MY_REMINDERS +
                " WHERE " + COLUMN_REMINDER_PRIORITY + " = '" + priority + "'"

        // execute select statement and store it in a Cursor
        return db.rawQuery(query, null)
    }

    fun getMyRemindersLowPriority(priority: String): Cursor {
        // get reference to the my reminders database
        val db = writableDatabase

        // declare and initialize String that will be returned
        val name = ""

        // define select statement
        val query = "SELECT * FROM " + TABLE_MY_REMINDERS +
                " WHERE " + COLUMN_REMINDER_PRIORITY + " = '" + priority + "'"

        // execute select statement and store it in a Cursor
        return db.rawQuery(query, null)
    }

    // get reference to database
    val count:

    // define select statement

    // execute select statment and return count of rows
            Int
        get() {

            // get reference to database
            val db = writableDatabase

            // define select statement
            val query = " SELECT * FROM $TABLE_MY_REMINDERS"

            // execute select statment and return count of rows
            return db.rawQuery(query, null).count
        }

    /**
     * This method gets called when the delete button in the Action Bar of the
     * View Item actiivty gets clicked. It declares a row in the shoppinglistitem
     * table
     * @param _id database id of the shopping list item to be deleted
     */
    fun deleteReimnder(_id: Int) {

        // get reference to the shopper database
        val db = writableDatabase

        // define a delete statement and store it in String
        val query = "DELETE FROM " + TABLE_MY_REMINDERS +
                " WHERE " + COLUMN_LIST_ID + " = " + _id

        // execute the delete statement
        db.execSQL(query)

        // close database reference
        db.close()
    }

    companion object {
        // initialize constants for the DB name and version
        const val DATABASE_NAME = "myreminders.db"
        const val DATABASE_VERSION = 13

        // initialize constants for the myreminders table
        const val TABLE_MY_REMINDERS = "myreminders"
        const val COLUMN_LIST_ID = "_id"
        const val COLUMN_LIST_TITLE = "title"
        const val COLUMN_LIST_TEXT = "text"
        const val COLUMN_REMINDER_PRIORITY = "priority"
        const val COLUMN_REMINDER_DATE = "date"
    }
}