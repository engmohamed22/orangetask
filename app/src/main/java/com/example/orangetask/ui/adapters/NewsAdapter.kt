package com.example.orangetask.ui.adapters

import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.item_article.view.*
import com.example.orangetask.R
import com.example.orangetask.models.Article
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class NewsAdapter :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private val callBack = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            img_progress_bar.isVisible = true
            Glide.with(context)
                .load(article.urlToImage.toString())
                .error(R.drawable.ic_image)
                .listener(object : RequestListener<Drawable?> {

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        img_progress_bar.isGone = true
                        return false
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        img_progress_bar.isGone = true
                       return false
                    }
                })
                .into(article_image)

            article_image.clipToOutline = true
            article_title.text = article.title
            val formattedDate = LocalDateTime
                .parse(article.publishedAt, DateTimeFormatter.ISO_DATE_TIME)
                .format(DateTimeFormatter.ofPattern(context.getString(R.string.data_pattern)))
            article_date.text = formattedDate

            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
            shared_article.setOnClickListener {
                onItemClickListenerShared?.let { it(article) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    private var onItemClickListenerShared: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnItemClickListenerShared(listener: (Article) -> Unit) {
        onItemClickListenerShared = listener
    }
}