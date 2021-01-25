package com.ook.academy.project.ui.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ook.academy.project.model.MovieRepository

@Suppress("UNCHECKED_CAST")
class MovieDetailsViewModelProvider(
    private val repository: MovieRepository,
    private val movieId: Int
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MovieDetailsViewModel(repository, movieId) as T
}