package mobileapptaylors.example.vegnish.MAD_assignment1;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import mobileapptaylors.example.vegnish.MAD_assignment1.R;
import com.facebook.stetho.Stetho;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class HistorySlots extends AppCompatActivity {
    public ArrayList<SlotsModel> allSlots;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private static final String TAG = "PdfCreatorActivity";
    private Button downloadButton;
    private File pdfFile;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;

    //Sort list in descending order according to date
    private void sortList(ArrayList<SlotsModel> allSlots) {
        Collections.sort(allSlots, new Comparator<SlotsModel>() {
            public int compare(SlotsModel date1, SlotsModel date2) {
                // avoiding NullPointerException in case name is null
                Long date_1 = new Long(date1.getDate_());
                Long date_2 = new Long(date2.getDate_());

                //for descending order swap return items
                return date_2.compareTo(date_1);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), Home.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    private void createPdfWrapper() throws FileNotFoundException,DocumentException{

        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                    }
                                }
                            });
                    return;
                }

                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        }else {
            createPdf();
        }
    }

    @Override
    public void onRequestPermissionsResult (int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    try {
                        createPdfWrapper();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Permission Denied
                    Toast.makeText(this, "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void createPdf() throws FileNotFoundException, DocumentException{

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            Log.i(TAG, "Created a new directory for PDF");
        }





        pdfFile = new File(docsFolder.getAbsolutePath(),"HelloWorld.pdf");
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document();
        PdfWriter.getInstance(document, output);
        document.open();

        int counter=1;
        Image img = null;
        Image image = null;
        try {

            Drawable d = getResources().getDrawable(R.mipmap.logo);
            BitmapDrawable bitDw = ((BitmapDrawable) d);
            Bitmap bmp = bitDw.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
//            image.setBorderWidth(15);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator<SlotsModel> iter = allSlots.iterator();
        while (iter.hasNext()) {
            if(counter!=1)
                counter++;
            if(counter == 1) {
//                img.setAbsolutePosition(0, 0);
                image.scalePercent(10);
                image.setAlignment(Element.ALIGN_CENTER);
                document.add(image);
//                document.add(new Paragraph("\n\n"));
                document.add(new Paragraph("History Slots\n\n"));
            }
            SlotsModel p = iter.next();
            long date = p.getDate_();
            long time = p.getTime_();
            long time2 = p.getTime_end();

//            Long date_1 = new Long(date.getDate_());
            Date new_date = new Date(time);
            Date new_date2 = new Date(time2);
            SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat dateformat2 = new SimpleDateFormat("KK:mm:a");

            final String location = p.getLocation_();
            document.add(new Paragraph(counter+".  "+dateformat.format(new_date)+"\nStart Time: "+dateformat2.format(new_date)+"\nEnd Time: "+dateformat2.format(new_date2)+"\nLocation: "+location+"\n\n"));
        }
//
//        document.add(new Paragraph("Hello World\n"));
//        document.add(new Paragraph("How are you today?\n"));

        document.close();
        previewPdf();

    }

    private void previewPdf() {

        PackageManager packageManager = getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(pdfFile);
            intent.setDataAndType(uri, "application/pdf");

            startActivity(intent);
        }else{
            Toast.makeText(this,"Download a PDF Viewer to see the generated PDF",Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_slots);
        setTitle("History Slots");

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Stetho.initializeWithDefaults(this);
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);


        //DB object creation
        final SQLiteHelper sQLiteHelper = new SQLiteHelper(HistorySlots.this);
        allSlots = sQLiteHelper.getAllRecords();

        sortList(allSlots);
        Iterator<SlotsModel> iter = allSlots.iterator();
        while (iter.hasNext()) {
            SlotsModel p = iter.next();
            final long time = p.getTime_();
            long currentTime = System.currentTimeMillis();
            if (time > currentTime) iter.remove();
        }

        SlotsAdapter slotsAdapters = new SlotsAdapter(allSlots);
        recList.setAdapter(slotsAdapters);

        downloadButton = (Button) findViewById(R.id.button_download);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createPdfWrapper();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });


    }

}


