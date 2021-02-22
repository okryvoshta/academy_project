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
    private lateinit var adapter: MovieAdapter
    private lateinit var viewModel: MoviesListViewModel
    private lateinit var sharedViewModel: SharedMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = view.findViewById<RecyclerView>(R.id.list)
        adapter = MovieAdapter(this@FragmentMoviesList)
        list.adapter = adapter
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
        adapter.setData(movies)
    }

    override fun openMovie(movie: Movie) {
        sharedViewModel.openMovieDetails(movie.id)
    }

}