package com.example.telkomtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.telkomtest.databinding.AdapterStoriesBinding
import com.example.telkomtest.listener.RecyclerViewClickListener
import com.example.telkomtest.model.Stories

class StoriesAdapter: RecyclerView.Adapter<StoriesMainHolder>() {
    var stories = mutableListOf<Stories>()
    var listener: RecyclerViewClickListener ?= null

    fun setStoryList(stories: List<Stories>){
        this.stories = stories.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesMainHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterStoriesBinding.inflate(inflater, parent, false)
        return StoriesMainHolder(binding)
    }

    override fun onBindViewHolder(holder: StoriesMainHolder, position: Int) {
        val story = stories[position]
        holder.binding.title.text = story.title
        holder.binding.title.setOnClickListener(View.OnClickListener {
            listener?.onItemClicked(it,story)
        })
    }

    override fun getItemCount(): Int {
        return stories.size
    }

}

class StoriesMainHolder(val binding: AdapterStoriesBinding): RecyclerView.ViewHolder(binding.root) {

}
