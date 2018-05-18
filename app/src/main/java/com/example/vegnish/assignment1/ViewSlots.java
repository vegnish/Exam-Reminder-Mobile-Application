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
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);


        //DB object creation
        final SQLiteHelper sQLiteHelper = new SQLiteHelper(ViewSlots.this);
        ArrayList<SlotsModel> allSlots = sQLiteHelper.getAllRecords();

        SlotsAdapter slotsAdapters = new SlotsAdapter(allSlots);
        recList.setAdapter(slotsAdapters);

    }

}

