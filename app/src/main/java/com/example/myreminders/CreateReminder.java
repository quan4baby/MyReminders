package com.example.myreminders;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreateReminder extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // declare Intent
    Intent intent;

    // declare EditTexts
    EditText titleEditText;
    EditText reminderEditText;

    // declare Spinner
    Spinner prioritySpinner;

    // declare String to store priority selected in Spinner
    String priority;

    // declare DBHandler
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reminder);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize EditTexts
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        reminderEditText = (EditText) findViewById(R.id.reminderEditText);


        // initialize Spinner
        prioritySpinner = (Spinner) findViewById(R.id.prioritySpinner);

        // initialize  ArrayAdapter with values in priorities string-array
        // and stylize it with style defined by simple_spinner_item
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priorities, android.R.layout.simple_spinner_item);

        // futher sylize the ArrayAdpater
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        // set ArrayAdpater on Spinner
        prioritySpinner.setAdapter(adapter);

        // register an OnItemSelectedListener to SPinner
        prioritySpinner.setOnItemSelectedListener(this);

        // initialize DBHandler
        dbHandler = new DBHandler(this, null);

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
        getMenuInflater().inflate(R.menu.menu_create_reminder, menu);
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
        switch (item.getItemId()) {
            case R.id.action_home:
                // initialize an Intent for the MainActivity and start it
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_create_reminder:
                // initialize an Intent for the CreateList Activity and start it
                intent = new Intent(this, CreateReminder.class);
                startActivity(intent);
                return true;
            case R.id.action_high_priority:
                // initialize an Intent for the CreateList Activity and start it
                intent = new Intent(this, ViewHighPriority.class);
                startActivity(intent);
                return true;
            case R.id.action_medium_priority:
                // initialize an Intent for the CreateList Activity and start it
                intent = new Intent(this, ViewMediumPriority.class);
                startActivity(intent);
                return true;
            case R.id.action_low_priority:
                // initialize an Intent for the CreateList Activity and start it
                intent = new Intent(this, ViewLowPriority.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

        /**
         * This method gets called when the add FLoating Action Button is clicked.
         * It starts the CreateList Activity
         * @param view MainActivity view
         */
        public void openCreateList(View view) {
            // initialize an Intent for the CreateList Activity and start it
            intent = new Intent(this, CreateReminder.class);
            startActivity(intent);
        }


    /**
     * This method is called when the add button in the Action Bar gets clicked.
     * @param menuItem add list menu item
     */
    public void createList (MenuItem menuItem) {

        // get data input in EditTexts and store it in Strings
        String title = titleEditText.getText().toString();
        String text = reminderEditText.getText().toString();

        // trim Strings and see if they're equal to empty String
        if (title.trim().equals("") || text.trim().equals("") || priority.trim().equals("")){
            // display "Please enter a title and text!" toast
            Toast.makeText(this, "Please enter a title, text and priority!",
                    Toast.LENGTH_LONG).show();
        } else {
            // add reminders into database
            dbHandler.addMyReminders(title, text, priority);

            // display "Reminder created!" toast
            Toast.makeText(this, "" +
                            "Reminder created!",
                    Toast.LENGTH_LONG).show();
        }

    }

    /**
     * This medthod gets called when on item in the Spinner is selected
     * @param parent the Spinner AdapterView
     * @param view CreateReminder view
     * @param position position of reminder that was selected in the Spinner
     * @param id database id of reminder that was selected in the Spinner
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    priority = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}