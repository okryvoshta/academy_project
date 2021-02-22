package com.ook.academy.project.ui.movie_list

import com.ook.academy.project.data.Movie

interface IMovieListCallback {
    fun openMovie(movie: Movie)
}