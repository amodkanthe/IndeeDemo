package com.example.indeedemo

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.indeedemo.databinding.MovieRowLayoutBinding
import com.example.indeedemo.model.MovieItem


class MovieListAdapter(private val items: List<MovieItem>) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: MovieRowLayoutBinding =
            MovieRowLayoutBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        print("amod-----------------123")
        return  items.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: MovieItem = items.get(position)
        holder.bind(movie)
    }

    class MovieViewHolder(val binding: MovieRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {


        /**
         * We will use this function to bind instance of Movie to the row
         */
        fun bind(model: MovieItem?) {
            binding?.model = model
            binding?.executePendingBindings()
        }
    }


}