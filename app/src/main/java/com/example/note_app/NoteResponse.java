package com.example.note_app;

public class NoteResponse {
    private int Id;
    private String ID;

    private String Title;
    private String Date;
    private String Note;



    public int getId() {
        return Id;
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

    public void setId(int id) {
        Id = id;
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
}
