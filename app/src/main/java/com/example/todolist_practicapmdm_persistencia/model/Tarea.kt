package com.example.todolist_practicapmdm_persistencia.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tareas")
@Parcelize
data class Tarea (

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nombreAsignatura: String,
    val tareaDescripcion: String
): Parcelable
