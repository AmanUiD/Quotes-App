package com.example.getquotes.Api

import com.example.getquotes.Models.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteServices {
    @GET("/quotes")
    suspend fun getQuotes(@Query("result") query: String): Response<QuoteList>
}