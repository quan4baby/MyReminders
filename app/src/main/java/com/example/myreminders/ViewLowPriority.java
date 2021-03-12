package com.example.myreminders;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;

public class ViewLowPriority extends AppCompatActivity {

    // declare Intent
    Intent intent;

    // declare a DBHandler
    DBHandler dbHandler;

    // declare a LowPriority CursorAdapter
    CursorAdapter viewLowPriorityCursorAdapter;

    // declare a ListView
    ListView viewLowPriorityListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_low_priority);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // initialize DBHandler
        dbHandler = new DBHandler(this, null);

        // initialize ListView
        viewLowPriorityListView = (ListView) findViewById(R.id.viewLowPriorityListView);

        // initialize LowPriority CursorAdapter
        viewLowPriorityCursorAdapter = new LowPriority(this, dbHandler.getMyRemindersMediumPriority("Low"), 0);

        // set LowPriority CursorAdapter on the ListView
        viewLowPriorityListView.setAdapter(viewLowPriorityCursorAdapter);

        // set OnItemClickListener on the ListView
        viewLowPriorityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * This method gets called when a item in the listView is clicked.
             * @param adapterView viewLowPriorityListView
             * @param view MainActivity view
             * @param position position of the clicked item
             * @param id database id of the clicked item
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                // initialize Intent for the ViewList Activity
                intent = new Intent(ViewLowPriority.this, ViewLowPriority.class);

                // put the database id in the Intent
                intent.putExtra("_id", id);

                // start the ViewLowPriority Activity
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
        getMenuInflater().inflate(R.menu.menu_low_priority, menu);
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