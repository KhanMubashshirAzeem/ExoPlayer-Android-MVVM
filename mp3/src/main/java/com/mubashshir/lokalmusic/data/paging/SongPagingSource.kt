package com.mubashshir.lokalmusic.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mubashshir.lokalmusic.data.model.Results
import com.mubashshir.lokalmusic.data.remote.SongApiService

/**
 * A PagingSource implementation that loads pages of songs from the network.
 * This class is responsible for fetching paginated data from the SongApiService
 * based on a search query.
 *
 * @param songApiService The Retrofit service interface for making network requests.
 * @param query The search term used to find songs.
 * @param name An optional filter, possibly for artist or album name.
 */
class SongPagingSource(
    private val songApiService: SongApiService,
    private val query: String,
    private val name: String? = null
) : PagingSource<Int, Results>() // PagingSource<Key, Value> where Key is Int (page number) and Value is Results (the song data model).
{

    /**
     * This function is called by the Paging library to asynchronously fetch more data
     * to be displayed. This is the core method of a PagingSource.
     *
     * @param params Contains information about the page to load, including the key (page number)
     * and the requested load size.
     * @return A LoadResult which can be a Page of data, an Error, or Invalid if the data is no longer valid.
     */
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Results>
    {
        return try
        {
            // Determine the current page number to fetch. If params.key is null, it's the initial load, so we start with page 1.
            val currentPage = params.key ?: 1

            // Make the network call using the provided ApiService.
            // We pass the search query, optional name, the current page, and the requested number of items (loadSize).
            val response = songApiService.searchSongs(
                query = query,
                name = name,
                page = currentPage,
                limit = params.loadSize
            )

            // Extract the list of songs from the API response body.
            val songs = response.data.results

            // Create a LoadResult.Page object, which represents a successful data load.
            LoadResult.Page(
                data = songs, // The list of items loaded for the current page.
                prevKey = if (currentPage == 1) null else currentPage - 1, // Key for the previous page. Null if this is the first page.
                nextKey = if (songs.isEmpty()) null else currentPage + 1 // Key for the next page. Null if we've reached the end of the data.
            )
        } catch (e: Exception)
        {
            // If an exception occurs (e.g., network error), return a LoadResult.Error.
            // The Paging library will be notified of the failure.
            LoadResult.Error(e)
        }
    }

    /**
     * This function provides the key to be used for the initial load of a new PagingData.
     * It's called when the data is refreshed or invalidated.
     * The goal is to load data around the user's last scroll position.
     *
     * @param state The current state of the paging data, including loaded pages and anchor position.
     * @return The key (page number) to be passed to the load() function for the refresh.
     */
    override fun getRefreshKey(
        state: PagingState<Int, Results>
    ): Int?
    {
        // Find the anchor position, which is the most recently accessed index in the list.
        return state.anchorPosition?.let { position ->
            // Find the page that contains the anchor position.
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}
