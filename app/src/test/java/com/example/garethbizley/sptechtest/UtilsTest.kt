package com.example.garethbizley.sptechtest

import com.example.garethbizley.sptechtest.model.Album
import com.example.garethbizley.sptechtest.util.Utils
import org.junit.Test

/**
 * Created by Gaz Biz on 1/11/18.
 */
class UtilsTest {

    @Test
    fun sortAlbumListTest(){

        val testAlbum1 = Album(1, 1, "AAAA")
        val testAlbum2 = Album(2, 2, "BBBB")
        val testAlbum3 = Album(3, 3, "CCCC")
        val testList = arrayListOf(testAlbum2, testAlbum3, testAlbum1)

        Utils.sortAlbumsListByTitle(testList)

        assert(testList[0] == testAlbum1)
        assert(testList[2] == testAlbum3)
    }
}