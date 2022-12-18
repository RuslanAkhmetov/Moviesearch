package com.geekbrain.androidwithkotlin.ui.main

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geekbrain.androidwithkotlin.R
import com.geekbrain.androidwithkotlin.response.item

class MovieAdapter (private var onItemViewClickListener: OnItemViewClickListener):
    RecyclerView.Adapter<MovieAdapter.MovieHolder>(){
    private val TAG = "MovieAdapter"
    private var movies: List<item> = listOf()

    interface OnItemViewClickListener{
        fun onItemViewClick(movie: item)
    }

    fun setMovieList(data: List<item>){
        movies = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view =   LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = movies[position]
        Log.i(TAG, "onBindViewHolder: ${movie.title}" )
        holder.bind(movie, holder.itemView)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieHolder(view: View): RecyclerView.ViewHolder(view){
        //private lateinit var movie: Movie

        val movieImage: ImageView = itemView.findViewById(R.id.movie_image)
        val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        val itemSettingButton : ImageButton = itemView.findViewById( R.id.setting_button)


        fun bind(movie: item, view: View){
            Glide.with(view).load(Uri.parse(movie.image)).into(movieImage)
//            movieImage.setImageURI(Uri.parse(movie.image))
            movieTitle.text = movie.title
            itemView.setOnClickListener{
                onItemViewClickListener.onItemViewClick(movie)
            }
        }
    }

}