package com.afrobaskets.App.activity;

/**
 * Created by HP-PC on 12/25/2017.
 */

public class Config {
    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "globals";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationCompletes";
    public static final String PUSH_NOTIFICATION = "pushNotifications";

    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 1000;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 1011;

    public static final String SHARED_PREF = "ah_firebase_id";
}
