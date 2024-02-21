package com.example.todolist_practicapmdm_persistencia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist_practicapmdm_persistencia.model.Tarea
import com.example.todolist_practicapmdm_persistencia.repository.TareaRepositorio
import kotlinx.coroutines.launch

class TareaViewModel(app: Application, private val noteRepository: TareaRepositorio): AndroidViewModel(app) {
    fun addNote(tarea: Tarea) =
        viewModelScope.launch {
            noteRepository.insertTarea(tarea)
        }

    fun deleteNote(tarea: Tarea) =
        viewModelScope.launch {
            noteRepository.deleteTarea(tarea)
        }

    fun updateNote(tarea: Tarea) =
        viewModelScope.launch {
            noteRepository.updateTarea(tarea)
        }

    fun getAllNotes() =noteRepository.getAllTareas()

    fun searchNote(query: String?) = noteRepository.searchTareas(query)
}