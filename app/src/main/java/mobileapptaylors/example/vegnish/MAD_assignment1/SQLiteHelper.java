package mobileapptaylors.example.vegnish.MAD_assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Assignment1.db";
    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String TABLE_NAME = "SLOTS";
    public static final String COLUMN_SUBJECT_NAME = "SUBJECT_NAME";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_TIME = "TIME_START";
    public static final String COLUMN_TIME_END = "TIME_END";
    public static final String COLUMN_LOCATION = "LOCATION";
    public static final String COLUMN_SPINNER = "SPINNER";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( " + COLUMN_SUBJECT_NAME + " VARCHAR,"
                + COLUMN_DATE + " INTEGER ,"
                + COLUMN_TIME + " INTEGER,"
                + COLUMN_TIME_END + " INTEGER ,"
                + COLUMN_LOCATION + " VARCHAR,"
                + COLUMN_SPINNER + " VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private SQLiteDatabase database;
    public void insertRecord(SlotsModel slots, Context context) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SUBJECT_NAME, slots.getSubjectName());
        contentValues.put(COLUMN_DATE, slots.getDate_());
        contentValues.put(COLUMN_TIME, slots.getTime_());
        contentValues.put(COLUMN_TIME_END, slots.getTime_end());
        contentValues.put(COLUMN_LOCATION, slots.getLocation_());
        contentValues.put(COLUMN_SPINNER, slots.getSpinner_());
        database.insert(TABLE_NAME, null, contentValues);
        database.close();
        Toast.makeText(context, "Slot added!",
                Toast.LENGTH_LONG).show();
    }
    public void deleteSlot(long startTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_TIME + " = ?",
                new String[]{String.valueOf(startTime)});
        db.close();
    }
    public ArrayList<SlotsModel> getAllRecords() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<SlotsModel> slots = new ArrayList<SlotsModel>();
        SlotsModel slotsModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                slotsModel = new SlotsModel();
                slotsModel.setSubjectName(cursor.getString(0));
                slotsModel.setDate_(cursor.getLong(1));
                slotsModel.setTime_(cursor.getLong(2));
                slotsModel.setTime_end(cursor.getLong(3));
                slotsModel.setLocation_(cursor.getString(4));
                slotsModel.setSpinner_(cursor.getString(5));

                slots.add(slotsModel);
            }
        }
        cursor.close();
        database.close();

        return slots;
    }


}

