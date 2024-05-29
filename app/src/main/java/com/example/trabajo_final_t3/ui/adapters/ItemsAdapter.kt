package com.example.trabajo_finalt3.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.trabajo_final_t3.data.models.shoppingList.Item
import com.example.trabajo_final_t3.databinding.HolderItemBinding

/**
 * @author Sandra Martinez
 *  * [Adapter] AdapterItem
 *  Este es el adaptador para listar los ingredientes.
 *  Es el adaptador del RecyclerView en AdapterAisles.
 */
class ItemsAdapter(val listener: Listener) : RecyclerView.Adapter<ItemsAdapter.ItemHolder>() {
    private val list = ArrayList<Item>()

    interface Listener{
        //este es el metodo al que se va a llamar en AdapterAisles al llamar a su listener
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
        //cargar el nombre del ingrediente, su cantidad y su medida metrica en un mismo textview
        holder.binding.TVitem.text = "${list[position].measures?.metric?.amount?.toInt().toString()} ${list[position].measures?.metric?.unit!!} ${list[position].name}"

        //activar el listener al pulsar sobre un ingrediente/ celda
        holder.itemView.setOnClickListener {
            list[position].id?.let {
                listener.onClickListener(it)
            }
        }
    }

    //para darle valores a la lista o sobreescribirlos
    fun newList(newList: List<Item>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}