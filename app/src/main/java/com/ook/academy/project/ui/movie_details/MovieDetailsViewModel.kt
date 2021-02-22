package com.ook.academy.project.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ook.academy.project.data.Movie
import com.ook.academy.project.model.MovieRepository
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: MovieRepository, private val movieId: Int) :
    ViewModel() {

    private val _movie: MutableLiveData<Movie?> = MutableLiveData(null)
    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)

    val movie: LiveData<Movie?> = _movie
    val loading: LiveData<Boolean> = _loading

    init {
        viewModelScope.launch(CoroutineName("Loading data")) {
            _loading.value = true
            _movie.value = repository.loadMovie(movieId)
            _loading.value = false
        }
    }

}
