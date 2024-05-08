package com.example.tabajo_finalt3.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tabajo_finalt3.databinding.FragmentceldarecetaBinding

class Adaptadorlistado( val listener: MyClick): RecyclerView.Adapter<Adaptadorlistado.disenioPlantilla>(){

    private val person = ArrayList<Character?>()

    inner class disenioPlantilla(val binding: FragmentceldarecetaBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface MyClick {
        fun onClick(character: Character)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): disenioPlantilla {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FragmentceldarecetaBinding.inflate(layoutInflater, parent, false)
        return disenioPlantilla(binding)
    }

    override fun getItemCount(): Int {
        return person.size
    }

    override fun onBindViewHolder(holder: disenioPlantilla, position: Int) {
        val personaje = person[position]

//        holder.binding.nombreper.text = personaje?.name.toString()
//        Glide.with(holder.itemView).load(personaje?.image).into(holder.binding.imageView)
//        holder.itemView.setOnClickListener {
//            if (personaje != null) {
//                listener.onClick(personaje)
//            }
//        }


    }


    fun refrescarListado(lista: List<Character?>?){
        person.clear()
        if(lista != null){
            person.addAll(lista)
        }
        notifyItemRangeChanged(0, itemCount)
    }
}
