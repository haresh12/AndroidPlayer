package com.example.androidplayer

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_gif_list.view.*

  const val VIDEO_URL = ""

class GifListAdapter(val gifList: GifModel) : RecyclerView.Adapter<GifListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_gif_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return gifList.data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(gifList.data[position])
        holder.itemView.imgThumb.visibility = View.VISIBLE
        holder.itemView.imgThumb.apply {
            setOnClickListener {
                context.startActivity(Intent(context, PlayerActivity::class.java).putExtra(
                    VIDEO_URL,gifList.data[position].images.originalMp4.mp4
                ))
            }
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(data: Data) {
            Picasso.with(itemView.imgView.context).load(data.images.wStill.url).into(itemView.imgView)

        }
    }

}