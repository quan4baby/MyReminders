package com.example.myreminders;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import static com.example.myreminders.R.id.prioritySpinner;
import static com.example.myreminders.R.id.priorityTextView;

public class ViewHighPriority extends AppCompatActivity {

    // declare Intent
    Intent intent;

    // declare a DBHandler
    DBHandler dbHandler;

    // declare a  HighPriority CursorAdapter
    CursorAdapter viewHighPriorityCursorAdapter;

    // declare a ListView
    ListView viewHighPriorityListView;

    /**
     * This method initializes the Action Bar and View of the activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // initialize the View and Action Bar of the MainActivty
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_high_priority);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize DBHandler
        dbHandler = new DBHandler(this, null);


        // initialize ListView
        viewHighPriorityListView = (ListView) findViewById(R.id.viewHighPriorityListView);

        // initialize HighPriority CursorAdapter
        viewHighPriorityCursorAdapter = new HighPriority(this, dbHandler.getMyRemindersHighPriority("High"), 0);

        // set HighPriority CursorAdapter on the ListView
        viewHighPriorityListView.setAdapter(viewHighPriorityCursorAdapter);

        // set OnItemClickListener on the ListView
        viewHighPriorityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * This method gets called when a item in the listView is clicked.
             * @param adapterView viewHighPriorityListView
             * @param view MainActivity view
             * @param position position of the clicked item
             * @param id database id of the clicked item
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                // initialize Intent for the ViewList Activity
                intent = new Intent(ViewHighPriority.this, ViewHighPriority.class);

                // put the database id in the Intent
                intent.putExtra("_id", id);

                // start the ViewHighPriority Activity
                startActivity(intent);
            }
        });

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
        getMenuInflater().inflate(R.menu.menu_high_priority, menu);
        return true;
    }

    /**
     * This method gets called when a menu item in the overflow menu is
     * selected and it controls what happens when the menu item is selected.
     * @param item selected menu item in the overflow menu
     * @return true if menu item is selected, else false
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
                // initialize an Intent for the CreateReminder Activity and start it
                intent = new Intent(this, CreateReminder.class);
                startActivity(intent);
                return true;
            case R.id.action_high_priority:
                // initialize an Intent for the ViewHighPriority Activity and start it
                intent = new Intent(this, ViewHighPriority.class);
                startActivity(intent);
                return true;
            case R.id.action_medium_priority:
                // initialize an Intent for the ViewMediumPriority Activity and start it
                intent = new Intent(this, ViewMediumPriority.class);
                startActivity(intent);
                return true;
            case R.id.action_low_priority:
                // initialize an Intent for the ViewLowPriority Activity and start it
                intent = new Intent(this, ViewLowPriority.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
