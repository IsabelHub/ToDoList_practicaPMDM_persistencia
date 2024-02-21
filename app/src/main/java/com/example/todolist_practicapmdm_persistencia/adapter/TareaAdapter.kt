package com.example.todolist_practicapmdm_persistencia.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist_practicapmdm_persistencia.databinding.TareaLayoutBinding
import com.example.todolist_practicapmdm_persistencia.fragments.HomeDirections
import com.example.todolist_practicapmdm_persistencia.model.Tarea

class TareaAdapter : RecyclerView.Adapter<TareaAdapter.TareaViewHolder>() {

    class TareaViewHolder(val itemBinding : TareaLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Tarea>(){
        override fun areItemsTheSame(oldItem: Tarea, newItem: Tarea): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.tareaDescripcion == newItem.tareaDescripcion &&
                    oldItem.nombreAsignatura == newItem.nombreAsignatura
        }
        override fun areContentsTheSame(oldItem: Tarea, newItem: Tarea): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        return TareaViewHolder(
            TareaLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val currentTarea = differ.currentList[position]

        holder.itemBinding.tareaTitle.text = currentTarea.nombreAsignatura
        holder.itemBinding.TareaDesc.text = currentTarea.tareaDescripcion

        holder.itemView.setOnClickListener{
            val direction = HomeDirections.actionHome2ToEditNoteFragment(currentTarea)
            it.findNavController().navigate(direction)
        }
    }
}