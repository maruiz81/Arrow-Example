package com.maruiz.arrowexample.presentation.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.maruiz.arrowexample.R
import com.maruiz.arrowexample.presentation.adapter.BooksAdapter
import com.maruiz.arrowexample.presentation.viewmodel.BooksViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val adapter: BooksAdapter by inject()

    private val booksViewModel: BooksViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecycler()

        booksViewModel.observeBooks().observe(this, Observer {
            adapter.renderables = it
        })
        booksViewModel.observeFailure().observe(this, Observer {
            Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show()
        })
        booksViewModel.requestBooks()
    }

    private fun setupRecycler() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
