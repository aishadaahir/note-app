package com.example.note_app;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EditNote extends AppCompatActivity {
    EditText titel, notetext;
    TextView datetext;
    ImageButton update;
    ImageView backnote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        backnote = findViewById(R.id.backnote);
        backnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity3.class));
            }
        });

        titel = findViewById(R.id.title2);
        datetext = findViewById(R.id.dateText);
        notetext = findViewById(R.id.notetext);
        update = findViewById(R.id.save);

        //GET DATA
        titel.setText(getIntent().getExtras().getString("Title"));
        datetext.setText(getIntent().getExtras().getString("Date"));
        notetext.setText(getIntent().getExtras().getString("Note"));
        final String id = getIntent().getExtras().getString("ID");


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title_txt = titel.getText().toString().trim();
                String date_txt = datetext.getText().toString().trim();
                String note_txt = notetext.getText().toString().trim();

                if (title_txt.equals("") || date_txt.equals("") || note_txt.equals("")) {
                    Toast.makeText(EditNote.this, "All Field is required ....", Toast.LENGTH_SHORT).show();
                } else {
                    assert id != null;

                    NoteRequest noteRequest = new NoteRequest();
                    noteRequest.setTitle(titel.getText().toString());
                    noteRequest.setDate(datetext.getText().toString());
                    noteRequest.setNote(notetext.getText().toString());
                    updateNote(id,noteRequest);
//                    myDB.updateData(id, title_txt, date_txt, note_txt);
//                    database.getDatabase(getApplicationContext()).getDao().updateData(title_txt, date_txt, note_txt, Integer.parseInt(id));
//                    finish();
                }
            }
        });
//        update_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //And only then we call this
//                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
//                title = title_input.getText().toString().trim();
//                author = author_input.getText().toString().trim();
//                pages = pages_input.getText().toString().trim();
//                myDB.updateData(id, title, author, pages);
//            }
//        });
    }

    public void updateNote(String id,NoteRequest noteRequest){

        Call<NoteResponse> ResponseCall = ApiService.getService2().updateNote(id,noteRequest);
        ResponseCall.enqueue(new Callback<NoteResponse>() {
            @Override
            public void onResponse(Call<NoteResponse> call, Response<NoteResponse> response) {

                if(response.isSuccessful()){


                    String message = "Note Updated";
                    Toast.makeText(EditNote.this,message,Toast.LENGTH_LONG).show();

                    startActivity(new Intent(EditNote.this,MainActivity3.class));
                    finish();

                }else{

                    String message = "An error occurred please try again later ...";
                    Toast.makeText(EditNote.this,message,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<NoteResponse> call, Throwable t) {

                String message = t.getLocalizedMessage();
                Toast.makeText(EditNote.this,message,Toast.LENGTH_LONG).show();

            }
        });
    }

//    void getAndSetIntentData(){
//        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
//                getIntent().hasExtra("author") && getIntent().hasExtra("pages")){
//            //Getting Data from Intent
//            id = getIntent().getStringExtra("id");
//            title = getIntent().getStringExtra("title");
//            author = getIntent().getStringExtra("author");
//            pages = getIntent().getStringExtra("pages");
//
//            //Setting Intent Data
//            title_input.setText(title);
//            author_input.setText(author);
//            pages_input.setText(pages);
//            Log.d("stev", title+" "+author+" "+pages);
//        }else{
//            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
//        }
//    }
}