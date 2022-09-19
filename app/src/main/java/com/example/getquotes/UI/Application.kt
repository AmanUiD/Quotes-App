package com.example.getquotes.UI

import android.app.Application
import com.example.getquotes.Api.QuoteServices
import com.example.getquotes.Api.RetrofitHelper

import com.example.getquotes.Repository.QouteRepository

class QuoteApplication : Application() {

    lateinit var qouteRepository: QouteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteServices::class.java)
        qouteRepository = QouteRepository(quoteService)
    }
}