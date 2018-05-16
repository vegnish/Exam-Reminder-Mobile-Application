package com.example.vegnish.assignment1;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.prefs.Preferences;

public class AddSlots extends AppCompatActivity {
    private Spinner spinner;
    private static final String[]paths = {"Chief Invigilator", "Invigilator", "Standby Invigilator"};

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_slots);
        setTitle("Add Slots");
        verifyStoragePermissions(this);
        final EditText subjectName = (EditText) findViewById(R.id.subjectName);
        Button addSlotButton = (Button) findViewById(R.id.addSlotsButton);
        addSlotButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    String subject_name =  subjectName.getText().toString();
                    if(!subject_name.trim().equals("")){
                        File file =new File(Environment.getExternalStorageDirectory()+ "Assign1.txt");

                        file.mkdirs();
                        if(file.exists()){
                            Log.d("file status", "file exists");
                        }
                        //if file doesnt exists, then create it
                        if(!file.exists()){
                            file.createNewFile();
                            Log.d("file status", "file created");
                        }

                        Log.d("path name", String.valueOf(file));

                        FileOutputStream  fileWritter = new FileOutputStream(file.getName(),true);
                        fileWritter.write(subject_name.getBytes());
                        fileWritter.close();
                    }
                }catch (IOException e) {

                    e.printStackTrace(); }

        }});
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddSlots.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);

    }

//    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
//
//        switch (position) {
//            case 0:
//                // Whatever you want to happen when the first item gets selected
//                break;
//            case 1:
//                // Whatever you want to happen when the second item gets selected
//                break;
//            case 2:
//                // Whatever you want to happen when the thrid item gets selected
//                break;
//
//        }
//    }
}
