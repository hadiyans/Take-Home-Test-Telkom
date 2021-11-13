package com.example.telkomtest.repository

import com.example.telkomtest.RetrofitService

class StoriesRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllStories() = retrofitService.getAllStories()
    fun getStoryById(storyId: Int) = retrofitService.getStoryById(storyId)
}