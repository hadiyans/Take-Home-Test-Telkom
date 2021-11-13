package com.example.telkomtest.viewModel

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.telkomtest.model.Stories
import com.example.telkomtest.repository.StoriesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StoriesViewModel constructor(private val storyRepository: StoriesRepository) : ViewModel(){

    val storiesList = MutableLiveData<MutableList<Stories>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllStories(){
        val response = storyRepository.getAllStories()
        val that = this
        response.enqueue(object : Callback<List<Int>> {
            override fun onResponse(call: Call<List<Int>>, response: Response<List<Int>>) {
                response.body()?.forEach(){
                    that.getStoryById(it)
                }
            }

            override fun onFailure(call: Call<List<Int>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getStoryById(storyId: Int) {
        val response = storyRepository.getStoryById(storyId);
        response.enqueue(object : Callback<Stories> {
            override fun onResponse(call: Call<Stories>, response: Response<Stories>) {
                var currentStoryList = storiesList.value
                if (currentStoryList == null) {
                    currentStoryList = mutableListOf<Stories>()
                }
                val stories: Stories? = response.body()
                if (stories != null) {
                    currentStoryList?.add(stories)
                }
                storiesList.postValue(currentStoryList)
            }

            override fun onFailure(call: Call<Stories>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun setProgressBar(){

    }

    fun onItemClicked(context: Context){
        Toast.makeText(context,"test",Toast.LENGTH_SHORT).show()
    }
}