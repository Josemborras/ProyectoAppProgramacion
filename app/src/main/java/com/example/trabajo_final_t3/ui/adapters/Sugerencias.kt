package com.example.trabajo_final_t3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.trabajo_final_t3.data.models.ingredients.IngredientsResponse
import com.example.trabajo_final_t3.databinding.SugerenciasBinding

class Sugerencias(): RecyclerView.Adapter<Sugerencias.SugerenciasCelda>() {

    private val lista = ArrayList<IngredientsResponse>()

    inner class SugerenciasCelda(val binding: SugerenciasBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SugerenciasCelda {
        return SugerenciasCelda(SugerenciasBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: SugerenciasCelda, position: Int) {
        val item = lista[position].results[position]

        holder.binding.searchItemID.text = item.name
    }

}