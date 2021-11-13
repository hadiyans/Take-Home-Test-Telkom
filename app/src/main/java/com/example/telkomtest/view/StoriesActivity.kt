package com.example.telkomtest.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.telkomtest.adapter.StoriesAdapter
import com.example.telkomtest.modelFactory.StoriesModelFactory
import com.example.telkomtest.R
import com.example.telkomtest.repository.StoriesRepository
import com.example.telkomtest.RetrofitService
import com.example.telkomtest.viewModel.StoriesViewModel
import com.example.telkomtest.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.GridLayoutManager
import com.example.telkomtest.listener.RecyclerViewClickListener


class StoriesActivity {
    class MainActivity : AppCompatActivity() {
        private val TAG = "MainActivity"
        private val retrofitService = RetrofitService.getInstance()
        val adapter = StoriesAdapter()
        private lateinit var binding: ActivityMainBinding
        private lateinit var viewModel: StoriesViewModel

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            viewModel = ViewModelProvider(this, StoriesModelFactory(StoriesRepository(retrofitService))).get(StoriesViewModel::class.java)

            binding.listStories.adapter = adapter
            listStories.setLayoutManager(GridLayoutManager(this, 2))

            viewModel.storiesList.observe(this, Observer {
                Log.d(TAG, "onCreate: $it")
                adapter.setStoryList(it)
            })

            viewModel.errorMessage.observe(this, Observer {

            })

            viewModel.getAllStories()

        }
    }
}