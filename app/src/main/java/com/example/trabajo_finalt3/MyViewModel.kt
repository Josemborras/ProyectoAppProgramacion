package com.example.trabajo_finalt3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trabajo_finalt3.model.Repositorio
import com.example.trabajo_finalt3.model.data.Recipe.ListRecipe
import com.example.trabajo_finalt3.model.data.Recipe.RecipeResponse

class MyViewModel: ViewModel() {

    private val repositorio = Repositorio()
    private val listRecipe: MutableLiveData<ListRecipe> = MutableLiveData()
    private val selectedRecipe = MutableLiveData<RecipeResponse>()


}