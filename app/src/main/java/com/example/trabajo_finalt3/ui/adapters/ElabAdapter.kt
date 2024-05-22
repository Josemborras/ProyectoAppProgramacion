package com.example.trabajo_finalt3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo_finalT3.databinding.HolderElabBinding
import com.example.trabajo_finalt3.model.data.Steps.StepsResponseItem

class ElabAdapter : RecyclerView.Adapter<ElabAdapter.ElabHolder>() {

    private var lista = ArrayList<StepsResponseItem>()

    inner class ElabHolder(val binding: HolderElabBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(stepsResponseItem: StepsResponseItem) {
            binding.tvElab.text = stepsResponseItem.name

            val stepAdapter = StepAdapter()
            stepAdapter.setSteps(stepsResponseItem.steps)

            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(binding.root.context)
                adapter = stepAdapter
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElabHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderElabBinding.inflate(layoutInflater, parent, false)
        return ElabHolder(binding)
    }

    override fun onBindViewHolder(holder: ElabHolder, position: Int) {
        holder.bind(lista[position])
    }

    override fun getItemCount() = lista.size

    fun setElabs(elabs: List<StepsResponseItem>) {
        this.lista.clear()
        this.lista.addAll(elabs)
        notifyDataSetChanged()
    }
}
