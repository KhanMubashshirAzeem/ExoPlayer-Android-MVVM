
package com.mubashshir.exoplayer_android_mvvm.data.model

data class SimpleAlbum(
    val id: String,
    val name: String,
    val url: String,
    val image: List<ImageXX>,
    val artists: Artists,
    val songCount: Int,
    val language: String,
    val year: String
)