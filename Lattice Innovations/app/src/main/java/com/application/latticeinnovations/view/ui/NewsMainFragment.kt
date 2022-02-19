package com.application.latticeinnovations.view.ui

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.latticeinnovations.R
import com.application.latticeinnovations.databinding.FragmentNewsMainBinding
import com.application.latticeinnovations.model.remote.Article
import com.application.latticeinnovations.view.adaptor.NewsPageAdapter
import com.application.latticeinnovations.view.adaptor.NewsAdapter
import com.application.latticeinnovations.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Fragment class to load the result and do all the major operations where the user will be able to see
 */
@AndroidEntryPoint
class NewsMainFragment : Fragment() {

    lateinit var newsMainBinding: FragmentNewsMainBinding
    private val newsViewModel: NewsViewModel by viewModels()
    private val delay = 1000
    private var newsList = mutableListOf<Article>()
    lateinit var newsPageAdapter: NewsPageAdapter
    private var runnable: Runnable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        newsMainBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news_main, container, false)
        return newsMainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        newsPageAdapter = NewsPageAdapter()
        setAdaptor()
        loadPageData()
        searchload()

    }

    /**
     * Method to load the page once the app is Launch
     */
    private fun loadPageData() {
        newsViewModel.deleteAll()
        newsViewModel.getPageData().observe(this, {

            it?.let { it ->
                CoroutineScope(Dispatchers.Main).launch {
                    newsPageAdapter.submitData(it)
                }
            }
        })
    }

    /**
     * Method to load the data once the user opt to search
     */
    private fun searchload() {
        newsMainBinding.etToSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (!p0.toString().isEmpty()) {
                    loadSearchData(newsMainBinding.etToSearch.text.toString())
                } else {
                    newsViewModel.getDbagain().observe(viewLifecycleOwner,{
                        newsList.clear()
                        newsList.addAll(it)
                        val adaptor = NewsAdapter(newsList)
                        val linearLayoutManager = LinearLayoutManager(context)
                        newsMainBinding.rvMainNews.apply {
                            adapter = adaptor
                            layoutManager = linearLayoutManager
                        }
                    })
                }
            }
        })
    }

    /**
     * Method to load the data once the user finished giving a keyword to search
     */
    private fun loadSearchData(query: String) {

        newsViewModel.newsDB(query).observe(this, {
            newsList.clear()
            newsList.addAll(it)
            newsList.reverse()
            val adaptor = NewsAdapter(newsList)
            val linearLayoutManager = LinearLayoutManager(context)
            newsMainBinding.rvMainNews.apply {
                adapter = adaptor
                layoutManager = linearLayoutManager
            }
        })
    }

    /**
     * Setting adaptor class how the user can see
     */
    private fun setAdaptor() {
        val linearLayoutManager = LinearLayoutManager(context)
        newsMainBinding.rvMainNews.apply {
            adapter = newsPageAdapter
            layoutManager = linearLayoutManager
        }
    }
}
