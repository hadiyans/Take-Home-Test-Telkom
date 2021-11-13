package com.example.telkomtest.model


data class Stories(
    val id: Int,
    val by: String,
    val descendants: Int,
    val kids: List<Int>,
    val time: Long,
    val title: String,
    val url: String,
)
