package com.example.watchnowanime.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.watchnowanime.R
import com.example.watchnowanime.interfaces.RecyclerViewInterface
import com.example.watchnowanime.model.Episodes

class DetailAdapter(context: Context, val recyclerViewInterface: RecyclerViewInterface) :
    RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {


    private val mContext = context


    inner class DetailViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var title: TextView = itemView.findViewById(R.id.ep_title_tv)
        var titleImg: ImageView = itemView.findViewById(R.id.episode_img)

    }

    private val differCallback = object : DiffUtil.ItemCallback<Episodes>(){
        override fun areItemsTheSame(oldItem: Episodes, newItem: Episodes): Boolean {
            return oldItem.episodeNum == newItem.episodeNum
        }

        override fun areContentsTheSame(oldItem: Episodes, newItem: Episodes): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.episode_item,
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val episode = differ.currentList[position]
        holder.title.text = episode.episodeId
       Glide.with(mContext).asBitmap().load(episode.episodeUrl)
            .apply(RequestOptions().override(1000,250))
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.titleImg)
        holder.itemView.setOnClickListener {
            recyclerViewInterface.onItemClick(episode.episodeUrl)
        }
    }
}