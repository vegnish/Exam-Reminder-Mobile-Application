package mobileapptaylors.example.vegnish.MAD_assignment1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SlotsAdapter_viewSlot extends RecyclerView.Adapter<SlotsAdapter_viewSlot.SlotsViewHolder> {

    private ArrayList<SlotsModel> slotsList;
    Context context;

    public SlotsAdapter_viewSlot(ArrayList<SlotsModel> slotsList) {
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
        int id_ = slotsObj.getId();
        String dateString = getDate(slotsObj.getDate_(), "dd/MM/yyyy");
        long dateLongString = slotsObj.getDate_();
        SlotsViewHolder.date.setText(dateString);
        String timeString = getDate(slotsObj.getTime_(), "hh:mm aa");
        SlotsViewHolder.time.setText(timeString);
        String endTimeString = getDate(slotsObj.getTime_end(), "hh:mm aa");
        SlotsViewHolder.endTime.setText(endTimeString);
        SlotsViewHolder.location.setText(slotsObj.getLocation_());
        SlotsViewHolder.role.setText(slotsObj.getSpinner_());

        //HANDLE ITEMCLICKS
        SlotsViewHolder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("clickkk", "onClick: "+ " "+ SlotsViewHolder.time.getText());
                final AppCompatActivity activity=(AppCompatActivity)v.getContext();
                showDialog(activity, "Update or Delete", "What do you wish to do?", id_,i);
            }
        });

    }
    public void showDialog(Activity activity, String title, CharSequence message, int id_, int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, mobileapptaylors.example.vegnish.MAD_assignment1.R.style.DialogeTheme);
//        this.setFinishOnTouchOutside(false);
        if (title != null) builder.setTitle(title);

        builder.setMessage(message);
        builder.setPositiveButton("Update",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){

                Log.d("startTime", "onClick:");
                SlotsModel slotsObj = slotsList.get(i);

                Intent myIntent = new Intent(activity, updateSlot.class);
                myIntent.putExtra("slotObject",slotsObj); //Optional parameters
                activity.startActivity(myIntent);

            }
        });
        builder.setNegativeButton("Delete",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
//                startActivity(new Intent(ViewSlots.this,Home.class));
                //DB object creation
                final SQLiteHelper sQLiteHelper = new SQLiteHelper(activity);
                sQLiteHelper.deleteSlot(id_);
                Intent refresh = new Intent(activity, ViewSlots.class);
                activity.startActivity(refresh);
                activity.finish();
            }
        });
        builder.show();
    }
    @Override
    public SlotsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_view2, viewGroup, false);
//        itemView.setOnClickListener(this);


        return new SlotsViewHolder(itemView);
    }

    public static class SlotsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView date;
        protected TextView time;
        protected TextView endTime;
        protected TextView location;
        protected TextView role;
        protected ImageButton menu;

        public SlotsViewHolder(View v) {
            super(v);
            date = (TextView)  v.findViewById(R.id.date);
            time = (TextView)  v.findViewById(R.id.time);
            endTime = (TextView) v.findViewById(R.id.endTime);
            location = (TextView) v.findViewById(R.id.location_text);
            role = (TextView) v.findViewById(R.id.role_text);
            menu = (ImageButton) v.findViewById(R.id.viewMenu);
        }

        @Override
        public void onClick(View v) {
//            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }
//        public void setItemClickListener(ItemClickListener ic)
//        {
////            this.itemClickListener=ic;
//        }

    }
}

