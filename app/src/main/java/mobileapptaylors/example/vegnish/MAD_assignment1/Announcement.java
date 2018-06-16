package mobileapptaylors.example.vegnish.MAD_assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class Announcement extends AppCompatActivity {

    private ArrayList<AnnouncementModel> arrayList;
    private AnnouncementAdapter adapter;
    private ListView announcementList;

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), Home.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mobileapptaylors.example.vegnish.MAD_assignment1.R.layout.activity_announcement);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        announcementList = (ListView) findViewById(mobileapptaylors.example.vegnish.MAD_assignment1.R.id.announcementList);
        arrayList = new ArrayList<>();
        arrayList.add(new AnnouncementModel("Before Examination", "For Non-MCQ examination", mobileapptaylors.example.vegnish.MAD_assignment1.R.raw.no_mcq));
        arrayList.add(new AnnouncementModel("Before Examination", "For MCQ examination", mobileapptaylors.example.vegnish.MAD_assignment1.R.raw.no_mcq));
        arrayList.add(new AnnouncementModel("During Examination", "Reminder (20mins before end)", mobileapptaylors.example.vegnish.MAD_assignment1.R.raw.reminder));
        arrayList.add(new AnnouncementModel("Complete", "Upon Completion!", mobileapptaylors.example.vegnish.MAD_assignment1.R.raw.time_up));

        adapter = new AnnouncementAdapter(this, mobileapptaylors.example.vegnish.MAD_assignment1.R.layout.custom_announcement, arrayList);
        announcementList.setAdapter(adapter);


    }
}
