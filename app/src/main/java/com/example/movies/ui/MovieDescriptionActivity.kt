package com.example.movies.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.example.movies.model.Movie
import com.example.movies.R
import com.squareup.picasso.Picasso

class MovieDescriptionActivity : AppCompatActivity() {
    private lateinit var movieTitle: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description)

        val context: Context = this

        val imageView: ImageView  = findViewById(R.id.image)
        val textView: TextView  = findViewById(R.id.desctexte)
        val textView1: TextView = findViewById(R.id.date)
        val textView3: TextView  = findViewById(R.id.rating)
        val toolbar: Toolbar  = findViewById(R.id.toolbar)
        val movie: Movie? = intent.getParcelableExtra("movie")
        val imageUrl: String = ("http://image.tmdb.org/t/p/w500/" + movie?.backdropPath)

        movieTitle = movie!!.title
        toolbar.title = movieTitle
        setSupportActionBar(toolbar)

        Picasso.with(context).load(imageUrl).into(imageView)

        textView.text = movie.overview
        textView1.text = ("Release Date : " + movie.releaseDate)
        textView3.text = ("Rating : " + movie.voteAverage)

        val actionBar:androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_bar2,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.share -> {
            val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,movieTitle)
            startActivity(intent)
            return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
