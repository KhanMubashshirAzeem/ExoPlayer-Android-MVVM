package com.mubashshir.exoplayer_android_mvvm.data.model

data class ArtistsApiResponse(
    val success: Boolean,
    val data: DataArtist
)

data class DataArtist(
    val results: List<SimpleArtist>,
    val start: Int,
    val total: Int
)