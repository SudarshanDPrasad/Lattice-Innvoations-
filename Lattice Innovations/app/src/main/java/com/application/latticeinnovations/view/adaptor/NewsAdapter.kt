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
import java.time.LocalTime

class NewsAdapter(
    private val list: List<Article>
) : RecyclerView.Adapter<NewsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val itemLayoutBinding : ItemLayoutBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_layout,parent,false)
        return NewsHolder(itemLayoutBinding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        var listOfNews = list[position]
        holder.setData(listOfNews)
        holder.itemLayoutBinding.tvNewsIndexNo.text = "${position.toString()})"
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
class NewsHolder(
    var itemLayoutBinding: ItemLayoutBinding,
) : RecyclerView.ViewHolder(itemLayoutBinding.root) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun setData(article: Article) {
        Glide.with(itemLayoutBinding.ivNewsImage).load(article.urlToImage)
            .into(itemLayoutBinding.ivNewsImage)
        itemLayoutBinding.tvAuthorName.text = article.source.name
        itemLayoutBinding.tvTitleName.text = article.title
        itemLayoutBinding.tvDescription.text = article.description


        val sdf = LocalTime.now()
        val currentDate = sdf

//        var timeCalculate = "${currentDate.toInt() - spp[0].toInt()}"

        itemLayoutBinding.tvNewsTime.text = article.publishedAt
    }


}
