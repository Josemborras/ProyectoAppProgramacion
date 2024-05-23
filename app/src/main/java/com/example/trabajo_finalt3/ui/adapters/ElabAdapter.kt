package com.example.trabajo_finalt3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo_finalT3.databinding.HolderElabBinding
import com.example.trabajo_finalt3.model.data.Steps.Step
import com.example.trabajo_finalt3.model.data.Steps.StepsResponseItem

class ElabAdapter : RecyclerView.Adapter<ElabAdapter.ElabHolder>() {

    private var lista = ArrayList<StepsResponseItem>()
    private lateinit var adapterStep: StepAdapter

    inner class ElabHolder(val binding: HolderElabBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElabHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderElabBinding.inflate(layoutInflater, parent, false)
        return ElabHolder(binding)
    }

    override fun onBindViewHolder(holder: ElabHolder, position: Int) {
        val elabs = lista[position]
        holder.binding.tvElab.text = elabs.name
        configRecyclerSteps(holder, elabs.steps)
    }

    private fun configRecyclerSteps(holder: ElabHolder, steps: List<Step>) {
        adapterStep = StepAdapter()
        adapterStep.setSteps(steps)

        holder.binding.rvSteps.layoutManager = LinearLayoutManager(holder.binding.rvSteps.context)
        holder.binding.rvSteps.adapter = adapterStep
    }

    override fun getItemCount() = lista.size

    fun setElabs(elabs: List<StepsResponseItem>) {
        this.lista.clear()
        this.lista.addAll(elabs)
        notifyDataSetChanged()
    }
}

