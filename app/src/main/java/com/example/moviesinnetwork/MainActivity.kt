package com.example.moviesinnetwork

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
//import kotlinx.android.synthetic.main.fragment_movies_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG = "MainActivity"

        var AllMovies: MutableList<MoviesItem> = ArrayList()
        var FavoriteMovies: MutableList<MoviesItem> = ArrayList()
        var LastFragmentAttached: Fragment = Fragment()
        var allFilmsFragmentAttached: Fragment = Fragment()
        var numLastAddIndex:Int =0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }
}
