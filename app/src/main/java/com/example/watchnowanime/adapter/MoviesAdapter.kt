package com.example.watchnowanime.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.Target
import com.example.watchnowanime.model.AnimeItem
import com.example.watchnowanime.R

class MoviesAdapter(context: Context) : RecyclerView.Adapter<MoviesAdapter.AnimeViewHolder>() {

    private val mContext = context

    inner class AnimeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var animeImg : ImageView = itemView.findViewById(R.id.anime_image)
        var title : TextView = itemView.findViewById(R.id.title_tv)

    }

    private val differCallback = object : DiffUtil.ItemCallback<AnimeItem>(){

        override fun areItemsTheSame(oldItem: AnimeItem, newItem: AnimeItem): Boolean {
           return oldItem.animeId == newItem.animeId
        }

        override fun areContentsTheSame(oldItem: AnimeItem, newItem: AnimeItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        return AnimeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.anime_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = differ.currentList[position]
        holder.apply {
            Glide.with(mContext).asBitmap().load(anime.animeImg)
                .apply(RequestOptions().override(1000,250))
                .into(animeImg)
            title.text = anime.animeTitle
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}