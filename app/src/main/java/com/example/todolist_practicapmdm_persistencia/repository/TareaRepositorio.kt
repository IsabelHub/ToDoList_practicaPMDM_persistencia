package com.example.todolist_practicapmdm_persistencia.repository

import com.example.todolist_practicapmdm_persistencia.database.TareaDatabase
import com.example.todolist_practicapmdm_persistencia.model.Tarea

class TareaRepositorio (private val db: TareaDatabase) {
    suspend fun insertTarea(tarea: Tarea) = db.getTareaDao().insertTarea(tarea)
    suspend fun updateTarea(tarea: Tarea) = db.getTareaDao().updateTarea(tarea)
    suspend fun deleteTarea(tarea: Tarea) = db.getTareaDao().deleteTarea(tarea)

    fun getAllTareas() = db.getTareaDao().getAllNotes()
    fun searchTareas(query: String?) = db.getTareaDao().searchTarea(query)
}