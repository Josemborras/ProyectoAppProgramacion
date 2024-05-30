package com.example.trabajo_final_t3.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabajo_final_t3.data.Repository
import com.example.trabajo_final_t3.data.models.AllRecipeInfo.Recipe
import com.example.trabajo_final_t3.data.models.SearchIngredient.IngredientsResponse
import com.example.trabajo_final_t3.data.models.SearchIngredient.RecipeResultSearch
import com.example.trabajo_final_t3.data.models.SearchRecipesByIngredients.ListRecipeResponse
import com.example.trabajo_final_t3.data.models.SearchRecipesByIngredients.RecipesResponseItem
import com.example.trabajo_final_t3.data.models.SearchRecipesByNutrients.RecipesByNutrientsResponse
import com.example.trabajo_final_t3.data.models.StepsRecipe.StepsResponse
import com.example.trabajo_final_t3.data.models.recipeCard.RecipeCard
import com.example.trabajo_final_t3.data.models.recipeRandom.RecipesRandom
import com.example.trabajo_final_t3.data.models.shoppingList.Item
import com.example.trabajo_final_t3.data.models.shoppingList.PostItem
import com.example.trabajo_final_t3.data.models.shoppingList.ResponseDeleteItem
import com.example.trabajo_final_t3.data.models.shoppingList.ResponseGetShoppingList
import com.example.trabajo_final_t3.data.models.triviaRandom.TriviaRandom
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {

    private val repository by lazy {
        Repository()
    }

    private val recipeList = MutableLiveData<ArrayList<RecipesResponseItem>>()
    private val selectedRecipeList = MutableLiveData<RecipesResponseItem>()
    private val stepsRecipe = MutableLiveData<StepsResponse>()
    private val selectedRecipeRandom = MutableLiveData<Recipe>()
    private val selectedRecipeSearch = MutableLiveData<RecipesResponseItem>()
    private val livedataBoolean = MutableLiveData<Boolean>()
    private val selectedRecipeFragment = MutableLiveData<Recipe>()
    private val listSimilar = MutableLiveData<ArrayList<RecipesResponseItem>>()

    fun setBoolean(boolean: Boolean){
        livedataBoolean.value = boolean
    }

    fun getBoolean(): MutableLiveData<Boolean> {
        return livedataBoolean
    }

    private val selectedRecipe = MutableLiveData<RecipesResponseItem>()

    // respuesta del servidor
    private val ingrResponseLiveData = MutableLiveData<IngredientsResponse>()
    // livedata que contiene mi lista de ingredientes seleccionados
    private var ingrListLiveData = MutableLiveData<ArrayList<RecipeResultSearch>>()

    // lista de recetas que devuelve la petición de getRecipesByIngredients
    private val recipesListLiveData = MutableLiveData<ListRecipeResponse>()

    // lista de recetas que devuelve la petición de getRecipesByNutrients
    private val recipesByNutrientsListLiveData = MutableLiveData<ListRecipeResponse>()

    // liveData para guardar las sugerencias que se muestran en el buscador
    private var suggestions = MutableLiveData<IngredientsResponse>()

    private var ingredienRecipetLiveData = MutableLiveData<ListRecipeResponse>()

    init {
        // init se encarga de inicializar valores la primera vez que se crea el livedata
        // aquí agregamos un listado vacio a mi lista de ingredietnes
        ingrListLiveData.postValue(ArrayList())
    }

    //    val selecList = MutableLiveData<TriviaRandom?>()

    //para guardar la lista de recetas por ingredientes
    // private val ingredienRecipetLiveData = MutableLiveData<RecipeResponse?>()

    fun setRecipeRandom(recipe: Recipe){
        selectedRecipeRandom.value = recipe
    }

    fun getRecipeRandom() = selectedRecipeRandom

    fun setRecipeSearch(recipe: RecipesResponseItem){
        selectedRecipeSearch.value = recipe
    }

    fun getRecipeSearch() = selectedRecipeSearch

    fun setRecipeFragment(recipe: Recipe){
        selectedRecipeFragment.value = recipe
    }

    fun getRecipeFragment() = selectedRecipeFragment

    fun setListSimilar(similars: ArrayList<RecipesResponseItem>){
        listSimilar.value = similars
    }

    fun getListSimilar() = listSimilar

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

    fun setRecipeNutrientResponseLiveData(recipes: RecipesByNutrientsResponse){
        val liveData = MutableLiveData<RecipesByNutrientsResponse>()

        liveData.value = recipes
    }

    //Para coger la lista de recetas por nutrientes

    //fun getRecipeNutrientResponse() =

    //para guardar la lista de recetas por ingredientes

    fun setRecipeIngredientLiveData(recipes: ListRecipeResponse){
             ingredienRecipetLiveData.value = recipes
    }

    //Para coger la lista de recetas por ingredientes

    fun getRecipeIngredientResponse() = ingredienRecipetLiveData

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

    fun recipeCardAddvm(id: Int): MutableLiveData<RecipeCard?> {
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

    fun setRecipe(recipe: RecipesResponseItem) {
        selectedRecipeList.value = recipe
    }

    // fun getRecipe() = selectedRecipeList

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
    fun getSimilars(id: Int): MutableLiveData<ArrayList<RecipesResponseItem>>{
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

    fun filterList(name: String): MutableLiveData<ArrayList<RecipeResultSearch>> {
        val filteredResult = MutableLiveData<ArrayList<RecipeResultSearch>>()

        ingrResponseLiveData.value?.let { response ->
            filteredResult.value = response.results.filter { result ->
                result.name == name
            } as ArrayList<RecipeResultSearch>
        }

        return filteredResult
    }

    fun addIngredienteResult(ingrediente: RecipeResultSearch) {
        val list = ingrListLiveData.value
        list?.add(ingrediente)
        list?.let { ingrListLiveData.postValue(it) }
    }

    fun removeIngredientResult(ingrediente: RecipeResultSearch) {
        val list = ingrListLiveData.value
        list?.remove(ingrediente)
        list?.let { ingrListLiveData.postValue(it) }
    }

    fun getRecipesByIngredients(ingredientsNames: String): MutableLiveData<ListRecipeResponse>{
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

    fun getRecipesByNutrients(minCarbs: Int, maxCarbs: Int, minProtein: Int, maxProtein: Int, minFat: Int, maxFat: Int, minCalories: Int, maxCalories: Int, number: Int): MutableLiveData<ListRecipeResponse>{

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