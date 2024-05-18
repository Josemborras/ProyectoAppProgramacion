package com.example.trabajo_finalt3.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.trabajo_finalt3.data.models.Item
import com.example.trabajo_finalt3.databinding.HolderItemBinding

class AdapterItems(val listener: Listener) : RecyclerView.Adapter<AdapterItems.ItemHolder>() {
    private val list = ArrayList<Item>()

    interface Listener{
        fun onClickListener(idItem: Int)
    }

    inner class ItemHolder(val binding: HolderItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HolderItemBinding.inflate(inflater, parent, false)
        return ItemHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.binding.TVitem.text = "${list[position].measures?.metric?.amount?.toInt().toString()} ${list[position].measures?.metric?.unit!!} ${list[position].name}"

        holder.itemView.setOnClickListener {
            list[position].id?.let {
                listener.onClickListener(it)
            }
        }
    }

    fun newList(newList: List<Item>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}