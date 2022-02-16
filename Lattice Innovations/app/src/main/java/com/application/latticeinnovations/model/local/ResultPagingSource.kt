package com.application.latticeinnovations.model.local

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.application.latticeinnovations.di.NewsModule
import com.application.latticeinnovations.model.remote.Article
import com.application.latticeinnovations.model.remote.ResponseDTO
import javax.inject.Inject

/**
 * Paging class For the Initial One to load all the Pages Present in the Api
 */
class ResultPagingSource @Inject constructor(val newsDao: NewsDao) : PagingSource<Int, Article>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val pageNumber = params.key ?: 1
        val responseDto: ResponseDTO = NewsModule.ProvidesApiService().getData(pageNumber)
        val result: List<Article> = responseDto.articles

        newsDao.addData(result)

        return try {
            LoadResult.Page(
                data = result,
                prevKey = null,
                nextKey = if (result.isEmpty()) null else pageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

}