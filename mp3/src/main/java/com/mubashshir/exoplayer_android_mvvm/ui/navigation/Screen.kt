package com.mubashshir.exoplayer_android_mvvm.ui.navigation

import android.net.Uri

sealed class Screen(val route: String) {
    object MP3 : Screen("mp3")
    object MP4 : Screen("mp4")


    // Detail screens
    object Search : Screen("search")
    object FullPlayer : Screen("full_player")

    object ArtistDetail : Screen("artist_detail/{artistId}") {
        fun createRoute(artistId: String): String {
            return "artist_detail/${Uri.encode(artistId)}"
        }
    }

    object AlbumDetail : Screen("album_detail/{albumId}") {
        fun createRoute(albumId: String): String {
            return "album_detail/${Uri.encode(albumId)}"
        }
    }
}