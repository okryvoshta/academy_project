package com.ook.academy.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ook.academy.project.data.Movie
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(repository: Repository) : ViewModelWithRepository(repository) {

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData(emptyList())
    private val _movie: MutableLiveData<Movie?> = MutableLiveData(null)
    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _finish: MutableLiveData<Boolean> = MutableLiveData(false)

    val movies: LiveData<List<Movie>> = _movies
    val movie: LiveData<Movie?> = _movie
    val loading: LiveData<Boolean> = _loading
    val finish: LiveData<Boolean> = _finish

    init {
        val scope = CoroutineScope(Dispatchers.Main + CoroutineName("Loading data"))
        scope.launch {
            _loading.value = true
            _movies.value = repository.loadMovies()
            _loading.value = false
        }
    }

    fun openMovieDetails(movie: Movie) {
        _movie.value = movie
    }

    fun closeMovieDetails() {
        _movie.value = null
    }

}
