package com.example.vegnish.assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.stetho.Stetho;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ViewSlots extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_slots);
        setTitle("View Slots");
        Stetho.initializeWithDefaults(this);
//        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        mRecyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        // specify an adapter (see also next example)
//        String[] myDataset = {"test", "testing"};
//        mAdapter = new MyAdapter(myDataset);
//        mRecyclerView.setAdapter(mAdapter);


        //DB object creation
        final SQLiteHelper sQLiteHelper = new SQLiteHelper(ViewSlots.this);
        ArrayList<SlotsModel> allSlots = sQLiteHelper.getAllRecords();
        SlotsModel temp = allSlots.get(0);

        TextView subjectName = (TextView) findViewById(R.id.subject_name);
        subjectName.setText(temp.getSubjectName());

        TextView date = (TextView) findViewById(R.id.date);
        date.setText(temp.getDate_());

        TextView time = (TextView) findViewById(R.id.time);
        time.setText(temp.getTime_());

        TextView location = (TextView) findViewById(R.id.location_text);
        location.setText(temp.getLocation_());

        TextView role = (TextView) findViewById(R.id.role_text);
        role.setText(temp.getSpinner_());


    }

}

