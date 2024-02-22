package com.example.todolist_practicapmdm_persistencia.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.appcompat.app.AppCompatDelegate
import com.example.todolist_practicapmdm_persistencia.MainActivity
import com.example.todolist_practicapmdm_persistencia.R
import com.example.todolist_practicapmdm_persistencia.adapter.TareaAdapter
import com.example.todolist_practicapmdm_persistencia.databinding.FragmentHomeBinding
import com.example.todolist_practicapmdm_persistencia.model.Tarea
import com.example.todolist_practicapmdm_persistencia.viewmodel.TareaViewModel
import com.google.android.material.switchmaterial.SwitchMaterial


class Home : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener, MenuProvider {


    private var homeBinding : FragmentHomeBinding? = null
    private val binding get() = homeBinding!!

    private lateinit var tareasViewModel : TareaViewModel
    private lateinit var tareaAdapter: TareaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        tareasViewModel = (activity as MainActivity).tareaViewModel
        setupHomeRecyclerView()

        binding.addTareaFab.setOnClickListener{
            it.findNavController().navigate(R.id.action_home2_to_addNoteFragment)
        }

        val modoNoche = binding.root.findViewById<SwitchMaterial>(R.id.swDarkMode)
        modoNoche.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Habilitar el modo oscuro
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                modoNoche.setText("Modo Claro")
            } else {
                // Habilitar el modo claro
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                modoNoche.setText("Modo Oscuro")
            }
        }
    }



    private fun updateUI(tarea: List<Tarea>?){
        if (tarea != null){
            if (tarea.isNotEmpty()){
                binding.emptyTareaImage.visibility = View.GONE
                binding.homeRecyclerView.visibility = View.VISIBLE
            }else{
                binding.emptyTareaImage.visibility = View.VISIBLE
                binding.homeRecyclerView.visibility = View.GONE
            }
        }
    }
    private fun setupHomeRecyclerView(){
        tareaAdapter = TareaAdapter()
        binding.homeRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = tareaAdapter
        }

        activity?.let {
            tareasViewModel.getAllTareas().observe(viewLifecycleOwner){ tarea ->
                tareaAdapter. differ.submitList(tarea)
                updateUI(tarea)
            }
        }
    }

    private fun searchTarea(query: String?){
        val searchQuery = "%$query"

        tareasViewModel.searchTarea(searchQuery).observe(this) {list ->
            tareaAdapter.differ.submitList(list)
        }
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        homeBinding = null
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null){
            searchTarea(newText)
        }
        return true
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.home_menu, menu)

        val menuSearch = menu.findItem(R.id.searchMenu ).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = false
        menuSearch.setOnQueryTextListener(this)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }

}
