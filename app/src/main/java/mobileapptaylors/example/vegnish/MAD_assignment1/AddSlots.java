package mobileapptaylors.example.vegnish.MAD_assignment1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddSlots extends AppCompatActivity {
    private Spinner spinner;
    private static final String[]paths = {"Chief Invigilator", "Invigilator", "Standby Invigilator"};
    String subject_name, location_, spinner_;
    long date_,  time_, time_end_, temp_EndTime;
    final Calendar myCalendar = Calendar.getInstance();
    boolean endTimeBool = true;

    public void createSlotObj(EditText subjectName, EditText location, Object selectedItem){
        subject_name = subjectName.getText().toString();
        date_ =  myCalendar.getTimeInMillis();
        location_ = location.getText().toString();
        spinner_ = selectedItem.toString();
    }
    private void updateDateLabel(EditText date) {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date.setText(sdf.format(myCalendar.getTime()));
    }
    public boolean checkSubName(EditText subjectName){
        String subName = subjectName.getText().toString();
        if (TextUtils.isEmpty(subName)){
            subjectName.setError("Please fill up");
            return false;
        }
        return true;
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
    public void showDialog(Activity activity, String title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, mobileapptaylors.example.vegnish.MAD_assignment1.R.style.DialogeTheme);
        this.setFinishOnTouchOutside(false);
        if (title != null) builder.setTitle(title);

        builder.setMessage(message);
        builder.setPositiveButton("Yes",null);
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                startActivity(new Intent(AddSlots.this,Home.class));
            }
        });
        builder.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(mobileapptaylors.example.vegnish.MAD_assignment1.R.layout.activity_add_slots);
        setTitle("Add Slots");
        final EditText subjectName = (EditText) findViewById(mobileapptaylors.example.vegnish.MAD_assignment1.R.id.subjectName);
        final EditText date = (EditText) findViewById(mobileapptaylors.example.vegnish.MAD_assignment1.R.id.date);
        final EditText time = (EditText) findViewById(mobileapptaylors.example.vegnish.MAD_assignment1.R.id.time);
        final EditText timeEnd = (EditText) findViewById(mobileapptaylors.example.vegnish.MAD_assignment1.R.id.time_end);
        final EditText location = (EditText) findViewById(mobileapptaylors.example.vegnish.MAD_assignment1.R.id.location);

        // Drop down menu
        spinner = (Spinner) findViewById(mobileapptaylors.example.vegnish.MAD_assignment1.R.id.spinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddSlots.this,
                android.R.layout.simple_spinner_item, paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        Button addSlotButton = (Button) findViewById(mobileapptaylors.example.vegnish.MAD_assignment1.R.id.addSlotsButton);

        //Date picker pop up
        final DatePickerDialog.OnDateSetListener date_new = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateDateLabel(date);
            }

        };

        date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog current = new DatePickerDialog(AddSlots.this, date_new, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                current.getDatePicker().setMinDate(System.currentTimeMillis());
                current.show();
            }
        });


        //Start time
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = myCalendar.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddSlots.this, new TimePickerDialog.OnTimeSetListener() {
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
                        time.setText( twoDigitsFormat.format(selectedHour) + " : " + twoDigitsFormat.format(selectedMinute) + " " + AM_PM);
                        myCalendar.set(Calendar.HOUR, selectedHour);
                        myCalendar.set(Calendar.MINUTE, selectedMinute);
                        myCalendar.set(Calendar.SECOND, 0);
                        time_ = myCalendar.getTimeInMillis();

                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Start Time");
                mTimePicker.show();

            }
        });

        //End time
        timeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                Calendar endTime = Calendar.getInstance();
                Calendar endTime= (Calendar) myCalendar.clone();
                int hour = endTime.get(Calendar.HOUR_OF_DAY);
                int minute = endTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddSlots.this, new TimePickerDialog.OnTimeSetListener() {
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
                        timeEnd.setText( twoDigitsFormat.format(selectedHour) + " : " + twoDigitsFormat.format(selectedMinute) + " " + AM_PM);
                        endTime.set(Calendar.HOUR, selectedHour);
                        endTime.set(Calendar.MINUTE, selectedMinute);
                        temp_EndTime = endTime.getTimeInMillis();
                        checkEndTimeValidation(timeEnd, time_, temp_EndTime);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select End Time");
                mTimePicker.show();

            }
        });

        //DB Object creation
        final SQLiteHelper sQLiteHelper = new SQLiteHelper(AddSlots.this);

        addSlotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkSubName(subjectName) && checkDateSet(date) && checkStartTime(time) &&
                        checkEndTime(timeEnd) && checkLocation(location) && checkEndTimeValidation(timeEnd, time_, temp_EndTime)) {
                    //Create a SlotsModel object and store data
                    final Object selectedItem = spinner.getSelectedItem();
                    createSlotObj(subjectName, location, selectedItem);
                    final SlotsModel slot = new SlotsModel(subject_name, location_, date_, time_, time_end_, spinner_);

                    sQLiteHelper.insertRecord(slot, AddSlots.this);

                    // Reset all inputs
                    subjectName.setText("");
                    date.setText("");
                    time.setText("");
                    timeEnd.setText("");
                    location.setText("");
                    spinner.setAdapter(adapter);
                    new SetAlarm().alarmSetter(AddSlots.this, time_ );
                    showDialog(AddSlots.this, "Slot Added", "Do you wish to add another slot?");
                }
        }
        });

    }


}
