package com.ook.academy.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ook.academy.project.data.Movie

class FragmentMoviesDetails : Fragment() {

    private lateinit var coverIV: ImageView
    private lateinit var nameTV: TextView
    private lateinit var tagTV: TextView
    private lateinit var list: RecyclerView
    private lateinit var rating: RatingBar

    companion object {
        private const val MOVIE_PARAM = "movie"

        fun newInstance(movie: Movie): FragmentMoviesDetails {
            return FragmentMoviesDetails().apply {
                val bundle = Bundle()
                bundle.putParcelable(MOVIE_PARAM, movie)
                arguments = bundle
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_movies_details, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coverIV = view.findViewById(R.id.movie_image)
        nameTV = view.findViewById(R.id.name)
        tagTV = view.findViewById(R.id.tag)
        list = view.findViewById(R.id.list)
        rating = view.findViewById(R.id.rating)

        view.findViewById<View>(R.id.path).setOnClickListener {
            activity?.onBackPressed()
        }

        requireArguments().getParcelable<Movie>(MOVIE_PARAM)?.let { setMovieData(it) }
    }

    private fun setMovieData(movie: Movie) {
        Glide.with(coverIV).load(movie.poster).into(coverIV)
        nameTV.text = movie.title
        tagTV.text = movie.genres.joinToString { it.name }
        rating.rating = movie.ratings / 2
        if (movie.actors.isEmpty()) {
            list.visibility = GONE
        } else {
            list.visibility = VISIBLE
            list.adapter = ActorAdapter(movie.actors)
        }
    }
}