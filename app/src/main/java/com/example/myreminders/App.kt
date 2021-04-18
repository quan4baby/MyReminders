package com.example.myreminders

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

/**
 * This class creates a Notification Channel for MyReminders. Notification Channels became
 * necessary starting with Android Oreo (API 26) to be able to show Notifications. The
 * Notification Channel for MyReminders will be created one time as soon as the application
 * starts and will be available for all other Activity classes to use.
 */
class App : Application() {
    // override the onCreate method
    override fun onCreate() {
        super.onCreate()

        // call method that creates Notification Channel for MyReminders
        createNotificationChannel()
    }

    /**
     * This method creates the Notification Channel for MyReminders
     */
    private fun createNotificationChannel() {
        // check if Android Oreo (API 26) or higher because Notification Channel weren't
        // available on lower versions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // initialize Notification Channel - must give it an Id, name and importance
            // level
            val channelreminder = NotificationChannel(
                    CHANNEL_REMINDER_ID,
                    "Channel Reminder",
                    NotificationManager.IMPORTANCE_DEFAULT
            )

            // customize the Notification Channel - set its description
            channelreminder.description = "This is the My Reminders Channel"

            // initialize a Notification manager
            val manager = getSystemService(NotificationManager::class.java)

            // create Reminder Notification Channel
            manager.createNotificationChannel(channelreminder)
        }
    }

    companion object {
        // declare and initialize a Channel Id
        const val CHANNEL_REMINDER_ID = "channelreminder"
    }
}