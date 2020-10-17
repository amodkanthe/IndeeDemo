package com.example.indeedemo.model

import android.content.res.Resources
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.indeedemo.R
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import org.json.JSONObject


class MovieItem {
    val id: String?
        get() = field
    val name: String?
        get() = field
    val payment_plan: String?
        get() = field
    val release_year: Int?
        get() = field
    val video_duration: Double?
        get() = field
    val type: String?
        get() = field
    val created_on: String?
        get() = field
    val update_on: String?
        get() = field
    val shortDescription: String?
        get() = field
    val description: String?
        get() = field
    val posterLink: String?
        get() = field

    constructor(jsonObject: JSONObject?) {
        id = jsonObject?.optString("id")
        name = jsonObject?.optString("name")
        payment_plan = jsonObject?.optString("payment_plan")
        release_year = jsonObject?.optInt("release_year")
        video_duration = jsonObject?.optDouble("video_duration")
        type = jsonObject?.optString("type")
        created_on = jsonObject?.optString("created_on")
        update_on = jsonObject?.optString("update_on")
        shortDescription = jsonObject?.optString("shortDescription")
        description = jsonObject?.optString("description")
        posterLink = jsonObject?.optString("posterLink")
    }


}

@BindingAdapter("bind:imageUrl")
public fun loadImage(view: ImageView, imageUrl: String?) {
    val resources: Resources = view.context.getResources()
    val resourceId: Int = resources.getIdentifier(
        imageUrl, "drawable",
        view.context.getPackageName()
    )
    Picasso.get()
        .load(resourceId)
        .networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.OFFLINE)
        .memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)
        .fit()
        .into(view, object : Callback {
            override fun onSuccess() {
               // Log.d(TAG, "success")
            }

            override fun onError(e: Exception?) {
            }
        })
}