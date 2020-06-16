package com.example.moviesinnetwork


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val nameFilm = itemView.findViewById<TextView>(R.id.nameMovieInAll)
    val imageFilm = itemView.findViewById<ImageView>(R.id.imageMovieInAll)
    val picFavorite = itemView.findViewById<ImageView>(R.id.imageFavoriteAll)
    val indexMovie = itemView.findViewById<TextView>(R.id.indexMovie)

    fun bind(item: MoviesItem){
        nameFilm.text  = item.name
        indexMovie.text = "Id :" + item.id.toString()


        Glide.with(imageFilm.context)
            .load(item.image)
            .placeholder(R.drawable.ic_image_blue)
            .error(R.drawable.ic_error_blue)
            .override(imageFilm.resources.getDimensionPixelSize(R.dimen.image_size))
            .centerCrop()
            .into(imageFilm)

        if (item.inFavorite== true) {picFavorite.setImageResource(R.drawable.ic_favorite_black_24dp)}
        else {picFavorite.setImageResource(R.drawable.ic_favorite_border_black_24dp)}
    }
}