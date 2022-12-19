package com.geekbrain.androidwithkotlin.ui.main.detailes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geekbrain.androidwithkotlin.databinding.FragmentDetailsBinding
import com.geekbrain.androidwithkotlin.response.MovieItem


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
        with(binding) {
            arguments?.getParcelable<MovieItem>(BUNDLE_EXTRA)?.apply {
                movieId.text = id
                movieRank.text = rank
                movieTitle.text = title
                movieYear.text = year
                movieCrew.text = crew
                movieImDbRating.text = imDbRating
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
