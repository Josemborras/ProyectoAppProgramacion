package com.example.trabajo_finalt3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabajo_finalt3.model.Repositorio
import com.example.trabajo_finalt3.model.data.CardImage.CardImage
import com.example.trabajo_finalt3.model.data.ListRecipe.RecipeItem
import com.example.trabajo_finalt3.model.data.Recipe.RecipeResponse
import com.example.trabajo_finalt3.model.data.Steps.StepsResponse
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {

    private val repositorio = Repositorio()
    private val recipeList = MutableLiveData<ArrayList<RecipeItem>>()
    private val selectedRecipeList = MutableLiveData<RecipeItem>()
    private val cardImageRecipe = MutableLiveData<CardImage>()
    private val stepsRecipe = MutableLiveData<StepsResponse>()

    fun setRecipe(recipe: RecipeItem) {
        selectedRecipeList.value = recipe
    }

    fun getRecipe() = selectedRecipeList

    fun getRecipeInfo(id: Int): MutableLiveData<RecipeResponse> {
        val liveData = MutableLiveData<RecipeResponse>()
        viewModelScope.launch {
            val respuesta = repositorio.getRecipe(id)
            if (respuesta.isSuccessful) {
                respuesta.body()?.let {
                    liveData.postValue(it)
                }
            }
        }
        return liveData
    }


    fun getCardImage(id: Int): MutableLiveData<CardImage>{
        viewModelScope.launch {
            val respuesta = repositorio.getCardImage(id)

            val code = respuesta.code()

            if (code == 200){
                val cardImage = respuesta.body()
                cardImage?.let {
                    cardImageRecipe.postValue(it)
                }
            }
        }
        return cardImageRecipe
    }

    fun getInstructions(id: Int): MutableLiveData<StepsResponse>{
        viewModelScope.launch {
            val respuesta = repositorio.getInstructions(id)

            val code = respuesta.code()

            if (code == 200){
                val steps = respuesta.body()
                steps?.let {
                    stepsRecipe.postValue(it)
                }
            }
        }
        return stepsRecipe
    }
    fun getSimilars(id: Int): MutableLiveData<ArrayList<RecipeItem>>{
        viewModelScope.launch {
            val respuesta = repositorio.listRecipes(id)

            val code = respuesta.code()

            if (code == 200){
                val similars = respuesta.body()
                similars?.let {
                    recipeList.postValue(it)
                }
            }
        }
        return recipeList
    }

}