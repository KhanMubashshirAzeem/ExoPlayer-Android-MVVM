package com.mubashshir.exoplayer_android_mvvm.data.repository

import com.mubashshir.exoplayer_android_mvvm.data.model.AlbumResponse
import com.mubashshir.exoplayer_android_mvvm.data.model.SimpleAlbum
import com.mubashshir.exoplayer_android_mvvm.data.model.SimpleArtist
import kotlinx.coroutines.flow.Flow
import com.mubashshir.exoplayer_android_mvvm.data.model.Results

interface SongRepository {

    fun searchSongs(
        query: String,
        name: String? = null,
    ): Flow<Result<List<Results>>>

    fun getAlbum(
        albumId: String
    ): Flow<Result<AlbumResponse>>

    fun searchArtists(
        query: String,
    ): Flow<Result<List<SimpleArtist>>>

    fun searchAlbums(
        query: String,
    ): Flow<Result<List<SimpleAlbum>>>
}
