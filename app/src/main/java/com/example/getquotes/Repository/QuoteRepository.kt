package com.example.getquotes.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.getquotes.Api.QuoteServices
import com.example.getquotes.Models.QuoteList

class QouteRepository(private val quoteService: QuoteServices) {
    private var quotesLiveData = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList>
        get() = quotesLiveData

    suspend fun getQuotes() {
        val result = quoteService.getQuotes("xy")
        if (result?.body() != null) {
            quotesLiveData.postValue(result.body())
        }
    }


}