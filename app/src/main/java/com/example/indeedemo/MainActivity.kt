package com.example.indeedemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.indeedemo.data.MovieViewModel
import com.example.indeedemo.databinding.ActivityMainBinding
import com.example.indeedemo.model.MovieItem

class MainActivity : AppCompatActivity() {

    val viewModel : MovieViewModel = MovieViewModel()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rvList.layoutManager = LinearLayoutManager(this)
        val adapter = MovieListAdapter( viewModel.getMoview(this))
        binding.rvList.setAdapter(adapter)



    }


}