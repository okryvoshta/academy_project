package com.ook.academy.project.ui.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ook.academy.project.model.MovieRepository

class MovieListViewModelProvider(private val repository: MovieRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MoviesListViewModel(repository) as T
}