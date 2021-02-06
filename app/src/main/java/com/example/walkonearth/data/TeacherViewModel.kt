package com.example.walkonearth.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walkonearth.model.Teacher
import com.google.gson.Gson
import java.io.InputStream
import org.json.JSONObject




class TeacherViewModel : ViewModel() {

    fun getTeacher(context: Context): MutableLiveData<Teacher?> {
        var teacher = MutableLiveData<Teacher?>()
        val gson = Gson()
        teacher.value = gson.fromJson(readJSONFromAsset(context), Teacher::class.java)
        return teacher
    }

    fun readJSONFromAsset(context :Context): String? {
        var json: String? = null
        try {
            val  inputStream: InputStream = context.assets.open("walkonearth.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}