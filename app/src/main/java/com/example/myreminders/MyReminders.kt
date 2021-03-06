package com.example.myreminders

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView

/**
 * The MyReminders class will map the data selected from the my reminders table,
 * in the Cursor, to the li_my_reminders layout resource.
 */
class MyReminders
/**
 * Initialize a MyReminders CursorAdapter.
 * @param context reference to the MainAcivity that initialzies the MyReminders CursorAdapter
 * @param c reference to the Cursor that contains the data selected
 * from the my reminders table
 * @param flags determines special behavior of the CursorAdapter. We
 * don't want any special behavior so we will always
 * pass 0.
 */
(context: Context?, c: Cursor?, flags: Int) : CursorAdapter(context, c, flags) {
    /**
     * Make a new View to hold the data in the Cursor.
     * @param context  reference to the Activity that initializes the
     * *                MyReminders CursorAdapter
     * @param cursor reference to the Cursor that contains the data selected
     * *          from the myreminders table
     * @param parent reference to myremindersListView that will contain the new
     * View created by this method
     * @return reference to the new View
     */
    override fun newView(context: Context, cursor: Cursor, parent: ViewGroup): View {
        return LayoutInflater.from(context).inflate(R.layout.li_my_reminders, parent, false)
    }

    /**
     * Bind new View to data in Cursor.
     * @param view reference to new View
     * @param context reference to the Activity that initializes the
     * MyReminders CursorAdapter
     * @param cursor reference to the Cursor that contains the data selected
     * from the myreminders table
     */
    override fun bindView(view: View, context: Context, cursor: Cursor) {
        (view.findViewById<View>(R.id.titleTextView) as TextView).text = cursor.getString(cursor.getColumnIndex("title"))
        (view.findViewById<View>(R.id.dateTextView) as TextView).text = cursor.getString(cursor.getColumnIndex("date"))
    }
}