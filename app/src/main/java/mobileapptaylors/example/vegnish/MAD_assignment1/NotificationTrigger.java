package mobileapptaylors.example.vegnish.MAD_assignment1;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;

public class NotificationTrigger extends IntentService {

    private static final String ALARM = "mobileapptaylors.example.vegnish.MAD_assignment1.ALARM";

    public NotificationTrigger() {
        super("NotificationTrigger");
    }

    public static void starNotification(Context context) {
        Intent intent = new Intent(context, NotificationTrigger.class);
        intent.setAction(ALARM);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ALARM.equals(action)) {
                handleAlarm();
            }
        }
    }

    private void handleAlarm() {
        PendingIntent notificationIntent = PendingIntent.getActivity(this, 0, new Intent(this, Welcome.class), 0);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(mobileapptaylors.example.vegnish.MAD_assignment1.R.drawable.ic_access_alarms_black_24dp)
                .setContentTitle("Next Exam Slot")
                .setTicker(this.getString(mobileapptaylors.example.vegnish.MAD_assignment1.R.string.app_name))
                .setContentText("You have a slot in one hour!")
                .setDefaults(Notification.DEFAULT_ALL);

        notificationBuilder.setContentIntent(notificationIntent);

        notificationBuilder.setDefaults(NotificationCompat.DEFAULT_VIBRATE);

        notificationBuilder.setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

//        notificationManager.notify(0, notificationBuilder.build());
        Notification notification = notificationBuilder.build();
        notification.sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (notification.sound == null)
        {
            notification.sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        notificationManager.notify(0, notification);
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "Tag");
        wakeLock.acquire();
        wakeLock.release();
    }


}

