package com.example.moviesinnetwork

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesinnetwork.MainActivity.Companion.FavoriteMovies

import kotlinx.android.synthetic.main.fragment_movies_list.*

class MoviesListFavoriteFragments : Fragment () {
    companion object{
        const val TAG = "MoviesListFavoriteFragments"
    }
    var listener : MoviesListFragments.MoviesListListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        return  inflater.inflate(R.layout.fragment_movies_list, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.recyclerView).adapter = MoviesAdapter(
            LayoutInflater.from(activity),
            FavoriteMovies
        ) { moviesItem: MoviesItem, long: Int, position: Int ->

            listener?.onMoviesSelected(moviesItem,long,position)
            recyclerView.adapter?.notifyItemRemoved(position)
            recyclerView.adapter?.notifyItemRangeChanged(position, recyclerView.adapter!!.itemCount)
        }
    }

}