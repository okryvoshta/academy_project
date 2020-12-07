package com.ook.academy.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentMoviesList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.movie).setOnClickListener {
            openMoviesDetails()
        }
    }

    private fun openMoviesDetails() {
        activity?.apply {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentMoviesDetails()).addToBackStack(null)
                .commit()
        }
    }

}