package com.application.latticeinnovations.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.application.latticeinnovations.model.remote.Article
import com.application.latticeinnovations.repository.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * View Model Class which acts a bridge between model and ui class
 */
@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepo: NewsRepo) : ViewModel() {

    fun getPageData() = newsRepo.getPageList()

    fun newsDB(query: String): LiveData<List<Article>> {
        return newsRepo.getData(query)
    }


    fun getDbagain()  : LiveData<List<Article>>{
        return newsRepo.getDataAll()
    }

    fun deleteAll(){
        newsRepo.deleteAll()
    }
}