package com.example.note_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    RecyclerView recyclerview;
    ImageButton click;

//    DatabaseHelper2 myDB;

//    ArrayList<String> Id,Title,Date,Note;

    RecyclerView recyclerView;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
//        setContentView(R.layout.test2);

        recyclerView = findViewById(R.id.recyclerView);
        click = findViewById(R.id.addnote);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity2.class));
//                startActivity(new Intent(getApplicationContext(), EditNote.class));
            }
        });



        getPost();

    }

//    private void deletePost(String id) {
//
//        Call<Void> calls = ApiService.getService2().deleteNote(id);
//        calls.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if (!response.isSuccessful()) {
//                    return;
//                }
//                Toast.makeText(MainActivity3.this, "Deleted Successfully : " + response.code(), Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//
//            }
//        });
//    }
//    public void deleteNote(String id){
//        Call<NoteResponse> ResponseCall = ApiService.getService2().deleteNote(id);
//        ResponseCall.enqueue(new Callback<NoteResponse>() {
//            @Override
//            public void onResponse(Call<NoteResponse> call, Response<NoteResponse> response) {
//                if(response.isSuccessful()){
//
//                    String message = "Successful ..";
//                    Toast.makeText(MainActivity3.this,message,Toast.LENGTH_LONG).show();
//
////                    startActivity(new Intent(MainActivity3.this,MainActivity3.class));
//                    finish();
//
//                }else{
//
//                    String message = "An error occurred please try again later ...";
//                    Toast.makeText(MainActivity3.this,message,Toast.LENGTH_LONG).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<NoteResponse> call, Throwable t) {
//
//                String message = t.getLocalizedMessage();
//                Toast.makeText(MainActivity3.this,message,Toast.LENGTH_LONG).show();
//
//            }
//        });
//    }

    private void deleteNote(String id) {

        Call<Void> call = ApiService.getService2().deleteNote(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()){
                    return;
                }
                Toast.makeText(MainActivity3.this, "Deleted Successfully" , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void getPost(){
        Call<List<Post>> call = ApiService.getService2().getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity3.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Post> postList = response.body();
                CustomAdapter postAdapter = new CustomAdapter(MainActivity3.this,MainActivity3.this,  postList,new CustomAdapter.DeleteItemClicklistner() {
                    @Override
                    public void onItemDelete(int position, String id) {
                        deleteNote(id);
                        getPost();

//                        Toast.makeText(MainActivity3.this, ""+id+"", Toast.LENGTH_SHORT).show();

                    }

                });
//                LinearLayoutManager lm=new LinearLayoutManager(this,LinearLayoutManager.vertical,false)
//                recyclerView.getLayoutManager(lm);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(postAdapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                Toast.makeText(MainActivity3.this, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBackPressed() {
        //do nothing
    }


}

