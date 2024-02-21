package com.example.todolist_practicapmdm_persistencia.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.example.todolist_practicapmdm_persistencia.MainActivity
import com.example.todolist_practicapmdm_persistencia.R
import com.example.todolist_practicapmdm_persistencia.databinding.FragmentAddTareaBinding
import com.example.todolist_practicapmdm_persistencia.model.Tarea
import com.example.todolist_practicapmdm_persistencia.viewmodel.TareaViewModel


class AddTareaFragment : Fragment(R.layout.fragment_add_tarea), MenuProvider{

    private var addTareaBinding: FragmentAddTareaBinding? = null
    private val binding get() = addTareaBinding!!

    private lateinit var tareasViewModel: TareaViewModel
    private lateinit var addTareaView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addTareaBinding = FragmentAddTareaBinding.inflate(inflater,container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        tareasViewModel = (activity as MainActivity).tareaViewModel
        addTareaView=view
    }

    private fun saveTarea(view: View){
        val nombreAsignatura = binding.addAsignaturaNombre.text.toString().trim()
        val tareaDescripcion = binding.addTareaDescripcion.text.toString().trim()

        if(nombreAsignatura.isNotEmpty()){
            val tarea = Tarea(0, nombreAsignatura, tareaDescripcion)
            tareasViewModel.addNote(tarea)

            Toast.makeText(addTareaView.context, "Nota Guardada", Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.home2, false)
        } else {
            Toast.makeText(addTareaView.context, "Por favor, introduce el nombre de la asignatura", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.add_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.aniadirMenu ->{
                saveTarea(addTareaView)
                true
            }
            else-> false
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        addTareaBinding = null
    }


}