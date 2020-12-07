package com.ook.academy.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentMoviesList : Fragment(), IMovieListCallback {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.list).apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = MovieAdapter(this@FragmentMoviesList)
        }
    }

    private fun openMoviesDetails(position: Int) {
        activity?.apply {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentMoviesDetails.newInstance(position))
                .addToBackStack(null)
                .commit()
        }
    }

    override fun handleClick(position: Int) {
        openMoviesDetails(position)
    }

}