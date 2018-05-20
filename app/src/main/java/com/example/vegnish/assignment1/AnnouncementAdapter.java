package com.example.vegnish.assignment1;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AnnouncementAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<AnnouncementModel> arrayList;
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer2;
    private Boolean flag = true;
    private Boolean stopFlag = false;
    private int hashID=0;
    private ViewHolder temp;
    private LayoutInflater layoutInflater2;
    private PopupWindow popupWindow;

    public AnnouncementAdapter(Context context, int layout, ArrayList<AnnouncementModel> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtType, txtDescription;
        ImageView ivPlay, ivStop;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(layout, null);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.txtType);
            viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.txtDescription);
            viewHolder.ivPlay = (ImageView) convertView.findViewById(R.id.ivPlay);
            viewHolder.ivStop = (ImageView) convertView.findViewById(R.id.ivStop);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final AnnouncementModel announcementModel = arrayList.get(position);

        viewHolder.txtType.setText(announcementModel.getAnnouncementType());
        viewHolder.txtDescription.setText(announcementModel.getAnnouncementDescription());



        //To Play Announcement
        viewHolder.ivPlay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!(hashID == 0)){
                    if(hashID == viewHolder.hashCode()) {
                        flag = false;
                        if(stopFlag)
                            flag = true;
                    }
                    else {
                        flag = true;
                        if(!stopFlag) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                        }
                        temp.ivPlay.setImageResource(R.drawable.ic_play);
                    }
                }
                hashID = viewHolder.hashCode();
                temp = viewHolder;
//                System.out.println("ViewHolder ID"+viewHolder.hashCode());

                if(flag){
                    mediaPlayer = MediaPlayer.create(context, announcementModel.getQuantity());
                    flag = false;
                    stopFlag = false;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    viewHolder.ivPlay.setImageResource(R.drawable.ic_play);
                }
                else{
                    mediaPlayer.start();
                    viewHolder.ivPlay.setImageResource(R.drawable.ic_pause);
//                    System.out.println("txtdescription"+viewHolder.txtDescription.getText().toString());
                    if ((viewHolder.txtDescription.getText().toString().equals("For Non-MCQ examination"))|| (viewHolder.txtDescription.getText().toString().equals("For MCQ examination"))) {
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
//                            Toast.makeText(context,"END OF AUDIO", Toast.LENGTH_SHORT).show();
                                layoutInflater2 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                ViewGroup container = (ViewGroup) layoutInflater2.inflate(R.layout.popup_message, null);

                                popupWindow = new PopupWindow(container, 1300, 2000, true);
                                popupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, 95, 400);

                                container.setOnTouchListener(new View.OnTouchListener() {
                                    @Override
                                    public boolean onTouch(View v, MotionEvent event) {
                                        popupWindow.dismiss();
                                        System.out.println("txtdescription"+viewHolder.txtDescription.getText().toString());
                                        if(viewHolder.txtDescription.getText().toString().equals("For MCQ examination")){
                                            mediaPlayer2 = MediaPlayer.create(context,R.raw.with_mcq);
                                            mediaPlayer2.start();
                                            mediaPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    viewHolder.ivPlay.setImageResource(R.drawable.ic_play);
                                                }
                                            });
                                        }
                                        return true;
                                    }
                                });
                                viewHolder.ivPlay.setImageResource(R.drawable.ic_play);
                            }
                        });
                    }
                    else {
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                viewHolder.ivPlay.setImageResource(R.drawable.ic_play);
                            }
                        });
                    }
                }
            }
        });

        //To Stop Announcement
        viewHolder.ivStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(hashID == viewHolder.hashCode()) {
                    if(!stopFlag) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                    }
                    stopFlag = true;
                    viewHolder.ivPlay.setImageResource(R.drawable.ic_play);
                }
            }
        });
        return convertView;
    }
}
