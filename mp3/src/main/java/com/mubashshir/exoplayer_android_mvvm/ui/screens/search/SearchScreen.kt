package com.mubashshir.exoplayer_android_mvvm.ui.screens.search

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mubashshir.exoplayer_android_mvvm.data.model.Results
import com.mubashshir.exoplayer_android_mvvm.ui.components.SongList
import com.mubashshir.exoplayer_android_mvvm.ui.theme.PaddingMedium
import com.mubashshir.exoplayer_android_mvvm.util.UiState

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onNavigateBack: () -> Unit,
    onNavigateToFullPlayer: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentSongId by viewModel.currentSongId.collectAsState()
    val isPlaying by viewModel.isPlaying.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingMedium)
    ) {

        // 🔹 Back Button + Search Bar Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onNavigateBack
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }

            SearchBar(
                query = viewModel.searchQuery,
                onQueryChange = { viewModel.search(it) },
                onClear = { viewModel.clearSearch() },
                modifier = Modifier
                    .weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(PaddingMedium))

        when (uiState)
        {

            is UiState.Loading ->
            {
                Text(
                    "Searching...",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            is UiState.Success ->
            {
                SongList(
                    songs = (uiState as UiState.Success<List<Results>>).data,
                    currentSongId = currentSongId,
                    isPlaying = isPlaying,
                    onSongClick = { song ->
                        viewModel.playSong(song)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            is UiState.Error   ->
            {
                Text(
                    "Error: ${(uiState as UiState.Error).message}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}



