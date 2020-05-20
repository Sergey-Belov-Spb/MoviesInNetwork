package com.example.moviesinnetwork.model

import com.example.moviesinnetwork.FilmModel
import com.google.gson.annotations.SerializedName


data class Response(

    @field:SerializedName("Title")
    val title: String,

    @field:SerializedName("Year")
    val yaer: String,

    @field:SerializedName("Rated")
    val rated: String,

    @field:SerializedName("Released")
    val released: String

)
/*{
    "Released":"05 May 2017",
    "Runtime":"136 min",
    "Genre":"Action, Adventure, Comedy, Sci-Fi",
    "Director":"James Gunn",
    "Writer":"James Gunn, Dan Abnett (based on the Marvel comics by), Andy Lanning (based on the Marvel comics by), Steve Englehart (Star-Lord created by), Steve Gan (Star-Lord created by), Jim Starlin (Gamora and Drax created by), Stan Lee (Groot created by), Larry Lieber (Groot created by), Jack Kirby (Groot created by), Bill Mantlo (Rocket Raccoon created by), Keith Giffen (Rocket Raccoon created by), Steve Gerber (Howard the Duck created by), Val Mayerik (Howard the Duck created by)",
    "Actors":"Chris Pratt, Zoe Saldana, Dave Bautista, Vin Diesel",
    "Plot":"The Guardians struggle to keep together as a team while dealing with their personal family issues, notably Star-Lord's encounter with his father the ambitious celestial being Ego.",
    "Language":"English",
    "Country":"USA",
    "Awards":"Nominated for 1 Oscar. Another 15 wins & 56 nominations.",
    "Poster":"https://m.media-amazon.com/images/M/MV5BNjM0NTc0NzItM2FlYS00YzEwLWE0YmUtNTA2ZWIzODc2OTgxXkEyXkFqcGdeQXVyNTgwNzIyNzg@._V1_SX300.jpg",
    "Ratings":[
    {
        "Source":"Internet Movie Database",
        "Value":"7.6/10"
    },
    {
        "Source":"Rotten Tomatoes",
        "Value":"85%"
    },
    {
        "Source":"Metacritic",
        "Value":"67/100"
    }
    ],
    "Metascore":"67",
    "imdbRating":"7.6",
    "imdbVotes":"537,920",
    "imdbID":"tt3896198",
    "Type":"movie",
    "DVD":"N/A",
    "BoxOffice":"N/A",
    "Production":"N/A",
    "Website":"N/A",
    "Response":"True"
}*/

/*data class ResponseFilm(

    @field:SerializedName("Title")
    val title: String,

    @field:SerializedName("Year")
    val year: String,

    @field:SerializedName("imdbID")
    val imdbID: String,

    @field:SerializedName("Type")
    val type: String,

    @field:SerializedName("Poster")
    val poster: String
)

data class Response(
    @field:SerializedName("Search")
    val search: ArrayList<ResponseFilm>,

    @field:SerializedName("totalResults")
    val totalResults: String,

    @field:SerializedName("Response")
    val response: String



//    @field:SerializedName("country_name")
//    val countryName: String,

//    @field:SerializedName("colors")
//    val colors: String
)*/
