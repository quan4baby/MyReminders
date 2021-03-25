package com.example.myreminders;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ViewReminder extends AppCompatActivity {

    // declare a DBHandler
    DBHandler dbHandler;

    // declare an Intent
    Intent intent;

    // declare EditTexts
    EditText titleEditText;
    EditText reminderEditText;

    // declare Spinner
    Spinner prioritySpinner;

    // declare a Bundle and a long used to get and store the data sent from
    // the MainActivity
    Bundle bundle;
    long id;


    // declare Strings to store the shopping list item's name, price, and quantity
    String title;
    String reminder;
    String priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reminder);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize Bundle
        bundle = this.getIntent().getExtras();

        // get the reminder id in the bundle and store it in Long
        id = bundle.getLong("_id");

// initialize DBHandler
        dbHandler = new DBHandler(this, null);

        // initialize EditTexts
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        reminderEditText = (EditText) findViewById(R.id.reminderEditText);
        prioritySpinner = (Spinner) findViewById(R.id.prioritySpinner);

        // disable EditTexts
        titleEditText.setEnabled(false);
        reminderEditText.setEnabled(false);
        prioritySpinner.setEnabled(false);

        // call the DBHandler method getMyReminders
        Cursor cursor = dbHandler.getReminder((int) id);

        // move to the first row in the Cursor
        cursor.moveToFirst();

        // get the title, reminder in the Cursor and store them in Strings
        title = cursor.getString(cursor.getColumnIndex("title"));
        reminder = cursor.getString(cursor.getColumnIndex("text"));
        priority = cursor.getString(cursor.getColumnIndex("priority"));

        // set the title, reminder, values in the EditTexts
        titleEditText.setText(title);
        reminderEditText.setText(reminder);

    }

    /**
     * This method further initializes the Action Bar of the activity.
     * It gets the code (XML) in the menu resource file and incorporates it
     * into the Action Bar.
     * @param menu menu resource file for the activity
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_reminder, menu);
        return true;
    }

    /**
     * This method gets called when a menu item in the overflow menu is
     * selected and it controls what happens when the menu item is selected.
     * @param item selected menu item in the overflow menu
     * @return true if menu item is selcted, else false
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // get the id of menu item selected
        switch (item.getItemId()){
            case R.id.action_home :
                // initialize an Intent for the MainActivity and start it
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_create_reminder :
                // initialize an Intent for the CreateList Activity and start it
                intent = new Intent(this, CreateReminder.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This method gets called when the delete button in the Acdtion Bar of the
     * View Item actiivty gets clicked. It declares a row in the shoppinglistitem
     * table
     * @param menuItem delete item menu item
     */
    public void deleteReminder(MenuItem menuItem) {

        // delete shopping list item from database
        dbHandler.deleteReimnder((int) id);

        // display a toast "Item deleted!"
        Toast.makeText(this, "Reminder Deleted!", Toast.LENGTH_LONG).show();
    }
}


