package com.example.getquotes.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getquotes.Models.QuoteList
import com.example.getquotes.Repository.QouteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val qouteRepository: QouteRepository): ViewModel() {


    init {

        viewModelScope.launch(Dispatchers.IO) {
            qouteRepository.getQuotes()
        }
    }
    val quotes: LiveData<QuoteList>
        get() =qouteRepository.quotes
}