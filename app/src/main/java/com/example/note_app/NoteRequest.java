package com.example.note_app;

public class NoteRequest {
    private int key;

    private String ID;

    private String Title;
    private String Date;
    private String Note;
    private String date_joined =  "2020-04-20T12:16:34.566Z";

    public int getKey() {
        return key;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public String getDate() {
        return Date;
    }

    public String getNote() {
        return Note;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(String date_joined) {
        this.date_joined = date_joined;
    }
}
