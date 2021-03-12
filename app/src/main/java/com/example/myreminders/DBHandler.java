package com.example.myreminders;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class DBHandler extends SQLiteOpenHelper {

    // initialize constants for the DB name and version
    public static final String DATABASE_NAME = "myreminders.db";
    public static final int DATABASE_VERSION = 13;

    // initialize constants for the myreminders table
    public static final String TABLE_MY_REMINDERS = "myreminders";
    public static final String COLUMN_LIST_ID = "_id";
    public static final String COLUMN_LIST_TITLE = "title";
    public static final String COLUMN_LIST_TEXT = "text";
    public static final String COLUMN_REMINDER_PRIORITY = "priority";
    public static final String COLUMN_REMINDER_DATE = "date";





    /**
     * Creates a version of the My Reminders database.
     *
     * @param context reference to the activity that initializes a DBHandler
     * @param factory null
     */
    public DBHandler(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    /**
     * Creates the tables in the database.
     *
     * @param sqLiteDatabase reference to the My Reminders database
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // define create statement for myreminders table and store it
        // in a String
        String query = "CREATE TABLE " + TABLE_MY_REMINDERS + "(" +
                COLUMN_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LIST_TITLE + " TEXT, " +
                COLUMN_LIST_TEXT + " TEXT, " +
                COLUMN_REMINDER_PRIORITY + " TEXT, " +
               COLUMN_REMINDER_DATE + " TEXT);";

        // execute the statment
        sqLiteDatabase.execSQL(query);
    }

    /**
     * Creates a new version of the My Reminders database.
     *
     * @param sqLiteDatabase reference to My Reminders database
     * @param oldVersion     old version of My Reminders database
     * @param newVersion     new version of My Reminders database
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        // define drop statement and store it in a String
        String query = "DROP TABLE IF EXISTS " + TABLE_MY_REMINDERS;

        // execute the drop statement
        sqLiteDatabase.execSQL(query);

        // call method that creates the tables
        onCreate(sqLiteDatabase);

    }

    /**
     * This method gets called when the add button in the Action Bar of the
     * CreateList Activity gets clicked. It inserts a new row into the my reminders table
     *  @param title reminder title
     * @param text  reminder text
     * @param priority  reminder priority
     *
     */
    public void addMyReminders(String title, String text, String priority, String date) {

        // get reference to the my reminders database
        SQLiteDatabase db = getWritableDatabase();

        // initialize a ContentValues object
        ContentValues values = new ContentValues();

        // put data into ContentValues object
        values.put(COLUMN_LIST_TITLE, title);
        values.put(COLUMN_LIST_TEXT, text);
        values.put(COLUMN_REMINDER_PRIORITY, priority);
        values.put(COLUMN_REMINDER_DATE, date);



        // insert data in ContentValues object into my reminders table
        db.insert(TABLE_MY_REMINDERS, null, values);

        // close database reference
        db.close();


    }

    /**
     * This method gets called when the MainActivity is created. It will
     * select and return all of the data in the my reminders table
     *
     * @return Cursor that contains all data in the my reminders table
     */
    public Cursor getMyReminders() {

        // get reference to the my reminders database
        SQLiteDatabase db = getWritableDatabase();

        // define select statement and store it in a String
        String query = "SELECT * FROM " + TABLE_MY_REMINDERS;

        // execute select statement and return it as a Cursor
        return db.rawQuery(query, null);
    }

    /**
     * This method gets called when the ViewHighPriority is started.
     *
     * @param priority id
     * @return myreminders priority
     */

    public Cursor getMyRemindersHighPriority(String priority) {
        // get reference to the my reminders database
        SQLiteDatabase db = getWritableDatabase();

        // declare and initialize String that will be returned
        String name = "";

        // define select statement
        String query = "SELECT * FROM " + TABLE_MY_REMINDERS +
                " WHERE " + COLUMN_REMINDER_PRIORITY + " = '" + priority + "'";

        // execute select statement and store it in a Cursor
        return db.rawQuery(query, null);
    }

    public Cursor getMyRemindersMediumPriority(String priority) {
        // get reference to the my reminders database
        SQLiteDatabase db = getWritableDatabase();

        // declare and initialize String that will be returned
        String name = "";

        // define select statement
        String query = "SELECT * FROM " + TABLE_MY_REMINDERS +
                " WHERE " + COLUMN_REMINDER_PRIORITY + " = '" + priority + "'";

        // execute select statement and store it in a Cursor
        return db.rawQuery(query, null);
    }

    public Cursor getMyRemindersLowPriority(String priority) {
        // get reference to the my reminders database
        SQLiteDatabase db = getWritableDatabase();

        // declare and initialize String that will be returned
        String name = "";

        // define select statement
        String query = "SELECT * FROM " + TABLE_MY_REMINDERS +
                " WHERE " + COLUMN_REMINDER_PRIORITY + " = '" + priority + "'";

        // execute select statement and store it in a Cursor
        return db.rawQuery(query, null);
    }

    public int getCount () {

        // get reference to database
        SQLiteDatabase db = getWritableDatabase();

        // define select statement
        String query = " SELECT * FROM " + TABLE_MY_REMINDERS;

        // execute select statment and return count of rows
        return db.rawQuery(query, null).getCount();

    }
}
