package com.example.trabajo_finalt3.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.trabajo_final_t3.databinding.HolderAisleBinding
import com.example.trabajo_finalt3.data.models.Aisle

/**
 * @author Sandra Martinez
 *  * [Adapter] AdapterAisles
 *  Este es el adaptador para las listas que agrupan los ingredientes de la lista de la compra.
 *  Tiene dentro otro RecyclerView para listar los ingredientes
 */
class AdapterAisles(val listener: Listener) : RecyclerView.Adapter<AdapterAisles.AislesHolder>() {
    private val list = ArrayList<Aisle>()
    //inicializar el adaptador del RecyclerView
    private lateinit var adapterItems: AdapterItems

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
        //cargar el titulo de la lista
        holder.binding.TVlistTitle.text = list[position].aisle

        //configurar el recyclerview que tiene esta celda y cargar la lista de su adaptador
        adapterItems = AdapterItems(object : AdapterItems.Listener{
            override fun onClickListener(idItem: Int) {
                listener.onClickListener(idItem)
                //el listener de AdapterItems va a ejecutar el mismo codigo que se le indique al listener de AdapterAisles desde el fragment
            }
        })
        holder.binding.recyclerItems.adapter = adapterItems
        holder.binding.recyclerItems.layoutManager = LinearLayoutManager(holder.itemView.context)

        list[position].items?.let {list ->
            adapterItems.newList(list)
        }
    }

    //para darle valores a la lista o sobreescribirlos
    fun newList(newList: List<Aisle>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}