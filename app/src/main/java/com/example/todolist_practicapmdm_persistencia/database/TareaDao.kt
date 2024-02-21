package com.example.todolist_practicapmdm_persistencia.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todolist_practicapmdm_persistencia.model.Tarea

@Dao
interface TareaDao {
    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun insertTarea(tarea: Tarea)
    @Update
    suspend fun updateTarea(tarea: Tarea)

    @Delete
    suspend fun deleteTarea(tarea: Tarea)

    @Query("SELECT * FROM TAREAS ORDER BY id DESC")
    fun getAllTareas(): LiveData<List<Tarea>>

    @Query("SELECT * FROM TAREAS WHERE nombreAsignatura LIKE :query OR tareaDescripcion LIKE :query")
    fun searchTarea(query: String?): LiveData<List<Tarea>>

}