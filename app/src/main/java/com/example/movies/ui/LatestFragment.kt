package com.example.movies.ui

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
import com.example.movies.adapter.MoviesAdapter.MovieClicked
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
class LatestFragment : Fragment(), MovieClicked {
    private var recyclerView: RecyclerView? = null
    //private var context: Context? = null
    var adapter: MoviesAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
      //  context = getContext()
        val view = inflater.inflate(R.layout.recycler_layout, container, false)
        recyclerView = view.findViewById<View>(R.id.recycler) as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = GridLayoutManager(context, 2)
        connectAndGetApiData()
        return view
    }

    private fun connectAndGetApiData() {
        if (retrofit == null) {
            retrofit =
                Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
        }
        val popularApiService =
            retrofit!!.create(
                ApiService::class.java
            )
        val call =
            popularApiService.getPopularMovies(API_KEY)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                val movieResponse = response.body()
                val movies =
                    movieResponse.results
                recyclerView!!.adapter = MoviesAdapter(
                    movies,
                    R.layout.list_item_movies,
                    getContext(),
                    this@LatestFragment
                )
                Log.d(
                    TAG,
                    "Number of movies received: " + movies.size
                )
            }

            override fun onFailure(
                call: Call<MovieResponse>,
                throwable: Throwable
            ) {
                Log.e(TAG, throwable.toString())
            }
        })
    }

    override fun onMovieClicked(movie: Movie) { // Toast.makeText(getContext(),movie.getTitle(),Toast.LENGTH_LONG).show();
        val intent = Intent(getContext(), MovieDescriptionActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
        // String image_url = IMAGE_URL_BASE_PATH + movie.getPosterPath();
// intent.putExtra("desc",movie.getOverview());
//intent.putExtra("image",("http://image.tmdb.org/t/p/w342//" + movie.getPosterPath()));
    }

    companion object {
        private const val TAG = "MoviesApp"
        const val BASE_URL = "http://api.themoviedb.org/3/"
        const val IMAGE_URL_BASE_PATH = "http://image.tmdb.org/t/p/w342//"
        private var retrofit: Retrofit? = null
        private const val API_KEY = "b6f6fcfbb225d8c500e4404655ccadcc"
    }
}