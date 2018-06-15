package mobileapptaylors.example.vegnish.MAD_assignment1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class SetAlarm {

    //Sort list in ascending order according to date
    private void sortList(ArrayList<SlotsModel> allSlots) {
        Collections.sort(allSlots, new Comparator<SlotsModel>() {
            public int compare(SlotsModel date1, SlotsModel date2) {
                // avoiding NullPointerException in case name is null
                Long date_1 = new Long(date1.getDate_());
                Long date_2 = new Long(date2.getDate_());

                //for descending order swap return items
                return date_1.compareTo(date_2);
            }
        });
    }

    public void alarmSetter(Context context, long alarmTime) {
        final SQLiteHelper alarmDB = new SQLiteHelper(MainActivity.appContext);
        ArrayList<SlotsModel> allSlots = alarmDB.getAllRecords();

        sortList(allSlots);
        Iterator<SlotsModel> iter = allSlots.iterator();
        while (iter.hasNext()) {
            SlotsModel p = iter.next();
            final long time = p.getTime_();
            long currentTime = System.currentTimeMillis();
            if (time < currentTime) iter.remove();
        }

        //This intent, will execute the AlarmBroadcaster when the alarm is triggered
        Intent alertIntent = new Intent(context, AlarmBroadcaster.class);
        int count = 0;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Iterator<SlotsModel> iter2 = allSlots.iterator();
        while (iter2.hasNext()) {
            SlotsModel p = iter2.next();
            long time = p.getTime_();
            time -= 3600000;
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, count, alertIntent, 0);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pendingIntent);
            count++;

        }


    }

}
