package com.example.trabajo_final_t3.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabajo_final_t3.data.models.ingredients.IngredientsResponse
import com.example.trabajo_final_t3.data.models.ingredients.Resultado
import com.example.trabajo_final_t3.data.models.recipes.RecipeResponse
import com.example.trabajo_final_t3.data.models.recipesbynutrients.RecipesByNutrientsResponse
import com.example.trabajo_final_t3.data.retrofit.Repository
import kotlinx.coroutines.launch

class ViewModel: ViewModel() {
    private val repositorio by lazy { Repository() }

    // respuesta del servidor
    private val ingrResponseLiveData = MutableLiveData<IngredientsResponse>()
    // livedata que contiene mi lista de ingredientes seleccionados
    private var ingrListLiveData = MutableLiveData<ArrayList<Resultado>>()

    private val recipesListLiveData = MutableLiveData<RecipeResponse>()
    private val recipesByNutrientsListLiveData = MutableLiveData<RecipesByNutrientsResponse>()
    private var suggestions = MutableLiveData<IngredientsResponse>()

    private val recipeNutientsResponseLiveData = MutableLiveData<RecipesByNutrientsResponse>()
    init {
        // init se encarga de inicializar valores la primera vez que se crea el livedata
        // aquí agregamos un listado vacio a mi lista de ingredietnes
        ingrListLiveData.postValue(ArrayList())
    }

    fun setSuggestions(sugerencia: IngredientsResponse) { suggestions.postValue(sugerencia) }

    fun getIngredients(ingredientName: String): MutableLiveData<IngredientsResponse>{
        viewModelScope.launch {
            val response = repositorio.getIngredients(ingredientName)

            if (response.code() == 200){
                response.body().let {
                    ingrResponseLiveData.postValue(it)
                    Log.d("viewModelScope", it.toString() + " respose.code() == 200")
                }
            }else Log.d("viewModelScope", "response.code() == " + response.code().toString())
        }
        return ingrResponseLiveData
    }

    fun getIngredienteResult() = ingrListLiveData

    fun filterList(name: String): MutableLiveData<ArrayList<Resultado>> {
        val filteredResult = MutableLiveData<ArrayList<Resultado>>()

        ingrResponseLiveData.value?.let { response ->
            filteredResult.value = response.results.filter { result ->
                result.name == name
            } as ArrayList<Resultado>
        }

        return filteredResult
    }

    fun addIngredienteResult(ingrediente: Resultado) {
        val list = ingrListLiveData.value
        list?.add(ingrediente)
        list?.let { ingrListLiveData.postValue(it) }
    }

    fun removeIngredientResult(ingrediente: Resultado) {
        val list = ingrListLiveData.value
        list?.remove(ingrediente)
        list?.let { ingrListLiveData.postValue(it) }
    }

    fun getRecipes(ingredientsNames: String): MutableLiveData<RecipeResponse>{

        viewModelScope.launch {
            val response = repositorio.getRecipes(ingredientsNames)

            if (response.code() == 200){
                response.body().let {
                    recipesListLiveData.postValue(it)
                    Log.d("viewModelScope", it.toString() + " respose.code() == 200")
                }
            }else Log.d("viewModelScope", "response.code() == " + response.code().toString())
        }
        return recipesListLiveData
    }

    fun getRecipesByNutrients(minCarbs: Int, maxCarbs: Int, minProtein: Int, maxProtein: Int, minFat: Int, maxFat: Int, minCalories: Int, maxCalories: Int, number: Int): MutableLiveData<RecipesByNutrientsResponse>{

        viewModelScope.launch {
            val response = repositorio.getRecipesByNutrients(minCarbs, maxCarbs, minProtein, maxProtein, minFat, maxFat, minCalories, maxCalories, number)

            if (response.code() == 200){
                response.body().let {
                    recipesByNutrientsListLiveData.postValue(it)
                    Log.d("viewModelScope", it.toString() + " respose.code() == 200")
                }
            }else Log.d("viewModelScope", "response.code() == " + response.code().toString())
        }
        return recipesByNutrientsListLiveData
    }

    fun setRecipesNutrientsResponse(recipes: RecipesByNutrientsResponse) { recipeNutientsResponseLiveData.value = recipes }

    fun getRecipesNutrientsResponse() = recipeNutientsResponseLiveData
}