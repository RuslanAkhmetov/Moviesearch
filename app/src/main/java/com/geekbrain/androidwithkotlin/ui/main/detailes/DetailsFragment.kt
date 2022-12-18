package com.geekbrain.androidwithkotlin.ui.main.detailes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geekbrain.androidwithkotlin.databinding.FragmentDetailsBinding
import com.geekbrain.androidwithkotlin.response.item


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding
        get() = _binding!!


    companion object {
        const val BUNDLE_EXTRA = "movie"

        @JvmStatic
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = arguments?.getParcelable<item>(BUNDLE_EXTRA)

        if (movie != null) {
            binding.movieId.text = movie.id
            binding.movieRank.text = movie.rank
            binding.movieTitle.text = movie.title
            binding.movieYear.text = movie.year
            binding.movieCrew.text = movie.crew
            binding.movieImDbRating.text = movie.imDbRating
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
