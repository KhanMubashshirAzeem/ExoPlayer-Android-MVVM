// data/model/PlaylistResponse.kt
package com.mubashshir.exoplayer_android_mvvm.data.model

data class PlaylistResponse(
    val success: Boolean,
    val data: PlaylistData?
)

data class PlaylistData(
    val id: String,
    val name: String,
    val image: List<ImageXX>,
    val followerCount: Int?,
    val songCount: Int?,
    val songs: List<Results>,          // ← again, reuse Result
    // ... more fields (description, user, etc.)
)