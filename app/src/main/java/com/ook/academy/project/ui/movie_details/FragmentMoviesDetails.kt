package com.ook.academy.project.ui.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ook.academy.project.ActorAdapter
import com.ook.academy.project.R
import com.ook.academy.project.data.Movie
import com.ook.academy.project.model.MovieRepository
import com.ook.academy.project.ui.SharedMainViewModel

class FragmentMoviesDetails : Fragment() {

    private lateinit var sharedViewModel: SharedMainViewModel
    private lateinit var coverIV: ImageView
    private lateinit var nameTV: TextView
    private lateinit var tagTV: TextView
    private lateinit var list: RecyclerView
    private lateinit var rating: RatingBar
    private lateinit var loader: View

    private lateinit var viewModel: MovieDetailsViewModel

    companion object {
        private const val MOVIE_ID_PARAM = "movie"

        fun newInstance(movieId: Int): FragmentMoviesDetails {
            return FragmentMoviesDetails().apply {
                arguments = bundleOf(MOVIE_ID_PARAM to movieId)
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
        loader = view.findViewById(R.id.loader)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedMainViewModel::class.java)
        viewModel = ViewModelProvider(
            this,
            MovieDetailsViewModelProvider(
                MovieRepository(
                    requireActivity().applicationContext
                ), requireArguments().getInt(MOVIE_ID_PARAM)
            )
        ).get(MovieDetailsViewModel::class.java)

        view.findViewById<View>(R.id.path).setOnClickListener {
            sharedViewModel.closeMovieDetails()
        }

        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            movie?.let { setMovieData(it) }
        }
        viewModel.loading.observe(viewLifecycleOwner, ::setLoading)

    }

    private fun setLoading(loading: Boolean) {
        loader.isVisible = loading
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