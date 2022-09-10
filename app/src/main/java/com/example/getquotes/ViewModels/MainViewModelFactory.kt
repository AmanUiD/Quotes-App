package com.example.getquotes.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.getquotes.Repository.QouteRepository

class MainViewModelFactory(private val qouteRepository: QouteRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(qouteRepository) as T
    }
}