package com.example.trabajo_final_t3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo_final_t3.databinding.HolderStepsBinding
import com.example.trabajo_final_t3.data.models.Steps.Step

class StepAdapter : RecyclerView.Adapter<StepAdapter.StepsHolder>() {

    private var lista = ArrayList<Step>()

    inner class StepsHolder(val binding: HolderStepsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderStepsBinding.inflate(layoutInflater, parent, false)
        return StepsHolder(binding)
    }

    override fun onBindViewHolder(holder: StepsHolder, position: Int) {
        val steps = lista[position]
        holder.binding.tvNumStep.text = steps.number.toString()
        holder.binding.tvStep.text = steps.step
    }

    override fun getItemCount() = lista.size

    fun setSteps(steps: List<Step>) {
        this.lista.clear()
        this.lista.addAll(steps)
        notifyDataSetChanged()
    }
}
