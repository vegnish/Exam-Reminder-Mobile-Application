package com.example.vegnish.assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class HistorySlots extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //Sort list in descending order according to date
    private void sortList(ArrayList<SlotsModel> allSlots) {
        Collections.sort(allSlots, new Comparator<SlotsModel>() {
            public int compare(SlotsModel date1, SlotsModel date2) {
                // avoiding NullPointerException in case name is null
                Long date_1 = new Long(date1.getDate_());
                Long date_2 = new Long(date2.getDate_());

                //for descending order swap return items
                return date_2.compareTo(date_1);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_slots);
        setTitle("History Slots");
        Stetho.initializeWithDefaults(this);
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);


        //DB object creation
        final SQLiteHelper sQLiteHelper = new SQLiteHelper(HistorySlots.this);
        ArrayList<SlotsModel> allSlots = sQLiteHelper.getAllRecords();

        sortList(allSlots);
        Iterator<SlotsModel> iter = allSlots.iterator();
        while (iter.hasNext()) {
            SlotsModel p = iter.next();
            final long time = p.getTime_();
            long currentTime = System.currentTimeMillis();
            if (time > currentTime) iter.remove();
        }

        SlotsAdapter slotsAdapters = new SlotsAdapter(allSlots);
        recList.setAdapter(slotsAdapters);

    }

}


