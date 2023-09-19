package com.example.note_app;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    ImageView getData;

    EditText title, note;
    TextView date;
    ImageButton save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getData = findViewById(R.id.backnote);
        title = findViewById(R.id.title2);
        date = findViewById(R.id.dateText);
        note = findViewById(R.id.notetext);
        save = findViewById(R.id.save);
        //create a date string.
        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
//get hold of textview.
        TextView date  = (TextView) findViewById(R.id.dateText);
//set it as current date.
        date.setText(date_n);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(note.getText().toString()) || TextUtils.isEmpty(date.getText().toString())){

                    String message = "All inputs required ..";
                    Toast.makeText(MainActivity2.this,message,Toast.LENGTH_LONG).show();
                }else {
                    NoteRequest noteRequest = new NoteRequest();
                    noteRequest.setTitle(title.getText().toString());
                    noteRequest.setDate(date.getText().toString());
                    noteRequest.setNote(note.getText().toString());
                    registerNote(noteRequest);
                }
            }
        });
    }

    public void registerNote(NoteRequest noteRequest){


        Call<NoteResponse> ResponseCall = ApiService.getService2().saveNote(noteRequest);
        ResponseCall.enqueue(new Callback<NoteResponse>() {
            @Override
            public void onResponse(Call<NoteResponse> call, Response<NoteResponse> response) {

                if(response.isSuccessful()){


                    String message = "Successful ..";
                    Toast.makeText(MainActivity2.this,message,Toast.LENGTH_LONG).show();

                    startActivity(new Intent(MainActivity2.this,MainActivity3.class));
                    finish();

                }else{

                    String message = "An error occurred please try again later ...";
                    Toast.makeText(MainActivity2.this,message,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<NoteResponse> call, Throwable t) {

                String message = t.getLocalizedMessage();
                Toast.makeText(MainActivity2.this,message,Toast.LENGTH_LONG).show();

            }
        });
    }
}