package com.mubashshir.exoplayer_android_mvvm.data.remote

import com.mubashshir.exoplayer_android_mvvm.data.model.AlbumResponse
import com.mubashshir.exoplayer_android_mvvm.data.model.AlbumsApiResponse
import com.mubashshir.exoplayer_android_mvvm.data.model.ArtistsApiResponse
import com.mubashshir.exoplayer_android_mvvm.data.model.SongsApiResponce
import retrofit2.http.GET
import retrofit2.http.Query

interface SongApiService {

    @GET("search/songs")
    suspend fun searchSongs(
        @Query("query") query: String,
        @Query("name") name: String? = null,
        @Query("limit") limit: Int = 20,
        @Query("page") page: Int = 1
    ): SongsApiResponce

    @GET("albums")
    suspend fun getAlbum(
        @Query("id") id: String
    ): AlbumResponse

    @GET("search/artists")
    suspend fun searchArtists(
        @Query("query") query: String,
        @Query("limit") limit: Int = 20
    ): ArtistsApiResponse

    @GET("search/albums")
    suspend fun searchAlbums(
        @Query("query") query: String,
        @Query("limit") limit: Int = 20
    ): AlbumsApiResponse
}
