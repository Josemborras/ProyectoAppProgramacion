package com.example.trabajo_final_t3.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo_final_t3.data.models.StepsRecipe.Step
import com.example.trabajo_final_t3.data.models.StepsRecipe.StepsResponseItem
import com.example.trabajo_final_t3.databinding.HolderElabBinding

class ElabAdapter : RecyclerView.Adapter<ElabAdapter.ElabHolder>() {

    /*
     * Adaptador para listar las elaboraciones de cada recetas
     * */

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

        if (elabs.name.isEmpty()) {
            holder.binding.tvElab.visibility = View.GONE
        } else {
            holder.binding.tvElab.visibility = View.VISIBLE
        }
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

