package com.ook.academy.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentMoviesDetails : Fragment() {

    companion object {
        private const val PARAM_POSITION = "position"

        fun newInstance(position: Int): FragmentMoviesDetails {
            val result = FragmentMoviesDetails()
            val args = Bundle(1)
            args.putInt(PARAM_POSITION, position)
            result.arguments = args
            return result
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_movies_details, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = MockData.movies[requireArguments().getInt(PARAM_POSITION)]

        view.findViewById<ImageView>(R.id.movie_image).setImageResource(data.coverId)
        view.findViewById<TextView>(R.id.name).setText(data.nameId)
        view.findViewById<TextView>(R.id.tag).setText(data.tagId)
        view.findViewById<View>(R.id.path).setOnClickListener {
            activity?.onBackPressed()
        }
    }
}