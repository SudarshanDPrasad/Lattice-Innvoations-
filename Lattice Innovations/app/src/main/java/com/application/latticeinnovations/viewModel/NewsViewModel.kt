package com.application.latticeinnovations.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.application.latticeinnovations.model.remote.ResponseDTO
import com.application.latticeinnovations.repository.NewsRepo
import com.application.latticeinnovations.extra.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers

import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(val newsRepo: NewsRepo) : ViewModel() {

    fun getData(): LiveData<Resource<ResponseDTO>> {
        return liveData(Dispatchers.IO) {
            val data = newsRepo.getNews()
            emit(data)
        }
    }

    fun getSearchData(query:String ) : LiveData<Resource<ResponseDTO>>{
        return liveData(Dispatchers.IO) {
            val data = newsRepo.getSearchData(query)
            emit(data)
        }
    }
}