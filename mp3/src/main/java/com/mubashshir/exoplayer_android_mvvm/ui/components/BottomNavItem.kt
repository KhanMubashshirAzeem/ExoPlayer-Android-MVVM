package com.mubashshir.exoplayer_android_mvvm.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.OndemandVideo
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)
{
    object MP3 : BottomNavItem(
        "mp3",
        Icons.Default.MusicNote,
        "mp3"
    )

    object MP4 : BottomNavItem(
        "mp4",
        Icons.Default.OndemandVideo,
        "mp4"
    )
}