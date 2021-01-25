package com.ook.academy.project.model

import android.content.Context

class MovieRepository(private val context: Context) {
    suspend fun loadMovies() =
        com.ook.academy.project.data.loadMovies(context)

    suspend fun loadMovie(id: Int) =
        com.ook.academy.project.data.loadMovies(context).find { it.id == id }
}