package com.example.garethbizley.sptechtest.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Gaz Biz on 22/9/18.
 */
data class Album (
        @SerializedName("id")
        val id: Int,
        @SerializedName("userId")
        val userId: Int,
        @SerializedName("title")
        val title: String = "")