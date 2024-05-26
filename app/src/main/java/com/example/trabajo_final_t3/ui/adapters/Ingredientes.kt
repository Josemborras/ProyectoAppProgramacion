package com.example.trabajo_final_t3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.trabajo_final_t3.data.models.ingredients.Resultado
import com.example.trabajo_final_t3.databinding.IngredientesBinding

class Ingredientes(
    val listener: DeleteClickListener
): RecyclerView.Adapter<Ingredientes.IngredientesCelda>() {

    private val lista = ArrayList<Resultado>()

    inner class IngredientesCelda(val binding: IngredientesBinding): ViewHolder(binding.root)

    interface DeleteClickListener {
        fun onDeleteClick(resultado: Resultado)
    }

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
            listener.onDeleteClick(item)
        }
    }

    fun updateList(newList: ArrayList<Resultado>){
        lista.clear()
        lista.addAll(newList)
        notifyDataSetChanged()
    }

    fun getString(): String{
        /*
         * devuelve el string de los nombres de los items separados
         * por comas (apple,banana...) para luego poder hacer la
         * petición de buscar recetas
         * */
        var string = ""
        lista.forEach{
            string = string + "," + it.name
        }

        return string.replaceFirst(",", "")
    }
}