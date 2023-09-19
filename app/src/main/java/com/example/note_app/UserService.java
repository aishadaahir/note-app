package com.example.note_app;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    @POST("post/")
    Call<NoteResponse> saveNote(@Body NoteRequest noteRequest);

    @GET("read/")
    Call<List<Post>> getPost();

    @PUT("update/{id}")
    Call<NoteResponse> updateNote(@Path("id") String id,@Body NoteRequest noteRequest);

    @DELETE("delete/{id}")
    Call<Void> deleteNote(@Path("id") String id);

}


