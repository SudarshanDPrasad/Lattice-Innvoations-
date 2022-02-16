package com.application.latticeinnovations.view.adaptor

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.latticeinnovations.R
import com.application.latticeinnovations.databinding.ItemLayoutBinding
import com.application.latticeinnovations.model.remote.Article
import com.bumptech.glide.Glide
import java.time.LocalDate
import java.time.LocalTime


/**
 * Adapter class to fill the search view
 */
class NewsAdapter(
    val list: List<Article>,

    ): RecyclerView.Adapter<NewsHolderData>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolderData {
        val itemLayoutBinding: ItemLayoutBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_layout, parent, false)

        return NewsHolderData(itemLayoutBinding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NewsHolderData, position: Int) {
        val newsList = list[position]
        holder.setData(newsList)
        holder.itemLayoutBinding.tvNewsIndexNo.text = "${position.toString()})"
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class NewsHolderData(
    val itemLayoutBinding: ItemLayoutBinding
) : RecyclerView.ViewHolder(itemLayoutBinding.root) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun setData(article: Article) {
        Glide.with(itemLayoutBinding.ivNewsImage).load(article.urlToImage)
            .into(itemLayoutBinding.ivNewsImage)
        itemLayoutBinding.newsData = article

        /**
         * To Split the Given Time in News Api
         */
        val articleDate = article.publishedAt.split("T")
        val test = articleDate[0]
        val dateSplit1 = test.split("-") // date
        val articleTime = dateSplit1[1].split("Z")
        val test1 = articleTime[0].toString()
        val timeHour = test1.split(":")


        /**
         * To split the system Date
         */
        val systemDate  = LocalDate.now().toString()
        val currentDate = systemDate .split("-")
        val systemTime = LocalTime.now().toString()
        val currentTIme = systemTime.split(":")


        /**
         * To show the time how long the post updated
         */
        if (currentDate[2] == dateSplit1[2]) {
            if (timeHour.toString() < currentTIme.toString()) {
                itemLayoutBinding.tvNewsTime.text = "${currentTIme[0].toInt() - timeHour[0].toInt()} hours ago "
            }else{
                itemLayoutBinding.tvNewsTime.text = "${timeHour[0].toInt() - currentTIme[0].toInt()} hours ago "
            }

        } else {
            itemLayoutBinding.tvNewsTime.text =
                "${currentDate[2].toInt() - dateSplit1[2].toInt()} day ago "
        }

    }
}
