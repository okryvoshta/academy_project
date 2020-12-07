package com.ook.academy.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(val callback: IMovieListCallback) : RecyclerView.Adapter<MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(MockData.movies[position])
        holder.itemView.setOnClickListener {
            callback.handleClick(position)
        }
    }

    override fun getItemCount(): Int = MockData.movies.size

}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val coverImageView: ImageView = itemView.findViewById(R.id.movie_image)
    private val tagTextView: TextView = itemView.findViewById(R.id.tag)
    private val nameTextView: TextView = itemView.findViewById(R.id.name)

    fun bind(data: MovieData) {
        coverImageView.setImageResource(data.coverId)
        nameTextView.setText(data.nameId)
        tagTextView.setText(data.tagId)
    }
}

class MovieData(
    @DrawableRes val coverId: Int,
    @StringRes val nameId: Int,
    @StringRes val tagId: Int,
)
