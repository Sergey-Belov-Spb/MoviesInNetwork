package com.example.moviesinnetwork

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class MoviesAdapter (private val layoutInflater: LayoutInflater,
                     private val items: List<MoviesItem>,
                     private val listener: (moviesItem: MoviesItem, addToFovarite : Int, position : Int) -> Unit) : RecyclerView.Adapter<MoviesViewHolder>(){
    companion object{
        const val TAG = "MoviesAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(layoutInflater.inflate(R.layout.item_movies, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(items[position])

        holder.itemView.setOnClickListener {
            listener.invoke(items[position],0,position)
        }

        holder.itemView.findViewById<ImageView>(R.id.imageFavoriteAll).setOnClickListener(){
            Log.d(TAG,"imageFavoriteAll->setOnLongClickListener")
            listener.invoke(items[position],1,position)
        }
    }
}