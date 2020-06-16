package com.example.moviesinnetwork

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesinnetwork.MainActivity.Companion.AllMovies
import kotlinx.android.synthetic.main.fragment_movies_list.*

//import com.example.fragmentsmovie.MainActivity.Companion.AllMovies
//import kotlinx.android.synthetic.main.fragment_movies_list.*

class MoviesListFragments : Fragment (){
    companion object{
         const val TAG = "MoviesListFragments"
    }
    /*object statics {
        @JvmField val TAG = "MoviesListFragments"
    }*/

    var listener : MoviesListListener? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity is MoviesListListener){
            listener = activity as MoviesListListener
        } else {
            throw Exception("Activity must implement MoviesListListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.fragment_movies_list, container, false)
        //return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recyclerView).adapter = MoviesAdapter(
            LayoutInflater.from(activity),
            AllMovies) { moviesItem: MoviesItem, addToFovarite: Int, position: Int ->
            listener?.onMoviesSelected(moviesItem,addToFovarite,position)
            recyclerView.adapter?.notifyItemChanged(position)
        }

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerView)

        recycler.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                Log.d("TAG","dx:$dx dy:$dy ")

                val layoutManager= recycler.layoutManager as LinearLayoutManager
                val x = layoutManager.findLastVisibleItemPosition()
                Log.d("TAG","layoutManager.findLastVisibleItemPosition() = $x ")
                if (layoutManager.findLastVisibleItemPosition()== AllMovies.size-1){

                    listener?.onMoviesSelected(MoviesItem(1,"1","1",false),-1,0)
                    Log.d("TAG","NEW LOAD!!!! ")
                }
            }
        })
    }

    interface MoviesListListener {
        fun onMoviesSelected(moviesItem: MoviesItem,add : Int,position : Int)
    }
}