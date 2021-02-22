package com.ook.academy.project.ui.movie_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ook.academy.project.R
import com.ook.academy.project.data.Movie

class MovieAdapter(private val callback: IMovieListCallback) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<Movie>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        movies?.get(position)?.let { movie ->
            holder.bind(movie)
            holder.itemView.setOnClickListener {
                callback.openMovie(movie)
            }
        }
    }

    override fun getItemCount(): Int = movies?.size ?: 0


    fun setData(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val coverImageView: ImageView = itemView.findViewById(R.id.movie_image)
    private val tagTextView: TextView = itemView.findViewById(R.id.tag)
    private val nameTextView: TextView = itemView.findViewById(R.id.name)
    private val ratingView: RatingBar = itemView.findViewById(R.id.rating)

    fun bind(data: Movie) {
        Glide.with(coverImageView.context)
            .load(data.poster)
            .into(coverImageView)
        nameTextView.text = data.title
        tagTextView.text = data.genres.joinToString { it.name }
        ratingView.rating = data.ratings / 2
    }
}

