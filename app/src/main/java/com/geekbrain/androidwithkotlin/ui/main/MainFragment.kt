package com.geekbrain.androidwithkotlin.ui.main

import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geekbrain.androidwithkotlin.R
import com.geekbrain.androidwithkotlin.database.Movie
import com.geekbrain.androidwithkotlin.databinding.ActivityMainBinding
import com.geekbrain.androidwithkotlin.databinding.FragmentMainBinding
import com.geekbrain.androidwithkotlin.databinding.ListItemMovieBinding
import com.geekbrain.androidwithkotlin.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private val TAG = "MainFragment"

    private lateinit var movieRecyclerView: RecyclerView

    private var adapter: MovieAdapter? = null

    private lateinit var binding: FragmentMainBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater)
        binding.movieRecyclerView.layoutManager = LinearLayoutManager(context)

        upDateUI()

        return binding.root
    }

    private fun upDateUI() {
        val movies = viewModel.getTop250Data()?.value
        Log.i(TAG, "upDateUI: ${movies?.size}")
        adapter = movies?.let { MovieAdapter(it) }
        binding.movieRecyclerView.adapter = adapter
    }

    private inner class MovieHolder(view: View): RecyclerView.ViewHolder(view){
        private lateinit var movie: Movie

        val movieImage: ImageView = itemView.findViewById(R.id.movie_image)
        val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        val itemSettingButton :ImageButton = itemView.findViewById( R.id.setting_button)

        fun bind(movie: Movie){
            //movieImage.setImageURI(Uri.parse(movie.Image))
            movieTitle.text = movie.Title

        }
    }

    private inner class MovieAdapter (val movies: List<Movie>): RecyclerView.Adapter<MovieHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
            val view = layoutInflater.inflate(R.layout.list_item_movie, parent, false)
            return MovieHolder(view)
        }

        override fun onBindViewHolder(holder: MovieHolder, position: Int) {
            val movie = movies[position]
            holder.bind(movie)
        }

        override fun getItemCount(): Int {
            return movies.size
        }

    }

}