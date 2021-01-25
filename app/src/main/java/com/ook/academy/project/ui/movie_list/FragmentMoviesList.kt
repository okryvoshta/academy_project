package com.ook.academy.project.ui.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ook.academy.project.R
import com.ook.academy.project.data.Movie
import com.ook.academy.project.model.MovieRepository
import com.ook.academy.project.ui.SharedMainViewModel

class FragmentMoviesList : Fragment(), IMovieListCallback {
    private lateinit var loader: View
    private lateinit var list: RecyclerView
    private lateinit var viewModel: MoviesListViewModel
    private lateinit var sharedViewModel: SharedMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list = view.findViewById(R.id.list)
        loader = view.findViewById(R.id.loader)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedMainViewModel::class.java)
        viewModel = ViewModelProvider(
            this,
            MovieListViewModelProvider(MovieRepository(requireActivity().applicationContext))
        ).get(MoviesListViewModel::class.java)

        with(viewModel) {
            loading.observe(viewLifecycleOwner, ::setLoading)
            movies.observe(viewLifecycleOwner, ::setMovies)
        }

    }

    private fun setLoading(loading: Boolean) {
        loader.isVisible = loading
    }

    private fun setMovies(movies: List<Movie>) {
        list.adapter = MovieAdapter(this@FragmentMoviesList, movies)
    }

    private fun openMoviesDetails(movie: Movie) {
        sharedViewModel.openMovieDetails(movie.id)
    }

    override fun openMovie(movie: Movie) {
        openMoviesDetails(movie)
    }

}