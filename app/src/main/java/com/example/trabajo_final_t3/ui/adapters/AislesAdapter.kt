package com.example.trabajo_finalt3.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.trabajo_final_t3.data.models.shoppingList.Aisle
import com.example.trabajo_final_t3.databinding.HolderAisleBinding

/**
 * @author Sandra Martinez
 *  * [Adapter] AdapterAisles
 *  Este es el adaptador para las secciones que agrupan los ingredientes de la lista de la compra.
 *  Tiene dentro otro RecyclerView para listar los ingredientes corerspondientes a la seccion
 */
class AislesAdapter(val listener: Listener) : RecyclerView.Adapter<AislesAdapter.AislesHolder>() {
    private val list = ArrayList<Aisle>()
    //inicializar el adaptador del RecyclerView
    private lateinit var itemsAdapter: ItemsAdapter

    interface Listener{
        //este metodo del listener funciona como intermediario para conectar el listener de AdapterIngredients
        // con el fragment y poder usar asi el viewmodel al pulsar sobre un ingrediente
        fun onClickListener(itemId: Int)
    }

    inner class AislesHolder(val binding: HolderAisleBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AislesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HolderAisleBinding.inflate(inflater, parent, false)
        return AislesHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AislesHolder, position: Int) {
        //cargar el titulo de la seccion
        holder.binding.TVlistTitle.text = list[position].aisle

        //configurar el recyclerview que tiene esta celda y cargar el ArrayList de su adaptador
        itemsAdapter = ItemsAdapter(object : ItemsAdapter.Listener{
            override fun onClickListener(idItem: Int) {
                listener.onClickListener(idItem)
                //el listener de AdapterItems va a ejecutar el mismo codigo que se le indique al listener de AdapterAisles desde el fragment
            }
        })
        holder.binding.recyclerItems.adapter = itemsAdapter
        holder.binding.recyclerItems.layoutManager = LinearLayoutManager(holder.itemView.context)

        list[position].items?.let {list ->
            itemsAdapter.newList(list)
        }
    }

    //para darle valores al ArrayList o sobreescribirlos
    fun newList(newList: List<Aisle>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}