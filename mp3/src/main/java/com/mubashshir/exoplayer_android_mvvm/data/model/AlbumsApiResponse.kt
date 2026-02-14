
package com.mubashshir.exoplayer_android_mvvm.data.model

data class AlbumsApiResponse(
    val success: Boolean,
    val data: DataAlbum
)

data class DataAlbum(
    val results: List<SimpleAlbum>,
    val start: Int,
    val total: Int
)