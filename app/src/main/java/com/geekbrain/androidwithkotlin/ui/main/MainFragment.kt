package com.geekbrain.androidwithkotlin.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.fragment.app.viewModels
import com.geekbrain.androidwithkotlin.R
import com.geekbrain.androidwithkotlin.databinding.FragmentMainBinding
import com.geekbrain.androidwithkotlin.response.item
import com.geekbrain.androidwithkotlin.ui.main.detailes.DetailsFragment
import com.geekbrain.androidwithkotlin.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private val TAG = "MainFragment"

    //private lateinit var movieRecyclerView: RecyclerView

    private var adapter = MovieAdapter(object : MovieAdapter.OnItemViewClickListener {
        override fun onItemViewClick(movie: item) {
            val fragmentManager=activity?.supportFragmentManager
            Log.i(TAG, "onItemViewClick: ")
            if(fragmentManager != null){
                val bundle = Bundle()
                bundle.putParcelable(DetailsFragment.BUNDLE_EXTRA, movie)
                fragmentManager.beginTransaction()
                    .replace(R.id.container, DetailsFragment.newInstance(bundle))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }


    })

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    //private lateinit var viewModel: MainViewModel
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val observer = Observer<List<item>?> { upDateUI(it) }

        binding.movieRecyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.getTop250Data()?.observe(viewLifecycleOwner, observer)

    }

    private fun upDateUI(movies: List<item>) {

        adapter.setMovieList(movies)
        binding.movieRecyclerView.adapter = adapter

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}