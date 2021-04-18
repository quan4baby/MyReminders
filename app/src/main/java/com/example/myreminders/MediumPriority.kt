package com.example.myreminders

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView
import com.example.myreminders.R.id

class MediumPriority
/**
 * Initialize a MediumPriority CursorAdapter.
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
     * *                MediumPriority CursorAdapter
     * @param cursor reference to the Cursor that contains the data selected
     * *          from the myreminders table
     * @param parent reference to viewMediumPriorityListView that will contain the new
     * View created by this method
     * @return reference to the new View
     */
    override fun newView(context: Context, cursor: Cursor, parent: ViewGroup): View {
        return LayoutInflater.from(context).inflate(R.layout.li_view_medium_priority, parent, false)
    }

    /**
     * Bind new View to data in Cursor.
     * @param view reference to new View
     * @param context reference to the Activity that initializes the
     * MediumPriority CursorAdapter
     * @param cursor reference to the Cursor that contains the data selected
     * from the myreminders table
     */
    override fun bindView(view: View, context: Context, cursor: Cursor) {
        (view.findViewById<View>(id.titleTextView) as TextView).text = cursor.getString(cursor.getColumnIndex("title"))
        (view.findViewById<View>(id.dateTextView) as TextView).text = cursor.getString(cursor.getColumnIndex("date"))
    }
}