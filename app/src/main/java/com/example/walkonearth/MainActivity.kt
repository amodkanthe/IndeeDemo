package com.example.walkonearth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walkonearth.data.TeacherViewModel
import com.example.walkonearth.databinding.ActivityMainBinding
import com.example.walkonearth.model.*
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    val viewModel : TeacherViewModel = TeacherViewModel()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rvList.layoutManager = LinearLayoutManager(this)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setTitle("")
        loadBgImage()
        viewModel.getTeacher(this).observe(this, Observer {
            var items : MutableList<Any> = arrayListOf()
            items.add(TeacherDetails(it?.photo,it?.firstName,it?.lastName,it?.level))
            items.add(TeacherBio(it?.bio))
            items.add(TitleAccreditations(getString(R.string.acreditations)))
            items.addAll(it?.teachingAccreditations?.split("\n")?.map { Accreditation(
                it
            ) }?: emptyList())
            items.add(IntroVideo(it?.introVideo , it?.firstName))
            items.add(SessionsTitle(it?.firstName + getString(R.string.apostrophe) + getString(R.string.spacer)+ getString(
                            R.string.sessions)))
            items.addAll(it?.classes?.filterNotNull() ?: arrayListOf())

            val adapter = TeacherDetailAdapter(items)
            binding.rvList.setAdapter(adapter)

        })



    }

    fun loadBgImage(){
        Picasso.get()
            .load(R.drawable.home_bg)
            .placeholder(R.color.cyan_blue)
            .fit()
            .centerCrop()
            .into(findViewById(R.id.ivBg), object : Callback {
                override fun onSuccess() {
                    // Log.d(TAG, "success")
                }

                override fun onError(e: Exception?) {
                }
            })
    }

}