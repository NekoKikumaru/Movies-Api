package com.ptzkg.moviesapi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ptzkg.moviesapi.R
import com.ptzkg.moviesapi.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_main.view.*

class MovieAdapter(var movieList: List<Result> = ArrayList()): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Result) {
            var poster_path = "https://image.tmdb.org/t/p/w500/"+movie.poster_path
            Picasso.get().load(poster_path).placeholder(R.drawable.placeholder).into(itemView.imgPoster)
            itemView.txtTitle.setText(movie.title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        Log.d("Adapter >>>> ", "onCreateViewHolder")
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    fun updateList(movieList: List<Result>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }
}