package com.example.moviesinnetwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.instance.api.getFilms()
            .enqueue(object : Callback <List<FilmModel>?> {
                override fun onFailure(call: Call<List<FilmModel>?>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<List<FilmModel>?>,
                    response: Response<List<FilmModel>?>
                ) {
                    //items.clear()
                    if (response.isSuccessful) {
                        response.body()
                            ?.forEach {
                                /*items.add(
                                    MainItem(
                                        it.id,
                                        it.title,
                                        it.image
                                    )
                                )*/
                            }
                    }
                   // adapter.notifyDataSetChanged()
                }
            })


        findViewById<Button>(R.id.buttonGoConnect).setOnClickListener(){
            Log.d("TEST","buttonGoConnect")
            App.instance.api.getFilms()
                .enqueue(object : Callback<List<FilmModel>?> {
                    override fun onFailure(call: Call<List<FilmModel>?>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<List<FilmModel>?>,
                        response: Response<List<FilmModel>?>
                    ) {

                        if (response.isSuccessful) {
                            response.body()
                                ?.forEach {
                                    /*items.add(
                                        MainItem(
                                            it.id,
                                            it.title,
                                            it.image
                                        )
                                    )*/
                                }
                        }
                        //adapter.notifyDataSetChanged()
                    }
                })

        }
    }
}
