package com.ptzkg.moviesapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ptzkg.moviesapi.R
import com.ptzkg.moviesapi.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_main.view.*

class MovieAdapter(var movieList: List<Result> = ArrayList()): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var mClickListener: ClickListener? = null

    fun setClickListener(clickListener: ClickListener) {
        this.mClickListener = clickListener
    }

    inner class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var movie: Result

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Result) {
            this.movie = movie
            var poster_path = "https://image.tmdb.org/t/p/w500/"+movie.poster_path
            Picasso.get().load(poster_path).placeholder(R.drawable.placeholder).into(itemView.imgPoster)
            itemView.txtTitle.setText(movie.title)
        }

        override fun onClick(v: View?) {
            mClickListener?.onClick(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
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

    interface ClickListener {
        fun onClick(movie: Result)
    }
}