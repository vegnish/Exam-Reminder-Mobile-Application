package com.example.vegnish.assignment1;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.facebook.stetho.Stetho;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

public class Welcome extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setTitle("Welcome Screen");
        Stetho.initializeWithDefaults(this);
        RecyclerView recList = (RecyclerView) findViewById(R.id.next_slot);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        TextView welcome = (TextView) findViewById(R.id.welcome_greeting);
        TextView time_now = (TextView) findViewById(R.id.current_time);
        TextView total_slots = (TextView) findViewById(R.id.slots_total);
        int num_slots_today=0;

        DateFormat df = new SimpleDateFormat("HH:mm");
        String current_time = df.format(Calendar.getInstance().getTime());
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
//        System.out.println("current time"+ current_time);
//        System.out.println("current time2"+System.currentTimeMillis());

        if(timeOfDay >= 0 && timeOfDay < 12)
            welcome.setText("Good Morning");
        else if(timeOfDay >= 12 && timeOfDay <= 16)
            welcome.setText("Good Afternoon");
        else if(timeOfDay >= 16 && timeOfDay <= 21)
            welcome.setText("Good Evening");
        else if(timeOfDay >= 21 && timeOfDay <= 24)
            welcome.setText("Good Night");
        time_now.setText("  "+current_time);

        //DB object creation
        final SQLiteHelper sQLiteHelper = new SQLiteHelper(Welcome.this);
        ArrayList<SlotsModel> allSlots = sQLiteHelper.getAllRecords();
        ArrayList<SlotsModel> future_slots = new ArrayList<>();
        ArrayList<SlotsModel> next_slot = new ArrayList<>();

        sortList(allSlots);
        Iterator<SlotsModel> iter = allSlots.iterator();
        while (iter.hasNext()) {
            SlotsModel p = iter.next();
            final long time = p.getTime_();
            long currentTime = System.currentTimeMillis();
            if (time < currentTime)
                iter.remove();
            else{
                //To check how many slots are present Today
                Date date = new Date(time);
                SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
//                System.out.println("date of slots"+dateformat.format(date));
                String current_date = dateformat.format(Calendar.getInstance().getTime());
                if(current_date.equals(dateformat.format(date))) {
                    num_slots_today++;
                    future_slots.add(p);
                }
            }
        }

        total_slots.setText("You have "+num_slots_today+" slots TODAY!");
        for(int num=0; num<future_slots.size();num++)
            next_slot.add(future_slots.get(num));

        SlotsAdapter slotsAdapters = new SlotsAdapter(next_slot);
        recList.setAdapter(slotsAdapters);


    }
}
