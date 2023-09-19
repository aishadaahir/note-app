package com.example.note_app;

public class Post {

    private String  _id , Note , Title,Date;

    public Post(String id,String title, String date, String note) {
        this._id = id;
        this.Title = title;
        this.Date = date;
        this.Note = note;
    }


    public void set_id(String _id) {
        this._id = _id;
    }

    public String getId() {
        return _id;
    }

    public String getNote() {
        return Note;
    }

    public String getTitle() {
        return Title;
    }

    public String getDate() {
        return Date;
    }
}