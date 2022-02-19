package com.application.latticeinnovations.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.application.latticeinnovations.model.local.NewsDao
import com.application.latticeinnovations.model.local.ResultPagingSource
import com.application.latticeinnovations.model.remote.ApiClient
import com.application.latticeinnovations.model.remote.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Repository class to load from the Paging source class which data Present in the Api class
 */
class NewsRepo @Inject constructor(val apiClient: ApiClient, val newsDao: NewsDao) {

    /**
     * Pagination Method to set the list per page and max CardView to refresh
     */
    fun getPageList() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = { ResultPagingSource(newsDao) }
        ).liveData

    fun getData(query: String): LiveData<List<Article>> {
        return newsDao.getDb(query)
    }

    fun getDataAll(): LiveData<List<Article>> {
        return newsDao.getDbAgain()
    }

    fun deleteAll() {
        CoroutineScope(Dispatchers.IO).launch {
            newsDao.deleteAll()
        }
    }
}