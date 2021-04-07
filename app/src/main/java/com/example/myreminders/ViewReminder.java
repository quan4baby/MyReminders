package com.example.myreminders;

import android.app.Notification;
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
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.example.myreminders.App.CHANNEL_REMINDER_ID;

public class ViewReminder extends AppCompatActivity {

    // declare a DBHandler
    DBHandler dbHandler;

    // declare an Intent
    Intent intent;

    // declare EditTexts
    EditText titleEditText;
    EditText reminderEditText;
    EditText priorityEditText;

    // declare a Bundle and a long used to get and store the data sent from
    // the MainActivity
    Bundle bundle;
    long id;

    // declare Notification Manager used to show (display) the notification
    NotificationManagerCompat notificationManagerCompat;


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
        priorityEditText = (EditText) findViewById(R.id.priorityEditText);

        // disable EditTexts
        titleEditText.setEnabled(false);
        reminderEditText.setEnabled(false);
        priorityEditText.setEnabled(false);

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
        priorityEditText.setText(priority);

        // initialize the Notification Manager
        notificationManagerCompat = NotificationManagerCompat.from(this);


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

        if((dbHandler.getMyRemindersHighPriority((String) this.priority)).getCount() == 0 && this.priority.equals("High")){
            // initialize Notification
            Notification notification = new NotificationCompat.Builder(this,
                    CHANNEL_REMINDER_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("MyReminders")
                    .setContentText("High priority reminders deleted!").build();

            // show notification
            notificationManagerCompat.notify(1, notification);

        }

        if((dbHandler.getMyRemindersMediumPriority((String) this.priority)).getCount() == 0 && this.priority.equals("Medium")){
            // initialize Notification
            Notification notification = new NotificationCompat.Builder(this,
                    CHANNEL_REMINDER_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("MyReminders")
                    .setContentText("Medium priority reminders deleted!").build();

            // show notification
            notificationManagerCompat.notify(1, notification);

        }

        if((dbHandler.getMyRemindersLowPriority((String) this.priority)).getCount() == 0 && this.priority.equals("Low")){
            // initialize Notification
            Notification notification = new NotificationCompat.Builder(this,
                    CHANNEL_REMINDER_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("MyReminders")
                    .setContentText("Low priority reminders deleted!").build();

            // show notification
            notificationManagerCompat.notify(1, notification);

        }
    }
}


