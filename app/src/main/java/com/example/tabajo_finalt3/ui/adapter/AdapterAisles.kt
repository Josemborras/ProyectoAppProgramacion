package com.example.tabajo_finalt3.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tabajo_finalt3.data.models.Aisle
import com.example.tabajo_finalt3.databinding.HolderAisleBinding
import com.example.tabajo_finalt3.viewmodel.ViewModel

class AdapterAisles(val listener: Listener) : RecyclerView.Adapter<AdapterAisles.AislesHolder>() {
    private val list = ArrayList<Aisle>()
    private lateinit var adapterItems: AdapterItems
    private lateinit var lifecycleOwner: LifecycleOwner

    interface Listener{
        fun onClickListener(itemId: Int)
    }

    inner class AislesHolder(val binding: HolderAisleBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AislesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HolderAisleBinding.inflate(inflater, parent, false)
        lifecycleOwner = parent.context as LifecycleOwner
        return AislesHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AislesHolder, position: Int) {
        holder.binding.TVlistTitle.text = list[position].aisle

        adapterItems = AdapterItems(object : AdapterItems.Listener{
            override fun onClickListener(idItem: Int) {
                listener.onClickListener(idItem)
            }
        })
        holder.binding.recyclerItems.adapter = adapterItems
        holder.binding.recyclerItems.layoutManager = LinearLayoutManager(holder.itemView.context)

        list[position].items?.let {list ->
            adapterItems.newList(list)
        }



    }

    fun newList(newList: List<Aisle>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}