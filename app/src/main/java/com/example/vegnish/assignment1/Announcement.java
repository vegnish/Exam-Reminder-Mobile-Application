package com.example.vegnish.assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Announcement extends AppCompatActivity {

    private ArrayList<AnnouncementModel> arrayList;
    private AnnouncementAdapter adapter;
    private ListView announcementList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        announcementList = (ListView) findViewById(R.id.announcementList);
        arrayList = new ArrayList<>();
        arrayList.add(new AnnouncementModel("Before Examination", "For Non-MCQ examination", R.raw.no_mcq));
        arrayList.add(new AnnouncementModel("Before Examination", "For MCQ examination", R.raw.no_mcq));
        arrayList.add(new AnnouncementModel("During Examination", "Reminder (20mins before end)", R.raw.reminder));
        arrayList.add(new AnnouncementModel("Complete", "Upon Completion!", R.raw.time_up));

        adapter = new AnnouncementAdapter(this,R.layout.custom_announcement, arrayList);
        announcementList.setAdapter(adapter);


    }
}
