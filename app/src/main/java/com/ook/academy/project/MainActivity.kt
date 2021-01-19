package com.ook.academy.project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            ViewModelWithRepositoryFactory(Repository(applicationContext))
        ).get(MoviesViewModel::class.java)

        with(viewModel) {
            movie.observe(this@MainActivity) {
                supportFragmentManager.beginTransaction()
                    .add(
                        R.id.fragment_container,
                        if (it == null) FragmentMoviesList() else FragmentMoviesDetails()
                    )
                    .commit()
            }
            finish.observe(this@MainActivity) {
                if (it) finish()
            }

        }
    }

    override fun onBackPressed() {
        viewModel.closeMovieDetails()
    }

}