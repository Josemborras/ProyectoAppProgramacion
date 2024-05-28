package com.example.trabajo_final_t3.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabajo_final_t3.data.Repository
import com.example.trabajo_final_t3.data.models.RecipeCard
import com.example.trabajo_final_t3.data.models.RecipesRandom
import com.example.trabajo_final_t3.data.models.TriviaRandom
import kotlinx.coroutines.launch

class ViewModel: ViewModel() {

//    val selecList = MutableLiveData<TriviaRandom?>()

    //para guardar la lista de recetas por ingredientes
    // private val ingredienRecipetLiveData = MutableLiveData<RecipeResponse?>()

    private val repository by lazy {
        Repository()
    }


    fun recipesRandomAddVw(number: Int): MutableLiveData<RecipesRandom?> {

        val liveData = MutableLiveData<RecipesRandom?>()

        viewModelScope.launch {
            val response = repository.recipesRandomAdd(number)
            if (response.code() == 200) {
                val characterResponse = response.body()
                characterResponse?.let {
                    liveData.postValue(it)
                }
            }

        }
        return liveData

    }

    //LIVE DATA PARA DANI

    //para guardar la lista de recetas por nutrientes

    //    fun setRecipeNutrientResponseLiveData(recipes: RecipesByNutrientsResponse){
            //recipeNutrientResponseLiveData.value = recipes
    //}

    //Para coger la lista de recetas por nutrientes

    //fun getRecipeNutrientResponse() = recipeNutrientResponseLiveData

    //para guardar la lista de recetas por ingredientes

    //    fun setRecipeIngredientLiveData(recipes: RecipeResponse){
             //ingredienRecipetLiveData.value = recipes
    //}

    //Para coger la lista de recetas por ingredientes

    //fun getRecipeIngredientResponse() = ingredienRecipetLiveData



    fun triviaRandomAddVw(): MutableLiveData<TriviaRandom?>{

        val liveData = MutableLiveData<TriviaRandom?>()

        viewModelScope.launch {
            val response = repository.triviaRandomAdd()
            if (response.code() == 200) {
                val characterResponse = response.body()
                characterResponse?.let {
                    liveData.postValue(it)
                }
            }

        }
        return liveData

    }

    fun recipeCardAddvw(id: Int): MutableLiveData<RecipeCard?> {

        val liveData = MutableLiveData<RecipeCard?>()

        viewModelScope.launch {
            val response = repository.recipeCard(id)
            if (response.code() == 200) {
                val characterResponse = response.body()
                characterResponse?.let {
                    liveData.postValue(it)
                }
            }

        }
        return liveData

    }


}