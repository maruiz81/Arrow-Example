package com.maruiz.arrowexample.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.maruiz.arrowexample.R
import com.maruiz.arrowexample.presentation.viewmodel.BooksViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getViewModel<BooksViewModel>().let {
            it.observeBooks().observe(this, Observer {
                it.map { it.title }.forEach { Log.d(TAG, it) }

            })
            it.observeFailure().observe(this, Observer {
                Log.d(TAG, "Failure")
            })
            it.requestBooks()
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }

}
