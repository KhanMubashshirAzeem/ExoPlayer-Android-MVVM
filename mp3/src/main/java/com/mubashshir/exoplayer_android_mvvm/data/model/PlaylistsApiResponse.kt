package com.mubashshir.exoplayer_android_mvvm.data.model

data class PlaylistsApiResponse(
    val success: Boolean,
    val data: DataPlaylist
)

data class DataPlaylist(
    val results: List<SimplePlaylist>,
    val start: Int,
    val total: Int
)