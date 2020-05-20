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
import kotlinx.android.synthetic.main.fragment_movies_list.*
//import kotlinx.android.synthetic.main.fragment_movies_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), MoviesListFragments.MoviesListListener {

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
        //val toolbar  =  findViewById<Toolbar>(R.id.toolbar)//findViewById(R.id.toolbar);
        openAllMoviesList()
        initButtonListener()
        GetDataFromInet()
    }

    private fun openAllMoviesList(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer,MoviesListFragments(), MoviesListFragments.TAG  )
            //.addToBackStack("Main")
            .commit()
    }

    private fun openFavoriteMoviesList(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer,MoviesListFavoriteFragments(), MoviesListFavoriteFragments.TAG  )
            //.addToBackStack("Favorite")
            .commit()
    }

    private fun openDetailedFragment(moviesItem: MoviesItem) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer,MovieDetailedFragment.newInstance(moviesItem.name,moviesItem.image),MovieDetailedFragment.TAG)
            .addToBackStack("Detaled")
            .commit()
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        LastFragmentAttached = fragment
        if (fragment is MoviesListFragments){
            fragment.listener = this
            Log.d(TAG,"onAttachFragment -> MoviesListFragments")
            allFilmsFragmentAttached = fragment

        }
        if (fragment is MoviesListFavoriteFragments){
            fragment.listener = this
            Log.d(TAG,"onAttachFragment -> MoviesListFavoriteFragments")
        }
        else{
            Log.d(TAG,"onAttachFragment -> ????????")
        }
    }

    private fun initButtonListener(){
        findViewById<BottomNavigationView>(R.id.navigationBottom).setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_allMovies -> {
                    Log.d(TAG,"action_allMovies")
                    openAllMoviesList()
                }
                R.id.action_favoriteMovies -> {
                    Log.d(TAG,"action_allMovies")
                    openFavoriteMoviesList()
                }
            }
            false
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        /*val fragmentsInStack= supportFragmentManager.backStackEntryCount
        Log.d(TAG,"fragmentsInStack =$fragmentsInStack")
        val CurFragment = supportFragmentManager.findFragmentByTag(MoviesListFragments.TAG)

        if (CurFragment is MoviesListFragments) {
            Log.d(TAG,"is MoviesListFragments")
        }

        if (fragmentsInStack>1){
            if (LastFragmentAttached is MoviesListFragments){
                finish()
             }
            else supportFragmentManager.popBackStack()
        } else if (fragmentsInStack==1){
            finish()
        } else {
            super.onBackPressed()
        }*/
    }

    private fun GetDataFromInet() {
        findViewById<ProgressBar>(R.id.progressBar).visibility=View.VISIBLE

        App.instance.api.getFilms()
            .enqueue(object : Callback <List<FilmModel>?> {
                override fun onFailure(call: Call<List<FilmModel>?>, t: Throwable) {
                    Log.d(TAG, "Error internet")
                }
                override fun onResponse(
                    call: Call<List<FilmModel>?>,
                    response: Response<List<FilmModel>?>
                ) {val lastSize = numLastAddIndex
                    if (response.isSuccessful) {
                        response.body()?.forEach {
                            Log.d(TAG, "it.id $it.id")
                            AllMovies.add(MoviesItem(numLastAddIndex++,it.title,it.image,false))
                        }
                    }
                    findViewById<ProgressBar>(R.id.progressBar).visibility=View.INVISIBLE
                    if (allFilmsFragmentAttached is MoviesListFragments){
                        allFilmsFragmentAttached.recyclerView.adapter?.notifyDataSetChanged()
                        allFilmsFragmentAttached.recyclerView.adapter?.notifyItemRangeChanged(lastSize, numLastAddIndex)
                    }
                }
            })
    }

    fun View.snack(message: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(this, message, duration).show()
    }

    override fun onMoviesSelected(moviesItem: MoviesItem, add: Int, position : Int) {
        Log.d(TAG,"onMoviesSelected MoviesItem=$moviesItem add=$add")

        if (add==-1) {GetDataFromInet()}
        else
            if (add==0) {openDetailedFragment(moviesItem)}
            else {
                moviesItem.inFavorite=!moviesItem.inFavorite
                if (FavoriteMovies.indexOf(moviesItem) == -1){
                    //Toast.makeText(applicationContext,"Фильм:${moviesItem.name}. Добавлен в спиок избранных.",Toast.LENGTH_SHORT).show()
                    val AddedMovieItem = moviesItem
                    val AddedPosition = position
                    FavoriteMovies.add(moviesItem)

                    val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    lp.setMargins(0, 0, 0, 10)
                    val mySnackbar =Snackbar.make(this.findViewById(R.id.fragmentContainer), "Отменить последнюю операцию?", Snackbar.LENGTH_SHORT)
                    mySnackbar.view.layoutParams=lp
                    mySnackbar.setAction(
                        "Отмена",
                        {
                            AddedMovieItem.inFavorite=!AddedMovieItem.inFavorite
                            FavoriteMovies.remove(AddedMovieItem)
                            if ((LastFragmentAttached is MoviesListFragments)||(LastFragmentAttached is MoviesListFavoriteFragments)) {
                                LastFragmentAttached.recyclerView.adapter?.notifyItemChanged(AddedPosition)
                            }
                        }
                    ).show()
                }
                else
                {
                    //Toast.makeText(applicationContext,"Фильм:${moviesItem.name}. Удален из спиока избранных.",Toast.LENGTH_SHORT).show()
                    FavoriteMovies.remove(moviesItem)
                    val DeletedMovieItem = moviesItem
                    val DeletedPosition = position
                    val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    lp.setMargins(0, 0, 0, 10)
                    val mySnackbar =Snackbar.make(this.findViewById(R.id.fragmentContainer), "Отменить последнюю операцию?", Snackbar.LENGTH_SHORT)
                    mySnackbar.view.layoutParams=lp
                    mySnackbar.setAction(
                        "Отмена",
                        {
                            DeletedMovieItem.inFavorite=!DeletedMovieItem.inFavorite
                            FavoriteMovies.add(DeletedMovieItem)
                            if ((LastFragmentAttached is MoviesListFragments)||(LastFragmentAttached is MoviesListFavoriteFragments)) {
                                LastFragmentAttached.recyclerView.adapter?.notifyItemChanged(DeletedPosition)
                            }
                        }
                    ).show()
                }
            }
    }
}