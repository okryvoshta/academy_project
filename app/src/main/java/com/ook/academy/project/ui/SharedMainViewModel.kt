package com.ook.academy.project.ui

import androidx.lifecycle.ViewModel

class SharedMainViewModel : ViewModel() {
    val movie: SingleLiveEvent<Int> = SingleLiveEvent()
    val navigateBack: SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun openMovieDetails(movieId: Int) {
        movie.value = movieId
    }

    fun closeMovieDetails() {
        navigateBack.value = true
    }
}