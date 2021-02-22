package com.ook.academy.project.ui.movie_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ook.academy.project.model.MovieRepository
import com.ook.academy.project.data.Movie
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch

class MoviesListViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData(emptyList())
    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)

    val movies: LiveData<List<Movie>> = _movies
    val loading: LiveData<Boolean> = _loading

    init {
        viewModelScope.launch(CoroutineName("Loading movies")) {
            _loading.value = true
            _movies.value = repository.loadMovies()
            _loading.value = false
        }
    }

}
