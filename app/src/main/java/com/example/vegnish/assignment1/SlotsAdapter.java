package com.example.vegnish.assignment1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SlotsAdapter extends RecyclerView.Adapter<SlotsAdapter.SlotsViewHolder> {

    private ArrayList<SlotsModel> slotsList;

    public SlotsAdapter(ArrayList<SlotsModel> slotsList) {
        this.slotsList = slotsList;
    }

    @Override
    public int getItemCount() {
        return slotsList.size();
    }

    @Override
    public void onBindViewHolder(SlotsViewHolder SlotsViewHolder, int i) {
        SlotsModel slotsObj = slotsList.get(i);
        SlotsViewHolder.subjectName.setText(slotsObj.getSubjectName());
        SlotsViewHolder.date.setText(slotsObj.getDate_());
        SlotsViewHolder.time.setText(slotsObj.getTime_());
        SlotsViewHolder.location.setText(slotsObj.getLocation_());
        SlotsViewHolder.role.setText(slotsObj.getSpinner_());
    }

    @Override
    public SlotsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_view, viewGroup, false);

        return new SlotsViewHolder(itemView);
    }

    public static class SlotsViewHolder extends RecyclerView.ViewHolder {
        protected TextView subjectName;
        protected TextView date;
        protected TextView time;
        protected TextView location;
        protected TextView role;

        public SlotsViewHolder(View v) {
            super(v);
            subjectName =  (TextView) v.findViewById(R.id.subject_name);
            date = (TextView)  v.findViewById(R.id.date);
            time = (TextView)  v.findViewById(R.id.time);
            location = (TextView) v.findViewById(R.id.location_text);
            role = (TextView) v.findViewById(R.id.role_text);
        }
    }
}

