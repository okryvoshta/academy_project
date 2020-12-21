package com.ook.academy.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ook.academy.project.pojo.MovieData

class FragmentMoviesDetails : Fragment() {

    private lateinit var coverIV: ImageView
    private lateinit var nameTV: TextView
    private lateinit var tagTV: TextView
    private lateinit var list: RecyclerView

    companion object {
        private const val PARAM_POSITION = "position"

        fun newInstance(position: Int): FragmentMoviesDetails {
            return FragmentMoviesDetails().apply {
                arguments = bundleOf(PARAM_POSITION to position)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_movies_details, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coverIV = view.findViewById(R.id.movie_image)
        nameTV = view.findViewById(R.id.name)
        tagTV = view.findViewById(R.id.tag)
        list = view.findViewById(R.id.list)

        view.findViewById<View>(R.id.path).setOnClickListener {
            activity?.onBackPressed()
        }

        setMovieData(MockData.movies[requireArguments().getInt(PARAM_POSITION)])
        list.adapter = ActorAdapter()
    }

    private fun setMovieData(movie: MovieData) {
        coverIV.setImageResource(movie.coverId)
        nameTV.setText(movie.nameId)
        tagTV.setText(movie.tagId)
    }
}