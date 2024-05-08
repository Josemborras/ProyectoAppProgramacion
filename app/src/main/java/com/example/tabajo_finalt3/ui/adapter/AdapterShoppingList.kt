package com.example.tabajo_finalt3.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tabajo_finalt3.data.models.Item
import com.example.tabajo_finalt3.databinding.HolderShoppingBinding

class AdapterShoppingList(val listener: Listener) : RecyclerView.Adapter<AdapterShoppingList.ListHolder>() {
    private val list = ArrayList<Item>()

    interface Listener{
        fun onCheckListener(isChecked: Boolean, position: Int, item: Item)
    }
    inner class ListHolder(val binding: HolderShoppingBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HolderShoppingBinding.inflate(inflater, parent, false)
        return ListHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.binding.checkBox.text = list[position].name

        holder.binding.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            listener.onCheckListener(isChecked, position, list[position])
        }
    }

    fun newList(newList: List<Item>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun addList(newList: List<Item>){
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun addItem(item: Item){
        list.add(item)
        notifyItemInserted(list.size-1)
        notifyItemRangeChanged(list.size-1, list.size)
    }

    fun removeItem(position: Int){
        list.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list.size)
    }
}