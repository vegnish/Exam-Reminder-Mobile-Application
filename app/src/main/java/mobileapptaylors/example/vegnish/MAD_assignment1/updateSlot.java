package mobileapptaylors.example.vegnish.MAD_assignment1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class updateSlot extends AppCompatActivity {
    private Spinner spinner;
    private static final String[]paths = {"Chief Invigilator", "Invigilator", "Standby Invigilator"};
    final Calendar myCalendar = Calendar.getInstance();
    String location_, spinner_;
    long date_,  time_, time_end_, temp_EndTime;


    private void updateDateLabel(EditText date) {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date.setText(sdf.format(myCalendar.getTime()));
    }
    private void fillData (SlotsModel value, EditText  update_date, EditText update_time, EditText update_timeEnd,EditText update_location){
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        update_date.setText(sdf.format(value.getDate_()));

        SimpleDateFormat time = new SimpleDateFormat("hh:mm aa");
        update_time.setText(time.format(value.getTime_()));
        myCalendar.setTimeInMillis(value.getDate_());
        time_ = value.getTime_();
        time_end_=value.getTime_end();
        update_timeEnd.setText(time.format(value.getTime_end()));
        update_location.setText(value.getLocation_());

    }
    public boolean checkEndTimeValidation(EditText timeEnd, long time_,long temp_EndTime) {
        if (time_ >= temp_EndTime) {
            Toast.makeText(getApplicationContext(), "Please make sure end time is after start time!",
                    Toast.LENGTH_LONG).show();
            timeEnd.setError("End time should be after start time!");
            return false;
        } else{
            time_end_ = temp_EndTime;
            timeEnd.setError(null);
            return true;
        }
    }
    public boolean checkLocation(EditText location){
        String checkLocation = location.getText().toString();
        if (TextUtils.isEmpty(checkLocation)){
            location.setError("Please fill up location");
            return false;
        }
        else {
            location.setError(null);
            return true;
        }
    }

    public boolean checkDateSet(EditText date){
        String strDate = date.getText().toString();
        if (TextUtils.isEmpty(strDate)) {
            date.setError("Please choose a date first!");
            Toast.makeText(getApplicationContext(), "Please fill up date",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        else {
//            date.requestFocus();
            date.setError(null);
            return true;
        }
    }

    public boolean checkStartTime(EditText time){
        String timeCurrent = time.getText().toString();
        if (TextUtils.isEmpty(timeCurrent)){
            time.setError("Please fill up");
            Toast.makeText(getApplicationContext(), "Please fill up Start time",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            time.setError(null);
            return true;
        }
    }

    public boolean checkEndTime(EditText timeEnd){
        String checkEndTime = timeEnd.getText().toString();
        if (TextUtils.isEmpty(checkEndTime)){
            timeEnd.setError("Please fill up");
            Toast.makeText(getApplicationContext(), "Please fill up End Time",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            timeEnd.setError(null);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_slot);

        final EditText update_date = (EditText) findViewById(R.id.update_date);
        final EditText update_time = (EditText) findViewById(R.id.update_time);
        final EditText update_timeEnd = (EditText) findViewById(R.id.update_time_end);
        final EditText update_location = (EditText) findViewById(R.id.update_location);


        Intent intent = getIntent();
        SlotsModel value = (SlotsModel) intent.getSerializableExtra("slotObject");
        fillData(value, update_date, update_time, update_timeEnd, update_location);
//        update_date.setText(value.getDate_());

        List<String> mStringList = Arrays.asList(paths);
        String currentSpinner = value.getSpinner_();
        int pos = mStringList.indexOf(currentSpinner);
        Collections.swap(mStringList, pos, 0);
        // Drop down menu
        spinner = (Spinner) findViewById(R.id.update_spinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(updateSlot.this,
                android.R.layout.simple_spinner_item, mStringList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        Button updateSlotButton = (Button) findViewById(R.id.updateSlotsButton);

        //Date picker pop up
        final DatePickerDialog.OnDateSetListener date_new = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateDateLabel(update_date);
            }

        };

        update_date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog current = new DatePickerDialog(updateSlot.this, R.style.DatePickerDialogTheme,date_new, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                current.getDatePicker().setMinDate(System.currentTimeMillis());
                current.getDatePicker().setLayoutMode(1);

                current.show();
//                new SpinnerDatePickerDialogBuilder()
//                        .context(AddSlots.this)
//                        .showTitle(true)
//                        .showDay(true)
//                        .defaultDate(2017, 0, 1)
//                        .maxDate(2020, 0, 1)
//                        .minDate(2000, 0, 1)
//                        .build()
//                        .show();
            }
        });
//
//
        //Start time
        update_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = myCalendar.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(updateSlot.this,0, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String AM_PM ;
                        if(selectedHour < 12) {
                            AM_PM = "AM";
                            if (selectedHour == 0){
                                selectedHour += 12;
                            }
                            myCalendar.set(Calendar.AM_PM, Calendar.AM);
                        } else {
                            AM_PM = "PM";
                            myCalendar.set(Calendar.AM_PM, Calendar.PM);
                            if (selectedHour > 12) {selectedHour -= 12;}
                        }
                        java.text.DecimalFormat twoDigitsFormat = new
                                java.text.DecimalFormat("#00");
                        update_time.setText( twoDigitsFormat.format(selectedHour) + " : " + twoDigitsFormat.format(selectedMinute) + " " + AM_PM);
                        myCalendar.set(Calendar.HOUR, selectedHour);
                        myCalendar.set(Calendar.MINUTE, selectedMinute);
                        myCalendar.set(Calendar.SECOND, 0);
                        time_ = myCalendar.getTimeInMillis();
                        Log.d("time", "onTimeSet: "+ time_);

                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Start Time");
                mTimePicker.show();

            }
        });

        //End time
        update_timeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                Calendar endTime = Calendar.getInstance();
                Calendar endTime= (Calendar) myCalendar.clone();
                int hour = endTime.get(Calendar.HOUR_OF_DAY);
                int minute = endTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(updateSlot.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String AM_PM ;
                        if(selectedHour < 12) {
                            AM_PM = "AM";
                            if (selectedHour == 0){
                                selectedHour += 12;
                            }
                            endTime.set(Calendar.AM_PM, Calendar.AM);
                        } else {
                            AM_PM = "PM";
                            endTime.set(Calendar.AM_PM, Calendar.PM);

                            if (selectedHour > 12) {
                                selectedHour -= 12;}
                        }
                        java.text.DecimalFormat twoDigitsFormat = new
                                java.text.DecimalFormat("#00");
                        update_timeEnd.setText( twoDigitsFormat.format(selectedHour) + " : " + twoDigitsFormat.format(selectedMinute) + " " + AM_PM);
                        endTime.set(Calendar.HOUR, selectedHour);
                        endTime.set(Calendar.MINUTE, selectedMinute);
                        temp_EndTime = endTime.getTimeInMillis();
                        checkEndTimeValidation(update_timeEnd, time_, temp_EndTime);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select End Time");
                mTimePicker.show();

            }
        });

        //DB Object creation
        final SQLiteHelper sQLiteHelper = new SQLiteHelper(updateSlot.this);

        updateSlotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkDateSet(update_date) && checkStartTime(update_time) &&
                        checkEndTime(update_timeEnd) && checkLocation(update_location)) {
                    //Create a SlotsModel object and store data
                    final Object selectedItem = spinner.getSelectedItem();
                    location_ = update_location.getText().toString();
                    date_ = myCalendar.getTimeInMillis();
                    spinner_ = selectedItem.toString();
                    value.setLocation_(location_);
                    value.setDate_(date_);
                    value.setTime_(time_);
                    Log.d("timeEnd", "onTimeSet: " + time_end_);
                    value.setTime_end(time_end_);
                    value.setSpinner_(spinner_);
                    sQLiteHelper.updateSlot(value, value.getId());

                    new SetAlarm().alarmSetter(updateSlot.this, time_);
                    Intent myIntent = new Intent(updateSlot.this, ViewSlots.class);
                    startActivity(myIntent);
                }
            }
        });
    }
}
