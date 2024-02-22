package com.example.todolist_practicapmdm_persistencia.fragments

import android.app.AlertDialog
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
import androidx.navigation.fragment.navArgs
import com.example.todolist_practicapmdm_persistencia.MainActivity
import com.example.todolist_practicapmdm_persistencia.R
import com.example.todolist_practicapmdm_persistencia.databinding.FragmentEditTareaBinding
import com.example.todolist_practicapmdm_persistencia.model.Tarea
import com.example.todolist_practicapmdm_persistencia.viewmodel.TareaViewModel


class EditTareaFragment : Fragment(R.layout.fragment_edit_tarea),MenuProvider {

    private var editTareaBinding: FragmentEditTareaBinding? = null
    private val binding get() = editTareaBinding!!

    private lateinit var tareasViewModel: TareaViewModel
    private lateinit var tareaActual: Tarea

    private val args: EditTareaFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    editTareaBinding = FragmentEditTareaBinding.inflate(inflater,container,false)
    return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        tareasViewModel = (activity as MainActivity).tareaViewModel
        tareaActual = args.tarea!!

        binding.editTareaTitle.setText(tareaActual.nombreAsignatura)
        binding.editTareaDesc.setText(tareaActual.tareaDescripcion)

        binding.editTareaFab.setOnClickListener {
            val noteTitle = binding.editTareaTitle.text.toString().trim()
            val noteDesc = binding.editTareaDesc.text.toString().trim()

            if(noteTitle.isNotEmpty()){
                val tarea = Tarea(tareaActual.id, noteTitle, noteDesc)
                tareasViewModel.updateTarea(tarea)
                view.findNavController().popBackStack(R.id.home2, false)

            }else{
                Toast.makeText(context, "Por favor, Introduce el nombre de la asignatura", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun deleteTarea(){
        AlertDialog.Builder(activity).apply {
            setTitle("Borrar tarea")
            setMessage("¿Quieres borrar esta tarea?")
            setPositiveButton("Borrar"){_,_ ->
                tareasViewModel.deleteTarea(tareaActual)
                Toast.makeText(context, "Tarea eliminada", Toast.LENGTH_SHORT).show()
                view?.findNavController()?.popBackStack(R.id.home2, false)
            }
            setNegativeButton("Cancelar", null)
        }.create().show()
    }
    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.deleteMenu -> {
                deleteTarea()
                true
            } else -> false
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        editTareaBinding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.editar_menu, menu)
    }



}