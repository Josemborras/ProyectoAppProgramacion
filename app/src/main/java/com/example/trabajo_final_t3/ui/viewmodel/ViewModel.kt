package com.example.trabajo_final_t3.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabajo_final_t3.data.models.ingredients.IngredientsResponse
import com.example.trabajo_final_t3.data.models.ingredients.Resultado
import com.example.trabajo_final_t3.data.retrofit.Repository
import kotlinx.coroutines.launch

class ViewModel: ViewModel() {
    private val repositorio by lazy { Repository() }

    private val ingredientsListLiveData = MutableLiveData<IngredientsResponse>()
    private var suggestionsLiveData = ArrayList<Resultado>()
    private var ingredienteResultLiveData = ArrayList<Resultado>()
    private var suggestionSelected = String()

    fun getIngredients(ingredientName: String): MutableLiveData<IngredientsResponse>{

        viewModelScope.launch {
            val response = repositorio.getIngredients(ingredientName)

            if (response.code() == 200){
                response.body().let {
                    ingredientsListLiveData.postValue(it)
                    Log.d("viewModelScope", it.toString() + " respose.code() == 200")
                }
            }else Log.d("viewModelScope", "response.code() == " + response.code().toString())
        }
        return ingredientsListLiveData
    }

    fun setSuggestions(ingrediente: ArrayList<Resultado>) { suggestionsLiveData = ingrediente }

    fun getSuggestions() = suggestionsLiveData

/*
 * no sé cómo hacer para guardar un listado de los ingredientes que mete
 * el usuario, creo que sería como con setSuggestions/getSuggestions
 *
 * */
    fun setIngredienteResult(ingrediente: ArrayList<Resultado>) { ingredienteResultLiveData = ingrediente }

    fun getIngredienteResult() = ingredienteResultLiveData

    fun setSuggestionsSelected(name: String) { suggestionSelected = name }

    fun getSuggestionsSelected() = suggestionSelected

    fun getRecipes(ingredientsNames: String) {

    }

}