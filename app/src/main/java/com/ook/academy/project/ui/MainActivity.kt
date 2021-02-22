package com.ook.academy.project.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ook.academy.project.R
import com.ook.academy.project.ui.movie_details.FragmentMoviesDetails
import com.ook.academy.project.ui.movie_list.FragmentMoviesList

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SharedMainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, FragmentMoviesList())
                .commit()
        }

        viewModel = ViewModelProvider(this).get(SharedMainViewModel::class.java)
        with(viewModel) {
            movie.observe(this@MainActivity) {
                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        FragmentMoviesDetails.newInstance(it)
                    ).addToBackStack("MovieDetails")
                    .commit()
            }
            navigateBack.observe(this@MainActivity) {
                onBackPressed()
            }
        }
    }

}