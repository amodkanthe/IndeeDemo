package com.example.indeedemo.data

import android.content.Context
import android.database.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.indeedemo.model.MovieItem
import java.io.InputStream
import org.json.JSONArray
import org.json.JSONObject




class MovieViewModel : ViewModel() {

    fun getMoview(context: Context): MutableList<MovieItem>{
        var listOfMoview : MutableList<MovieItem> = ArrayList()
        try {
            val jsonObject = JSONObject(readJSONFromAsset(context))
            val jsonArray = jsonObject.optJSONArray("TestData")
            for(i in 0 until jsonArray.length()){
                listOfMoview.add(MovieItem(jsonArray.getJSONObject(i)))
            }
        } catch (e: Exception) {
        }
        return listOfMoview
    }

    fun readJSONFromAsset(context :Context): String? {
        var json: String? = null
        try {
            val  inputStream: InputStream = context.assets.open("indee.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}