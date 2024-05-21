package com.example.trabajo_finalt3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabajo_finalt3.model.Repositorio
import com.example.trabajo_finalt3.model.data.CardImage.CardImage
import com.example.trabajo_finalt3.model.data.ListRecipe.RecipeItem
import com.example.trabajo_finalt3.model.data.Recipe.ListRecipe
import com.example.trabajo_finalt3.model.data.Recipe.RecipeResponse
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {

    private val repositorio = Repositorio()
    private val listRecipe: MutableLiveData<ListRecipe> = MutableLiveData()
    private val selectedRecipeList = MutableLiveData<RecipeItem>()
    private val selectedRecipe = MutableLiveData<RecipeResponse>()
    private val cardImageRecipe = MutableLiveData<CardImage>()

    fun setRecipe(recipe: RecipeItem) {
        selectedRecipeList.value = recipe
    }

    fun getRecipe() = selectedRecipeList

    fun getRecipeInfo(id: Int): MutableLiveData<RecipeResponse>{
        viewModelScope.launch {
            val respuesta = repositorio.getRecipe(id)

            val code = respuesta.code()

            if (code == 200){
                val recipeInfo = respuesta.body()
                recipeInfo?.let {
                    selectedRecipe.postValue(it)
                }
            }
        }
        return selectedRecipe
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

}