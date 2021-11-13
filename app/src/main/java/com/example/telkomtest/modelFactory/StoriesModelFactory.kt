package com.example.telkomtest.modelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.telkomtest.repository.StoriesRepository
import com.example.telkomtest.viewModel.StoriesViewModel

class StoriesModelFactory constructor(private val repository: StoriesRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(StoriesViewModel::class.java)) {
            StoriesViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}