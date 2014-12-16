package com.thoughtworks.karthikintern.reminderapp;
import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.thoughtworks.karthikintern.reminderapp.JsonParser.Jparser;

import org.json.JSONException;
import org.json.JSONObject;

import static android.support.v4.content.WakefulBroadcastReceiver.completeWakefulIntent;

/**
 * Created by karthikintern on 9/24/14.
 */
public class MedStockScheduler extends IntentService {
    public MedStockScheduler(){
        super("MedStockScheduler");
    }
    public static final String TAG="Scheduling Service";
    SharedPreferences pref;
    String phno,sjson;
    SharedPreferences.Editor e;
    private static String url = "http://10.0.3.2:8082/pullMed";

    @Override
    protected void onHandleIntent(Intent intent) {
        Jparser jParser = new Jparser();
        // Getting JSON from URL
        try {
            pref = getSharedPreferences("AppPref", MODE_PRIVATE);
            phno = pref.getString("phno", null);
            String jsonstr = (String) pref.getString("medjson", null);
            JSONObject jsonmed = new JSONObject(jsonstr);
        }catch(JSONException ew){

        }
        e.commit();
        Log.i("Wakeful Completed","Wakeful Complete");
        completeWakefulIntent(intent);
        stockLow();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void stockLow() {
        NotificationCompat.Builder notification;
        PendingIntent pIntent;
        NotificationManager manager;
        Intent resultIntent;
        TaskStackBuilder stackBuilder;
        notification = new NotificationCompat.Builder(MedStockScheduler.this);
        //Title for Notification
        //("Thulasi Pharmacy on Mobile");
        notification.setContentTitle("Thulasi Pharmacy on Mobile");
        //Message in the Notification
        notification.setContentText("Stock of Medication(s) is low");
        //Alert shown when Notification is received
        notification.setTicker("Thulasi Pharmacy  - Notification");
        //Icon to be set on Notification
        notification.setSmallIcon(R.drawable.ic_launcher);
        //Creating new Stack Builder
        stackBuilder = TaskStackBuilder.create(MedStockScheduler.this);
        stackBuilder.addParentStack(PresView.class);
        //Intent which is opened when notification is clicked
        resultIntent = new Intent(MedStockScheduler.this, PresView.class);
        stackBuilder.addNextIntent(resultIntent);
        pIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pIntent);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, notification.build());
    }
}

