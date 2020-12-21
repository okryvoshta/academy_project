package com.ook.academy.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ook.academy.project.data.Movie
import com.ook.academy.project.data.loadMovies
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentMoviesList : Fragment(), IMovieListCallback {
    private lateinit var list: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list = view.findViewById(R.id.list)

        initData()
    }

    private fun initData() {
        val scope = CoroutineScope(Dispatchers.Main + CoroutineName("Loading data"))
        scope.launch {
            list.adapter = MovieAdapter(this@FragmentMoviesList, loadMovies(requireContext()))
        }
    }

    private fun openMoviesDetails(movie: Movie) {
        activity?.apply {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentMoviesDetails.newInstance(movie))
                .addToBackStack(null)
                .commit()
        }
    }

    override fun openMovie(movie: Movie) {
        openMoviesDetails(movie)
    }

}