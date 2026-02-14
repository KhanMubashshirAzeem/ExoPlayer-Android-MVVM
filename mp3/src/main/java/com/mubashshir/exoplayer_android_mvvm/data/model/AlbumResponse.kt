// data/model/AlbumResponse.kt
package com.mubashshir.exoplayer_android_mvvm.data.model

data class AlbumResponse(
    val success: Boolean,
    val data: AlbumData?
)

data class AlbumData(
    val id: String,
    val name: String,
    val image: List<ImageXX>,
    val year: String?,
    val label: String?,
    val language: String?,
    val songs: List<Results>,          // ← same Result as song (great reuse!)
    // ... more fields if needed (artists, playCount, etc.)
)