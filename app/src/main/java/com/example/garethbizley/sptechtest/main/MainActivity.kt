package com.example.garethbizley.sptechtest.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import android.arch.lifecycle.ViewModelProviders

import com.example.garethbizley.sptechtest.AlbumsApplication
import com.example.garethbizley.sptechtest.R
import com.example.garethbizley.sptechtest.adapter.AlbumsRvAdapter
import com.example.garethbizley.sptechtest.viewmodel.AlbumViewModel
import com.example.garethbizley.sptechtest.viewmodel.IAlbumViewModel
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //region Instantiate Class Variables
    private val TAG = MainActivity::class.java.name
    @Inject
    lateinit var viewModel: IAlbumViewModel
    //endregion

    //region Activity Functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as AlbumsApplication).appComponent.inject(this)

        viewModel = ViewModelProviders.of(this).get(AlbumViewModel::class.java)

        setupRecyclerView()
        //show getAlbum button or not depending on if list is populated
        get_albums_btn.visibility = if (viewModel.albumsList.isEmpty()) View.VISIBLE else View.GONE

        get_albums_btn.setOnClickListener {
            viewModel.requestAlbums()
                    .doOnSuccess { onAlbumsReturned() }
                    .doOnError { showError(it.localizedMessage) }
                    .subscribe()

            get_albums_btn.visibility = View.GONE
            loading_display_ll.visibility = View.VISIBLE
        }
    }
    //endregion

    private fun setupRecyclerView() {
        albums_display_rv.layoutManager = LinearLayoutManager(this)
        albums_display_rv.adapter = AlbumsRvAdapter(viewModel.albumsList, this)
        val dividerDecoration = DividerItemDecoration(albums_display_rv.context,
                LinearLayout.VERTICAL)
        albums_display_rv.addItemDecoration(dividerDecoration)
    }

    private fun onAlbumsReturned() {
        loading_display_ll.visibility = View.GONE
        albums_display_rv.visibility = View.VISIBLE
        albums_display_rv.adapter?.notifyDataSetChanged()

        if(albums_display_rv.adapter?.itemCount == 0)
            showError("No Albums were returned")
    }

    private fun showError(message: String) {
        //log error for debugging
        Log.e(TAG, "Error getting albums: $message")
        //inform user of error
        Toast.makeText(this, "Something went wrong! Try again?", Toast.LENGTH_SHORT).show()
        //allow another request attempt
        get_albums_btn.visibility = View.VISIBLE
        loading_display_ll.visibility = View.GONE
    }
}
