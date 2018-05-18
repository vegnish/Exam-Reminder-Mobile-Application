package com.example.vegnish.assignment1;

public class SlotsModel {
    private String subjectCode, subjectName, location_, date_, time_, spinner_ ;

    public SlotsModel(String subjectCode, String subjectName, String location_, String date_, String time_, String spinner_) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.location_ = location_;
        this.date_ = date_;
        this.time_ = time_;
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

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
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

    public String getDate_() {
        return date_;
    }

    public void setDate_(String date_) {
        this.date_ = date_;
    }

    public String getTime_() {
        return time_;
    }

    public void setTime_(String time_) {
        this.time_ = time_;
    }
}
