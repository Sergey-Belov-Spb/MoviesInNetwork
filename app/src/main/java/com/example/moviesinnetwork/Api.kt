package com.example.moviesinnetwork

import com.example.moviesinnetwork.FilmModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("films")
    fun getFilms(): Call<List<FilmModel>>

    //@GET("films?id=1&name=blabla")
    //fun getFilmById(@Query("image") id: String, @Query("name") name:String): Call<FilmModel>
}