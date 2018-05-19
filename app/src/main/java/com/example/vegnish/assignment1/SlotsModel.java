package com.example.vegnish.assignment1;

import java.util.Calendar;
import java.util.Date;

public class SlotsModel {
    private String subjectName, location_, spinner_ ;
    private long date_,time_, time_end;

    public SlotsModel(String subjectName, String location_, long date_, long time_,long time_end, String spinner_) {
        this.subjectName = subjectName;
        this.location_ = location_;
        this.date_ = date_;
        this.time_ = time_;
        this.time_end = time_end;
        this.spinner_ = spinner_;
    }

    public SlotsModel (){

    }
    public String getSpinner_() {
        return spinner_;
    }

    public void setSpinner_(String spinner_) {
        this.spinner_ = spinner_;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getLocation_() {
        return location_;
    }

    public void setLocation_(String location_) {
        this.location_ = location_;
    }

    public long getDate_() {
        return date_;
    }

    public void setDate_(long date_) {
        this.date_ = date_;
    }

    public long getTime_() {
        return time_;
    }

    public void setTime_(long time_) {
        this.time_ = time_;
    }

    public long getTime_end() {
        return time_end;
    }

    public void setTime_end(long time_end) {
        this.time_end = time_end;
    }
}
