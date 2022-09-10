package com.example.getquotes.Models

data class Result(
//    @PrimaryKey(autoGenerate = true)
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
    val tags: List<String>
)