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
import com.geekbrain.androidwithkotlin.viewmodel.AppState
import com.geekbrain.androidwithkotlin.R
import com.geekbrain.androidwithkotlin.databinding.FragmentMainBinding
import com.geekbrain.androidwithkotlin.response.MovieItem
import com.geekbrain.androidwithkotlin.ui.main.detailes.DetailsFragment
import com.geekbrain.androidwithkotlin.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private val TAG = "MainFragment"

    //private lateinit var movieRecyclerView: RecyclerView

    private var adapter = MovieAdapter(object : MovieAdapter.OnItemViewClickListener {
        override fun onItemViewClick(movie: MovieItem) {
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
    val viewModel by viewModels<MainViewModel>()

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    //private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //val viewModel by viewModels<MainViewModel>()
        //val observer = Observer<List<MovieItem>?> { upDateUI(it) }
        val observer = Observer<AppState> { upDateUI(it) }

        binding.movieRecyclerView.layoutManager = LinearLayoutManager(context)
//        viewModel.getTop250Data()?.observe(viewLifecycleOwner, observer)
        viewModel.getTop250Data()?.observe(viewLifecycleOwner, observer)

    }

    private fun upDateUI(appState: AppState){
        when(appState){
            is AppState.Success -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                adapter.setMovieList(appState.movieData)
                binding.movieRecyclerView.adapter = adapter
            }

            is AppState.Loading ->{
                binding.mainFragmentLoadingLayout.visibility = View.VISIBLE
            }

            is AppState.Error ->{
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                Snackbar.make(binding.movieRecyclerView, "Error", Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.reload)){
                        viewModel.getTop250Data()
                    }
                    .show()

            }
        }
    }
    /*private fun upDateUI(movies: List<MovieItem>) {

        adapter.setMovieList(movies)
        binding.movieRecyclerView.adapter = adapter

    }*/

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}