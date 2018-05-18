package com.example.vegnish.assignment1;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.prefs.Preferences;

public class AddSlots extends AppCompatActivity {
    private Spinner spinner;
    private static final String[]paths = {"Chief Invigilator", "Invigilator", "Standby Invigilator"};
    String subject_code, subject_name, location_, date_, time_, spinner_ ;

    final Calendar myCalendar = Calendar.getInstance();

    public void createSlotObj(EditText subjectCode, EditText subjectName, EditText date, EditText time, EditText location, Object selectedItem){
        subject_code = subjectCode.getText().toString();
        subject_name = subjectName.getText().toString();
        date_ = date.getText().toString();
        time_ = time.getText().toString();
        location_ = location.getText().toString();
        spinner_ = selectedItem.toString();
    }
    private void updateDateLabel(EditText date) {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date.setText(sdf.format(myCalendar.getTime()));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_add_slots);
        setTitle("Add Slots");

        final EditText subjectCode = (EditText) findViewById(R.id.subjectCode);
        final EditText subjectName = (EditText) findViewById(R.id.subjectName);
        final EditText date = (EditText) findViewById(R.id.date);
        final EditText time = (EditText) findViewById(R.id.time);
        final EditText location = (EditText) findViewById(R.id.location);

        // Drop down menu
        spinner = (Spinner) findViewById(R.id.spinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddSlots.this,
                android.R.layout.simple_spinner_item, paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        final Object selectedItem = spinner.getSelectedItem();

        Button addSlotButton = (Button) findViewById(R.id.addSlotsButton);

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

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddSlots.this, date_new, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddSlots.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String AM_PM ;
                        if(selectedHour < 12) {
                            AM_PM = "AM";
                        } else {
                            AM_PM = "PM";
                            if (selectedHour > 12) {selectedHour -= 12;}
                        }
                        time.setText( selectedHour + " : " + selectedMinute + " " + AM_PM);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        //DB creation
        final SQLiteHelper sQLiteHelper = new SQLiteHelper(AddSlots.this);
        addSlotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a SlotsModel object and store data
                createSlotObj(subjectCode,subjectName,date,time,location, selectedItem);
                final SlotsModel slot = new SlotsModel(subject_code, subject_name, location_, date_, time_, spinner_);
                sQLiteHelper.insertRecord(slot, AddSlots.this);

                // Reset all inputs
                subjectCode.setText("");
                subjectName.setText("");
                date.setText("");
                time.setText("");
                location.setText("");
                spinner.setAdapter(adapter);

        }
        });

    }


}