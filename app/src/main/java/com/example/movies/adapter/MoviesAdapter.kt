package com.example.movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.model.Movie
import com.example.movies.R
import com.squareup.picasso.Picasso

class MoviesAdapter(var movies: List<Movie>,private var rowLayout: Int,var context: Context?, var movieClicked: MovieClicked ):
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private val imagePath: String  = "http://image.tmdb.org/t/p/w342//"

    interface MovieClicked{
        fun onMovieClicked(movie: Movie)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var movieImage: ImageView = itemView.findViewById(R.id.movie_image)

        init {
            movieImage.setOnClickListener(this)
        }

        override fun onClick(v: View?) = movieClicked.onMovieClicked(movies[adapterPosition])

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(rowLayout,parent,false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val imageUrl: String = imagePath + movies[position].posterPath
        Picasso.with(context).load(imageUrl).into(holder.movieImage)
    }
}


