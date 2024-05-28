package com.example.trabajo_final_t3.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabajo_final_t3.data.Repository
import com.example.trabajo_final_t3.data.models.RecipeCard
import com.example.trabajo_final_t3.data.models.RecipesRandom
import com.example.trabajo_final_t3.data.models.TriviaRandom
import com.example.trabajo_final_t3.data.models.ingredients.IngredientsResponse
import com.example.trabajo_final_t3.data.models.ingredients.Resultado
import com.example.trabajo_final_t3.data.models.recipesbynutrients.RecipesByNutrientsResponse
import com.example.trabajo_final_t3.data.models.CardImage.CardImage
import com.example.trabajo_final_t3.data.models.ListRecipe.RecipeItem
import com.example.trabajo_final_t3.data.models.Recipe.Recipe
import com.example.trabajo_final_t3.data.models.Steps.StepsResponse
import com.example.trabajo_final_t3.data.models.recipes.RecipeResponse
import com.example.trabajo_finalt3.data.models.Item
import com.example.trabajo_finalt3.data.models.PostItem
import com.example.trabajo_finalt3.data.models.ResponseDeleteItem
import com.example.trabajo_finalt3.data.models.ResponseGetShoppingList
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {

    private val repository by lazy {
        Repository()
    }

    private val recipeList = MutableLiveData<ArrayList<RecipeItem>>()
    private val selectedRecipeList = MutableLiveData<RecipeItem>()
    private val cardImageRecipe = MutableLiveData<CardImage>()
    private val stepsRecipe = MutableLiveData<StepsResponse>()

    // respuesta del servidor
    private val ingrResponseLiveData = MutableLiveData<IngredientsResponse>()
    // livedata que contiene mi lista de ingredientes seleccionados
    private var ingrListLiveData = MutableLiveData<ArrayList<Resultado>>()

    // lista de recetas que devuelve la petición de getRecipesByIngredients
    private val recipesListLiveData = MutableLiveData<RecipeResponse>()

    // lista de recetas que devuelve la petición de getRecipesByNutrients
    private val recipesByNutrientsListLiveData = MutableLiveData<RecipesByNutrientsResponse>()

    // liveData para guardar las sugerencias que se muestran en el buscador
    private var suggestions = MutableLiveData<IngredientsResponse>()

    init {
        // init se encarga de inicializar valores la primera vez que se crea el livedata
        // aquí agregamos un listado vacio a mi lista de ingredietnes
        ingrListLiveData.postValue(ArrayList())
    }

    //    val selecList = MutableLiveData<TriviaRandom?>()

    //para guardar la lista de recetas por ingredientes
    // private val ingredienRecipetLiveData = MutableLiveData<RecipeResponse?>()

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

    fun setRecipe(recipe: RecipeItem) {
        selectedRecipeList.value = recipe
    }

    fun getRecipe() = selectedRecipeList

    fun getRecipeInfo(id: Int): MutableLiveData<Recipe> {
        val liveData = MutableLiveData<Recipe>()
        viewModelScope.launch {
            val respuesta = repository.getRecipe(id)
            if (respuesta.isSuccessful) {
                respuesta.body().let {
                    liveData.postValue(it)
                }
            }
        }
        return liveData
    }


    fun getCardImage(id: Int): MutableLiveData<CardImage>{
        viewModelScope.launch {
            val respuesta = repository.getCardImage(id)

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
            val respuesta = repository.getInstructions(id)

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
            val respuesta = repository.listRecipes(id)

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

    fun setSuggestions(sugerencia: IngredientsResponse) { suggestions.postValue(sugerencia) }

    fun getIngredients(ingredientName: String): MutableLiveData<IngredientsResponse>{
        viewModelScope.launch {
            val response = repository.getIngredients(ingredientName)

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

    fun getRecipesByIngredients(ingredientsNames: String): MutableLiveData<RecipeResponse>{
        viewModelScope.launch {
            val response = repository.getRecipesByIngredients(ingredientsNames)

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
            val response = repository.getRecipesByNutrients(minCarbs, maxCarbs, minProtein, maxProtein, minFat, maxFat, minCalories, maxCalories, number)

            if (response.code() == 200){
                response.body().let {
                    recipesByNutrientsListLiveData.postValue(it)
                    Log.d("viewModelScope", it.toString() + " respose.code() == 200")
                }
            }else Log.d("viewModelScope", "response.code() == " + response.code().toString())
        }
        return recipesByNutrientsListLiveData
    }


    /**
     * @author Sandra Martinez
     * funciones get y delete de la lista de la compra
     */

    //GET
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

    //DELETE
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

    //POST
    fun addItemShoppingList(item: PostItem): MutableLiveData<Item> {
        val itemLiveData = MutableLiveData<Item>()

        viewModelScope.launch {
            val res = repository.addItemShoppingList(item)
            if(res.code() == 200){
                itemLiveData.postValue(res.body())
            }
        }

        return itemLiveData
    }
}