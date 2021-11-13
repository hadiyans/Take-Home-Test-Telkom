package com.example.telkomtest.listener

import android.view.View
import com.example.telkomtest.model.Stories

interface RecyclerViewClickListener {
    fun onItemClicked(view: View, story: Stories)
}