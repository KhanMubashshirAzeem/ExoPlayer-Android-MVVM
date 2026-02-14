package com.mubashshir.exoplayer_android_mvvm.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mubashshir.exoplayer_android_mvvm.ui.components.FullPlayerScreen
import com.mubashshir.exoplayer_android_mvvm.ui.screens.MP4Screen
import com.mubashshir.exoplayer_android_mvvm.ui.screens.home.MP3MainScreen
import com.mubashshir.exoplayer_android_mvvm.ui.screens.home.tab_screen.album.AlbumDetailScreen
import com.mubashshir.exoplayer_android_mvvm.ui.screens.home.tab_screen.artists.ArtistDetailScreen
import com.mubashshir.exoplayer_android_mvvm.ui.screens.search.SearchScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MP3.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        // --- Bottom Nav Tabs ---
        composable(Screen.MP3.route) {
            MP3MainScreen(
                onNavigateToSearch = { navController.navigate(Screen.Search.route) },
                onNavigateToArtist = { artistId ->
                    navController.navigate(Screen.ArtistDetail.createRoute(artistId))
                },
                onNavigateToAlbum = { albumId ->
                    navController.navigate(Screen.AlbumDetail.createRoute(albumId))
                },
                onNavigateToFullPlayer = { navController.navigate(Screen.FullPlayer.route) }
            )
        }

        composable(Screen.MP4.route) {
            MP4Screen()
        }

        // --- Detail Screens ---

        composable(Screen.Search.route) {
            SearchScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToFullPlayer = { navController.navigate(Screen.FullPlayer.route) }
            )
        }

        composable(
            route = Screen.ArtistDetail.route,
            arguments = listOf(navArgument("artistId") { type = NavType.StringType })
        ) { backStackEntry ->
            val artistId = backStackEntry.arguments?.getString("artistId") ?: ""
            ArtistDetailScreen(
                artistId = artistId,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.AlbumDetail.route,
            arguments = listOf(navArgument("albumId") { type = NavType.StringType })
        ) { backStackEntry ->
            val albumId = backStackEntry.arguments?.getString("albumId") ?: ""
            AlbumDetailScreen(
                albumId = albumId,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.FullPlayer.route) {
            FullPlayerScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}