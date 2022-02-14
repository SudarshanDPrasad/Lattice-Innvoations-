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
import com.application.latticeinnovations.extra.Status
import com.application.latticeinnovations.model.remote.Article
import com.application.latticeinnovations.view.adaptor.NewsAdapter
import com.application.latticeinnovations.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsMainFragment : Fragment() {

    lateinit var newsMainBinding: FragmentNewsMainBinding
    private val newsViewModel: NewsViewModel by viewModels()
    private var list = emptyList<Article>()
    private val delay = 5000
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
        loadApi()
        searchload()
    }

    private fun searchload() {
        newsMainBinding.etToSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    val handler = Handler()
                    handler.postDelayed(Runnable {
                        kotlin.run {
                            loadSearchData(newsMainBinding.etToSearch.text.toString())
                        }
                    }, 5000)
                } else {
                    loadApi()
                    val handler = Handler()
                    handler.postDelayed(Runnable {
                        loadApi()
                        Toast.makeText(context, "Refreshed", Toast.LENGTH_SHORT).show()
                    }.also { runnable = it }, delay.toLong())
                }
            }
        })
    }

    private fun loadSearchData(query: String) {
        newsViewModel.getSearchData(query).observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    list = it.data!!.articles
                    var adapter = NewsAdapter(list)
                    var layoutManager = LinearLayoutManager(context)
                    newsMainBinding.rvMainNews.adapter = adapter
                    newsMainBinding.rvMainNews.layoutManager = layoutManager
                }
                Status.ERROR -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun loadApi() {
        newsViewModel.getData().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    list = it.data!!.articles
                    var adapter = NewsAdapter(list)
                    var layoutManager = LinearLayoutManager(context)
                    newsMainBinding.rvMainNews.adapter = adapter
                    newsMainBinding.rvMainNews.layoutManager = layoutManager
                }
                Status.ERROR -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}