package com.example.todolist_practicapmdm_persistencia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist_practicapmdm_persistencia.model.Tarea
import com.example.todolist_practicapmdm_persistencia.repository.TareaRepositorio
import kotlinx.coroutines.launch

class TareaViewModel(app: Application, private val tareaRepository: TareaRepositorio): AndroidViewModel(app) {
    fun addTarea(tarea: Tarea) =
        viewModelScope.launch {
            tareaRepository.insertTarea(tarea)
        }

    fun deleteTarea(tarea: Tarea) =
        viewModelScope.launch {
            tareaRepository.deleteTarea(tarea)
        }

    fun updateTarea(tarea: Tarea) =
        viewModelScope.launch {
            tareaRepository.updateTarea(tarea)
        }

    fun getAllTareas() =tareaRepository.getAllTareas()

    fun searchTarea(query: String?) = tareaRepository.searchTareas(query)
}