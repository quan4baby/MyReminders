package com.example.myreminders

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.CursorAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myreminders.ViewLowPriority

class ViewLowPriority : AppCompatActivity() {
    // declare Intent
    // var intent: Intent? = null

    // declare a DBHandler
    var dbHandler: DBHandler? = null

    // declare a LowPriority CursorAdapter
    var viewLowPriorityCursorAdapter: CursorAdapter? = null

    // declare a ListView
    var viewLowPriorityListView: ListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_low_priority)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        // initialize DBHandler
        dbHandler = DBHandler(this, null)

        // initialize ListView
        viewLowPriorityListView = findViewById<View>(R.id.viewLowPriorityListView) as ListView

        // initialize LowPriority CursorAdapter
        viewLowPriorityCursorAdapter = LowPriority(this, dbHandler!!.getMyRemindersMediumPriority("Low"), 0)

        // set LowPriority CursorAdapter on the ListView
        viewLowPriorityListView!!.adapter = viewLowPriorityCursorAdapter

        // set OnItemClickListener on the ListView
        viewLowPriorityListView!!.onItemClickListener = OnItemClickListener { adapterView, view, position, id ->
            /**
             * This method gets called when a item in the listView is clicked.
             * @param adapterView viewLowPriorityListView
             * @param view MainActivity view
             * @param position position of the clicked item
             * @param id database id of the clicked item
             */
            /**
             * This method gets called when a item in the listView is clicked.
             * @param adapterView viewLowPriorityListView
             * @param view MainActivity view
             * @param position position of the clicked item
             * @param id database id of the clicked item
             */

            // initialize Intent for the ViewList Activity
            intent = Intent(this@ViewLowPriority, ViewLowPriority::class.java)

            // put the database id in the Intent
            intent!!.putExtra("_id", id)

            // start the ViewLowPriority Activity
            startActivity(intent)
        }
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
        menuInflater.inflate(R.menu.menu_low_priority, menu)
        return true
    }

    /**
     * This method gets called when a menu item in the overflow menu is
     * selected and it controls what happens when the menu item is selected.
     * @param item selected menu item in the overflow menu
     * @return true if menu item is selected, else false
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
                // initialize an Intent for the CreateReminder Activity and start it
                intent = Intent(this, CreateReminder::class.java)
                startActivity(intent)
                true
            }
            R.id.action_high_priority -> {
                // initialize an Intent for the ViewHighPriority Activity and start it
                intent = Intent(this, ViewHighPriority::class.java)
                startActivity(intent)
                true
            }
            R.id.action_medium_priority -> {
                // initialize an Intent for the ViewMediumPriority Activity and start it
                intent = Intent(this, ViewMediumPriority::class.java)
                startActivity(intent)
                true
            }
            R.id.action_low_priority -> {
                // initialize an Intent for the ViewLowPriority Activity and start it
                intent = Intent(this, ViewLowPriority::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}