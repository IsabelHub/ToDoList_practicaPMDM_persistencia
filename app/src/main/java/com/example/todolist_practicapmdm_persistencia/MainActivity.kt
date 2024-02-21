package com.example.todolist_practicapmdm_persistencia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.todolist_practicapmdm_persistencia.database.TareaDatabase
import com.example.todolist_practicapmdm_persistencia.repository.TareaRepositorio
import com.example.todolist_practicapmdm_persistencia.viewmodel.TareaViewModel
import com.example.todolist_practicapmdm_persistencia.viewmodel.TareaViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var tareaViewModel: TareaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
    }
    private fun setupViewModel(){
        val noteRepository = TareaRepositorio(TareaDatabase(this))
        val viewModelProviderFactory = TareaViewModelFactory(application , noteRepository)
       tareaViewModel = ViewModelProvider(this, viewModelProviderFactory)[TareaViewModel::class.java]
    }
}