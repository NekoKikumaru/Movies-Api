package com.ptzkg.moviesapi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.ptzkg.moviesapi.R
import com.ptzkg.moviesapi.model.Movie
import com.ptzkg.moviesapi.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        var movieArgs = arguments.let { MovieFragmentArgs.fromBundle(it!!) }
        var movieId = movieArgs.movieId
        viewModel.loadResult(movieId)
    }

    fun observeViewModel() {
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        viewModel.getResult().observe(viewLifecycleOwner, Observer {
            result -> bindMovie(result)
        })
    }

    fun bindMovie(movie: Movie) {
        var poster_path = "https://image.tmdb.org/t/p/w500/"+movie.poster_path
        Picasso.get().load(poster_path).placeholder(R.drawable.placeholder).into(imgPoster)
        txtRating.text = txtRating.text.toString()+movie.popularity.toString()
        txtDate.text = txtDate.text.toString()+movie.release_date
        txtTitle.text = movie.title
        txtOverview.text = movie.overview
    }
}
