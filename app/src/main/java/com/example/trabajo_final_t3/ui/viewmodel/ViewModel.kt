package com.example.trabajo_final_t3.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabajo_final_t3.data.models.ingredients.IngredientsResponse
import com.example.trabajo_final_t3.data.retrofit.Repository
import kotlinx.coroutines.launch

class ViewModel: ViewModel() {
    private val repositorio by lazy { Repository() }

    private val ingredientsListLiveData = MutableLiveData<IngredientsResponse>()
    private var suggestionsLiveData = Result
    private var ingredienteResultLiveData = Result

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

    /*fun setSuggestions(ingrediente: Result) { suggestionsLiveData = ingrediente }

    fun getSuggestions() = suggestionsLiveData


    fun setIngredienteResult(ingrediente: Result) { ingredienteResultLiveData = ingrediente }

    fun getIngredienteResult() = ingredienteResultLiveData*/

}