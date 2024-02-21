package com.example.todolist_practicapmdm_persistencia.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist_practicapmdm_persistencia.repository.TareaRepositorio

class TareaViewModelFactory (val app: Application, private val noteRepository: TareaRepositorio) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TareaViewModel(app, noteRepository) as T
    }



}