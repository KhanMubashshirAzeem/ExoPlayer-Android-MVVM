package com.mubashshir.exoplayer_android_mvvm.data.model

data class SimplePlaylist(
    val id: String,
    val name: String,
    val url: String,
    val image: List<ImageXX>,
    val songCount: Int,
    val followerCount: Int,
    val language: String
)