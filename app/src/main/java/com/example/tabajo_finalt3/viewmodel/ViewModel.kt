package com.example.tabajo_finalt3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabajo_finalt3.data.Repository
import com.example.tabajo_finalt3.data.models.RecipesRandom
import com.example.tabajo_finalt3.data.models.TriviaRandom
import kotlinx.coroutines.launch

class ViewModel: ViewModel() {

    val selecList = MutableLiveData<TriviaRandom?>()

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

//    fun triviaRandomAddVw(trive: TriviaRandom){
//        selecList.value = trive
//    }


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


}