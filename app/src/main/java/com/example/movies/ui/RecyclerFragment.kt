package com.example.movies.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.adapter.MoviesAdapter
import com.example.movies.model.Movie
import com.example.movies.model.MovieResponse
import com.example.movies.R
import com.example.movies.rest.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass.
 */
class RecyclerFragment : Fragment(),MoviesAdapter.MovieClicked  {
    private val name: String = MainActivity::class.java.simpleName
    private val baseUrl = "http://api.themoviedb.org/3/"
    companion object{
        private var retrofit: Retrofit?=null
    }
    private lateinit var recyclerView : RecyclerView
    private var context1: Context? = context
    private val apiKey = "b6f6fcfbb225d8c500e4404655ccadcc"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        context1=context
        val view : View = inflater.inflate(R.layout.recycler_layout,container, false)
        recyclerView = view.findViewById(R.id.recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        connectAndGetApiData()
        return view

    }

    private fun connectAndGetApiData(){

        if( retrofit == null){
            retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        }
        val popularApiService: ApiService?  = retrofit?.create(ApiService :: class.java)
        val call : Call<MovieResponse>? = popularApiService?.getTopMovies(apiKey)
        call?.enqueue(object: Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse> ) {
                val movieResponse: MovieResponse = response.body()
                val movies: List<Movie>  = movieResponse.results
                recyclerView.adapter = MoviesAdapter( movies, R.layout.list_item_movies, context,this@RecyclerFragment )
            }

            override fun onFailure(call: Call<MovieResponse>, throwable:  Throwable) {
                Log.e(name, throwable.toString())
            }
        })

    }
    override fun onMovieClicked(movie: Movie) {
        val intent = Intent(context, MovieDescriptionActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }
}
