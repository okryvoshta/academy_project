package com.ook.academy.project

import com.ook.academy.project.pojo.ActorData
import com.ook.academy.project.pojo.MovieData

object MockData {
    val movies = listOf(
        MovieData(
            R.drawable.movie_avengers,
            R.string.name_avengers,
            R.string.tag_avengers
        ),
        MovieData(
            R.drawable.movie_tenet,
            R.string.name_tenet,
            R.string.tag_tenet
        ),
        MovieData(
            R.drawable.movie_black_widow,
            R.string.name_black_widow,
            R.string.tag_black_widow
        ),
        MovieData(
            R.drawable.movie_wonder_woman,
            R.string.name_wonder_woman,
            R.string.tag_wonder_woman
        ),
    )

    val actors = listOf(
        ActorData(R.drawable.chris_evans, R.string.chris_evans),
        ActorData(R.drawable.mark_ruffalo, R.string.mark_ruffalo),
        ActorData(R.drawable.chris_hemsw, R.string.chris_hemsw),
        ActorData(R.drawable.robert_downey, R.string.robert_downey),
    )
}