package mobileapptaylors.example.vegnish.MAD_assignment1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mobileapptaylors.example.vegnish.MAD_assignment1.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SlotsAdapter extends RecyclerView.Adapter<SlotsAdapter.SlotsViewHolder> {

    private ArrayList<SlotsModel> slotsList;

    public SlotsAdapter(ArrayList<SlotsModel> slotsList) {
        this.slotsList = slotsList;
    }

    @Override
    public int getItemCount() {
        return slotsList.size();
    }

    /**
     * Return date in specified format.
     * @param milliSeconds Date in milliseconds
     * @param dateFormat Date format
     * @return String representing date in specified format
     */
    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    @Override
    public void onBindViewHolder(SlotsViewHolder SlotsViewHolder, int i) {
        SlotsModel slotsObj = slotsList.get(i);
        String dateString = getDate(slotsObj.getDate_(), "dd/MM/yyyy");
        SlotsViewHolder.date.setText(dateString);
        String timeString = getDate(slotsObj.getTime_(), "hh:mm aa");
        SlotsViewHolder.time.setText(timeString);
        String endTimeString = getDate(slotsObj.getTime_end(), "hh:mm aa");
        SlotsViewHolder.endTime.setText(endTimeString);
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
        protected TextView date;
        protected TextView time;
        protected TextView endTime;
        protected TextView location;
        protected TextView role;

        public SlotsViewHolder(View v) {
            super(v);
            date = (TextView)  v.findViewById(R.id.date);
            time = (TextView)  v.findViewById(R.id.time);
            endTime = (TextView) v.findViewById(R.id.endTime);
            location = (TextView) v.findViewById(R.id.location_text);
            role = (TextView) v.findViewById(R.id.role_text);
        }
    }
}

