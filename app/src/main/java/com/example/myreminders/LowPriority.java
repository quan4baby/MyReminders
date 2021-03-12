package com.example.myreminders;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import static com.example.myreminders.R.id.dateTextView;
import static com.example.myreminders.R.id.titleTextView;

public class LowPriority extends CursorAdapter {

    /**
     * Initialize a LowPriority CursorAdapter.
     * @param context reference to the MainAcivity that initialzies the MyReminders CursorAdapter
     * @param c reference to the Cursor that contains the data selected
     *          from the my reminders table
     * @param flags determines special behavior of the CursorAdapter. We
     *              don't want any special behavior so we will always
     *              pass 0.
     */
    public LowPriority(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    /**
     * Make a new View to hold the data in the Cursor.
     * @param context  reference to the Activity that initializes the
     *      *                MyReminders CursorAdapter
     * @param cursor reference to the Cursor that contains the data selected
     *      *          from the myreminders table
     * @param parent reference to viewLowPriorityListView that will contain the new
     *               View created by this method
     * @return reference to the new View
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.li_view_low_priority, parent, false);
    }

    /**
     * Bind new View to data in Cursor.
     * @param view reference to new View
     * @param context reference to the Activity that initializes the
     *                 LowPriority CursorAdapter
     * @param cursor reference to the Cursor that contains the data selected
     *                from the myreminders table
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView) view.findViewById(titleTextView)).
                setText(cursor.getString(cursor.getColumnIndex("title")));
        ((TextView) view.findViewById(dateTextView)).
                setText(cursor.getString(cursor.getColumnIndex("date")));
    }
    }
