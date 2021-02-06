package com.example.walkonearth.model

import android.content.res.Resources
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.walkonearth.R
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.gson.annotations.SerializedName
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

data class Teacher(

	@field:SerializedName("firstName")
	val firstName: String? = null,

	@field:SerializedName("lastName")
	val lastName: String? = null,

	@field:SerializedName("teacherId")
	val teacherId: String? = null,

	@field:SerializedName("introVideo")
	val introVideo: String? = null,

	@field:SerializedName("level")
	val level: String? = null,

	@field:SerializedName("teachingAccreditations")
	val teachingAccreditations: String? = null,

	@field:SerializedName("classes")
	val classes: List<ClassesItem?>? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("bio")
	val bio: String? = null
)

data class ClassesItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class TeacherDetails (
	val photo: String? = null,
	val firstName: String? = null,
	val lastName: String? = null,
	val level: String? = null

)

data class TeacherBio (
	val bio: String? = null
)

data class TitleAccreditations (
	val title: String? = null
)

data class SessionsTitle (
	val title: String? = null
)

data class Accreditation (
	val accreditation: String? = null
)

data class IntroVideo (
	val introVideo: String? = null,
	val firstName: String? = null
)

@BindingAdapter("bind:imageUrl")
public fun loadImage(view: ImageView, imageUrl: String?) {
	Picasso.get()
		.load(imageUrl)
		.placeholder(R.color.cyan_blue)
		.fit()
		.centerCrop()
		.into(view, object : Callback {
			override fun onSuccess() {
				// Log.d(TAG, "success")
			}

			override fun onError(e: Exception?) {
			}
		})
}


interface PlayerStateCallback {
	/**
	 * Callback to when the [PlayerView] has fetched the duration of video
	 **/
	fun onVideoDurationRetrieved(duration: Long, player: Player)

	fun onVideoBuffering(player: Player)

	fun onStartedPlaying(player: Player)

	fun onFinishedPlaying(player: Player)
}


@BindingAdapter("video_url", "on_state_change")
fun PlayerView.loadVideo(video_url: String, callback: PlayerStateCallback) {
	if (video_url == null) return
	val player = ExoPlayerFactory.newSimpleInstance(
		context, DefaultRenderersFactory(context), DefaultTrackSelector(),
		DefaultLoadControl()
	)

	//player.playWhenReady = true
	player.repeatMode = Player.REPEAT_MODE_ALL
	// When changing track, retain the latest frame instead of showing a black screen
	setKeepContentOnPlayerReset(true)
	// We'll show the controller
	this.useController = true
	// Provide url to load the video from here
	val mediaSource = ExtractorMediaSource.Factory(
		DefaultHttpDataSourceFactory("Demo")
	).createMediaSource(Uri.parse(video_url))

	player.prepare(mediaSource)

	this.player = player

	this.player!!.addListener(object : Player.EventListener {

//		override fun onPlayerError(error: ExoPlaybackException?) {
//			//super.onPlayerError(error)
//			//this@loadVideo.context.toast("Oops! Error occurred while playing media.")
//		}

		override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
			super.onPlayerStateChanged(playWhenReady, playbackState)

			if (playbackState == Player.STATE_BUFFERING) callback.onVideoBuffering(player) // Buffering.. set progress bar visible here
			if (playbackState == Player.STATE_READY){
				// [PlayerView] has fetched the video duration so this is the block to hide the buffering progress bar
				callback.onVideoDurationRetrieved((this@loadVideo.player as SimpleExoPlayer).duration, player)
			}
			if (playbackState == Player.STATE_READY && player.playWhenReady){
				// [PlayerView] has started playing/resumed the video
				callback.onStartedPlaying(player)
			}
		}
	})
}






