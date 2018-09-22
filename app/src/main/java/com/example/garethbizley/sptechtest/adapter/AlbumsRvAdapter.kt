package com.example.garethbizley.sptechtest.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.garethbizley.sptechtest.R
import com.example.garethbizley.sptechtest.model.Album
import kotlinx.android.synthetic.main.album_display_cell.view.*

/**
 * Created by Gaz Biz on 22/9/18.
 */
class AlbumsRvAdapter(private val albumsList: ArrayList<Album>, private val context: Context) : RecyclerView.Adapter<AlbumViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(LayoutInflater.from(context).inflate(R.layout.album_display_cell, parent, false))
    }

    override fun getItemCount(): Int {
        return albumsList.size
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.albumTitle.text = albumsList[position].title
    }
}

class AlbumViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val albumTitle: TextView = view.album_title_tv
}