package com.example.myreminders

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ViewReminder : AppCompatActivity() {
    // declare a DBHandler
    var dbHandler: DBHandler? = null

    // declare an Intent
    // var intent: Intent? = null

    // declare EditTexts
    var titleEditText: EditText? = null
    var reminderEditText: EditText? = null
    var priorityEditText: EditText? = null

    // declare a Bundle and a long used to get and store the data sent from
    // the MainActivity
    var bundle: Bundle? = null
    var id: Long = 0

    // declare Notification Manager used to show (display) the notification
    var notificationManagerCompat: NotificationManagerCompat? = null

    // declare Strings to store the shopping list item's name, price, and quantity
    var title: String? = null
    var reminder: String? = null
    var priority: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_reminder)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // initialize Bundle
        bundle = getIntent().extras

        // get the reminder id in the bundle and store it in Long
        id = bundle!!.getLong("_id")

// initialize DBHandler
        dbHandler = DBHandler(this, null)

        // initialize EditTexts
        titleEditText = findViewById<View>(R.id.titleEditText) as EditText
        reminderEditText = findViewById<View>(R.id.reminderEditText) as EditText
        priorityEditText = findViewById<View>(R.id.priorityEditText) as EditText

        // disable EditTexts
        titleEditText!!.isEnabled = false
        reminderEditText!!.isEnabled = false
        priorityEditText!!.isEnabled = false

        // call the DBHandler method getMyReminders
        val cursor = dbHandler!!.getReminder(id.toInt())

        // move to the first row in the Cursor
        cursor.moveToFirst()

        // get the title, reminder in the Cursor and store them in Strings
        title = cursor.getString(cursor.getColumnIndex("title"))
        reminder = cursor.getString(cursor.getColumnIndex("text"))
        priority = cursor.getString(cursor.getColumnIndex("priority"))

        // set the title, reminder, values in the EditTexts
        titleEditText!!.setText(title)
        reminderEditText!!.setText(reminder)
        priorityEditText!!.setText(priority)

        // initialize the Notification Manager
        notificationManagerCompat = NotificationManagerCompat.from(this)
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
        menuInflater.inflate(R.menu.menu_view_reminder, menu)
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
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * This method gets called when the delete button in the Acdtion Bar of the
     * View Item actiivty gets clicked. It declares a row in the shoppinglistitem
     * table
     * @param menuItem delete item menu item
     */
    fun deleteReminder(menuItem: MenuItem?) {

        // delete shopping list item from database
        dbHandler!!.deleteReimnder(id.toInt())

        // display a toast "Item deleted!"
        Toast.makeText(this, "Reminder Deleted!", Toast.LENGTH_LONG).show()
        if (dbHandler!!.getMyRemindersHighPriority(priority!!).count == 0 && priority == "High") {
            // initialize Notification
            val notification = NotificationCompat.Builder(this,
                    App.CHANNEL_REMINDER_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("MyReminders")
                    .setContentText("High priority reminders deleted!").build()

            // show notification
            notificationManagerCompat!!.notify(1, notification)
        }
        if (dbHandler!!.getMyRemindersMediumPriority(priority!!).count == 0 && priority == "Medium") {
            // initialize Notification
            val notification = NotificationCompat.Builder(this,
                    App.CHANNEL_REMINDER_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("MyReminders")
                    .setContentText("Medium priority reminders deleted!").build()

            // show notification
            notificationManagerCompat!!.notify(1, notification)
        }
        if (dbHandler!!.getMyRemindersLowPriority(priority!!).count == 0 && priority == "Low") {
            // initialize Notification
            val notification = NotificationCompat.Builder(this,
                    App.CHANNEL_REMINDER_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("MyReminders")
                    .setContentText("Low priority reminders deleted!").build()

            // show notification
            notificationManagerCompat!!.notify(1, notification)
        }
    }
}