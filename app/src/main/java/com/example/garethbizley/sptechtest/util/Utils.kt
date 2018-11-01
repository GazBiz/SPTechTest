package com.example.garethbizley.sptechtest.util

import com.example.garethbizley.sptechtest.model.Album

/**
 * Created by Gaz Biz on 1/11/18.
 */
class Utils {

    companion object {
        //todo move to BG thread for sorting operation
        fun sortAlbumsListByTitle(albumList: ArrayList<Album>){
            val sortedArrayList = ArrayList(albumList.sortedWith(compareBy{ it.title }))
            albumList.clear()
            albumList.addAll(sortedArrayList)
        }
    }
}