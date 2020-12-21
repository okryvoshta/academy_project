package com.ook.academy.project

import com.ook.academy.project.data.Movie

interface IMovieListCallback {
    fun openMovie(position: Movie)
}