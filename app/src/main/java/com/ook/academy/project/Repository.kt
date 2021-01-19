package com.ook.academy.project

import android.content.Context

class Repository(private val context: Context) {
    suspend fun loadMovies() =
        com.ook.academy.project.data.loadMovies(context)
}