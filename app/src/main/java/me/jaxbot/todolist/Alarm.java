package me.jaxbot.todolist;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;


public class Alarm extends BroadcastReceiver {
    static int i = 0;

    @Override
    public void onReceive(Context context, Intent intent) {

        String m = intent.getStringExtra("ConstantTitle");

        Log.i("helllo", m);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentTitle("My Notification")
                .setAutoCancel(true)
                .setContentText("Alarm !!! " + m);


        Intent resultIntent = new Intent(context, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, i, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Log.i("pppp", i + "");
        i++;
        mBuilder.setContentIntent(resultPendingIntent);


        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(i, mBuilder.build());

    }

}
