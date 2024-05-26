package com.example.trabajo_final_t3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.trabajo_final_t3.data.models.ingredients.Resultado
import com.example.trabajo_final_t3.databinding.IngredientesBinding
import kotlin.time.Duration.Companion.milliseconds

class Ingredientes(): RecyclerView.Adapter<Ingredientes.IngredientesCelda>() {

    private val lista = ArrayList<Resultado>()

    inner class IngredientesCelda(val binding: IngredientesBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientesCelda {
        return IngredientesCelda(IngredientesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: IngredientesCelda, position: Int) {
        var item = lista[position] // para inicializar la variable

        Glide.with(holder.itemView)
            .load("https://img.spoonacular.com/ingredients_100x100/" + item.image)
            .into(holder.binding.imvImagenIngredienteEnRV)

        holder.binding.tvIngredientName.text = item.name

        holder.binding.imvDeleteIngredient.setOnClickListener {
            /*
             * al pulsar para borrar un ingrediente, lo elimina
             * de la lista y lo notifica
             * */
            lista.removeAt(position)
            notifyDataSetChanged()
        }
    }

    fun addIngredient(ingredient: Resultado){
        // añade un ingrediente al recyclerView
        lista.add(ingredient)
        notifyDataSetChanged()
    }

    fun clearIngredients(){
        // vacía la lista del recyclerView
        lista.clear()
        notifyDataSetChanged()
    }

    fun getString(): String{
        /*
         * devuelve el string de los nombres de los items separados
         * por comas (apple, banana...) para luego poder hacer la
         * petición de buscar recetas
         * */
        var string = ""
        lista.forEach{
            string = string + "," + it.name
        }

        return string.replaceFirst(",", "")
    }

}