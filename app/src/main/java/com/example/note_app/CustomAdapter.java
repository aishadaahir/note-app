package com.example.note_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList Id,Title,Date,Note;
    List<Post> postList;

    DeleteItemClicklistner deleteItemClicklistner;

    CustomAdapter(Activity activity, Context context,List<Post> posts,DeleteItemClicklistner deleteItemClicklistner){
        this.activity = activity;
        this.context = context;
        postList = posts;
//        this.Id = Id;
//        this.Title = Title;
//        this.Date = Date;
//        this.Note = Note;
        this.deleteItemClicklistner = deleteItemClicklistner;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notelayout, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        Post post = postList.get(position);
        holder.Title.setText(post.getTitle());
        holder.Date.setText(post.getDate());
        holder.Note.setText(post.getNote());
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditNote.class);
                intent.putExtra("ID", String.valueOf(post.getId()));
                intent.putExtra("Title", String.valueOf(post.getTitle()));
                intent.putExtra("Date", String.valueOf(post.getDate()));
                intent.putExtra("Note", String.valueOf(post.getNote()));
                activity.startActivityForResult(intent, 1);
//                startActivity(new Intent(getApplicationContext(), MainActivity2.class));

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id=String.valueOf(post.getId());
                deleteItemClicklistner.onItemDelete(position, id);

            }
        });


    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public interface DeleteItemClicklistner {
        void onItemDelete(int position, String id);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Title, Date,Note;
        ImageButton update, delete;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.titel);
            Date = itemView.findViewById(R.id.date);
            Note = itemView.findViewById(R.id.Note);
            update = itemView.findViewById(R.id.updateId);
            delete = itemView.findViewById(R.id.deleteId);
            //Animate Recyclerview
//            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
//            mainLayout.setAnimation(translate_anim);
        }



    }

}