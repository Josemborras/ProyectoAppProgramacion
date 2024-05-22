package com.example.trabajo_final_t3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.trabajo_final_t3.R
import com.example.trabajo_final_t3.data.models.ingredients.Resultado
import com.example.trabajo_final_t3.databinding.IngredientesBinding

class Ingredientes(val ingrediente: ArrayList<Resultado>, val name: String?): RecyclerView.Adapter<Ingredientes.IngredientesCelda>() {

    // private val lista = ArrayList<IngredientsResponse>()


    inner class IngredientesCelda(val binding: IngredientesBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientesCelda {
        return IngredientesCelda(IngredientesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return ingrediente.size
    }

    override fun onBindViewHolder(holder: IngredientesCelda, position: Int) {
        var item = ingrediente[position] // para inicializar la variable

        // cambia item por lo que ha seleccionado el usuario en las sugerencias
        //ingrediente.forEach { if (it.name == name) item = it }
        // val item = ingrediente

        /*
         * hacer la petición de la imagen cogiendo el string de aquí: item.image
         * para hacerla mira en: https://spoonacular.com/food-api/docs#Show-Images
         *
         * */
        Glide.with(holder.itemView)
            .load(R.drawable.ic_launcher_background)
            .into(holder.binding.imvImagenIngredienteEnRV)

        holder.binding.tvIngredientName.text = item.name
    }

    fun  refrescarListado(lista: ArrayList<Resultado>){
        ingrediente.clear()
        if (lista != null) {
            /*
             * si añades la lista de personajes sin limpiar antes
             * se cierra la aplicación
             *
             * Primero limpiar la lista, notificar los cambios
             * y después añadir la lista. Volver a notificar
             * los cambios con notifyItemRangeChanged(0, itemCount)
             */
            ingrediente.clear()
            notifyDataSetChanged()
            ingrediente.addAll(lista)
        }
        notifyItemRangeChanged(0, itemCount)
    }

}