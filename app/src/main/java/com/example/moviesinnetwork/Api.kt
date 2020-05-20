package com.example.moviesinnetwork

import com.example.moviesinnetwork.FilmModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    //@GET ("?apikey=7da3fa59&s=xxx&type=movie&y=2002")//@GET("films")
    @GET ("?i=tt3896198&apikey=7da3fa59")
    fun getFilms(): Call<List<FilmModel>>

    @GET("films?id=1&name=blabla")
    fun getFilmById(@Query("image") id: String, @Query("name") name:String): Call<FilmModel>
}