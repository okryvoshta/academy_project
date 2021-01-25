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

class MovieAdapter(private val callback: IMovieListCallback, private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            callback.openMovie(movie)
        }
    }

    override fun getItemCount(): Int = movies.size

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

