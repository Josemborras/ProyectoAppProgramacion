package com.example.tabajo_finalt3.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.tabajo_finalt3.data.Repository
import com.example.tabajo_finalt3.data.models.ResponseGetShoppingList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tabajo_finalt3.data.models.ResponseDeleteItem
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {
    private val repository by lazy {
        Repository()
    }

    /**
     * @author Sandra Martinez
     * funciones get y delete de la lista de la compra
     */
    fun getShoppingList(): MutableLiveData<ResponseGetShoppingList> {
        val listLiveData = MutableLiveData<ResponseGetShoppingList>()

        viewModelScope.launch{
            val res = repository.getShoppingList()
            if(res.code() == 200){
                listLiveData.postValue(res.body())
            }
        }

        return listLiveData
    }

    fun deleteItemShoppingList(idItem: Int): MutableLiveData<ResponseDeleteItem> {
        val deleteResLiveData = MutableLiveData<ResponseDeleteItem>()

        viewModelScope.launch {
            val res = repository.deleteItemShoppingList(idItem)
            if(res.code() == 200) {
                deleteResLiveData.postValue(res.body())
            }
        }

        return deleteResLiveData
    }
}