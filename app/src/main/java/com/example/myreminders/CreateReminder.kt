package com.example.myreminders

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.text.DateFormat
import java.util.*

class CreateReminder : AppCompatActivity(), OnItemSelectedListener {
    // declare Intent
    // var intent: Intent? = null

    // declare EditTexts
    var titleEditText: EditText? = null
    var reminderEditText: EditText? = null

    // declare Spinner
    var prioritySpinner: Spinner? = null

    // declare String to store priority selected in Spinner
    var priority: String? = null

    // declare DBHandler
    var dbHandler: DBHandler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_reminder)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // initialize EditTexts
        titleEditText = findViewById<View>(R.id.titleEditText) as EditText
        reminderEditText = findViewById<View>(R.id.reminderEditText) as EditText


        // initialize Spinner
        prioritySpinner = findViewById<View>(R.id.prioritySpinner) as Spinner

        // initialize  ArrayAdapter with values in priorities string-array
        // and stylize it with style defined by simple_spinner_item
        val adapter = ArrayAdapter.createFromResource(this,
                R.array.priorities, android.R.layout.simple_spinner_item)

        // futher sylize the ArrayAdpater
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)

        // set ArrayAdpater on Spinner
        prioritySpinner!!.adapter = adapter

        // register an OnItemSelectedListener to SPinner
        prioritySpinner!!.onItemSelectedListener = this

        // initialize DBHandler
        dbHandler = DBHandler(this, null)
    }

    /**
     * This method further initializes the Action Bar of the activity.
     * It gets the code (XML) in the menu resource file and incorporates it
     * into the Action Bar.
     * @param menu menu resource file for the activity
     * @return true
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_create_reminder, menu)
        return true
    }

    /**
     * This method gets called when a menu item in the overflow menu is
     * selected and it controls what happens when the menu item is selected.
     * @param item selected menu item in the overflow menu
     * @return true if menu item is selcted, else false
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // get the id of menu item selected
        return when (item.itemId) {
            R.id.action_home -> {
                // initialize an Intent for the MainActivity and start it
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_create_reminder -> {
                // initialize an Intent for the CreateList Activity and start it
                intent = Intent(this, CreateReminder::class.java)
                startActivity(intent)
                true
            }
            R.id.action_high_priority -> {
                // initialize an Intent for the CreateList Activity and start it
                intent = Intent(this, ViewHighPriority::class.java)
                startActivity(intent)
                true
            }
            R.id.action_medium_priority -> {
                // initialize an Intent for the CreateList Activity and start it
                intent = Intent(this, ViewMediumPriority::class.java)
                startActivity(intent)
                true
            }
            R.id.action_low_priority -> {
                // initialize an Intent for the CreateList Activity and start it
                intent = Intent(this, ViewLowPriority::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * This method gets called when the add FLoating Action Button is clicked.
     * It starts the CreateList Activity
     * @param view MainActivity view
     */
    fun openCreateList(view: View?) {
        // initialize an Intent for the CreateList Activity and start it
        intent = Intent(this, CreateReminder::class.java)
        startActivity(intent)
    }

    /**
     * This method is called when the add button in the Action Bar gets clicked.
     * @param menuItem add list menu item
     */
    fun createList(menuItem: MenuItem?) {

        // get data input in EditTexts and store it in Strings
        val title = titleEditText!!.text.toString()
        val text = reminderEditText!!.text.toString()
        val calendar = Calendar.getInstance()
        val currentDate = DateFormat.getDateInstance().format(calendar.time)


        // trim Strings and see if they're equal to empty String
        if (title.trim { it <= ' ' } == "" || text.trim { it <= ' ' } == "" || priority!!.trim { it <= ' ' } == "") {
            // display "Please enter a title and text!" toast
            Toast.makeText(this, "Please enter a title, text and priority!",
                    Toast.LENGTH_LONG).show()
        } else {
            // add reminders into database
            dbHandler!!.addMyReminders(title, text, priority, currentDate)

            // display "Reminder created!" toast
            Toast.makeText(this,
                    "Reminder created!", Toast.LENGTH_LONG).show()
        }
    }

    /**
     * This medthod gets called when on item in the Spinner is selected
     * @param parent the Spinner AdapterView
     * @param view CreateReminder view
     * @param position position of reminder that was selected in the Spinner
     * @param id database id of reminder that was selected in the Spinner
     */
    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        priority = parent.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}