package com.example.trabajo_final_t3.data

import com.example.trabajo_final_t3.data.models.AllRecipeInfo.Recipe
import com.example.trabajo_final_t3.data.models.shoppingList.Item
import com.example.trabajo_final_t3.data.models.shoppingList.PostItem
import com.example.trabajo_final_t3.data.models.shoppingList.ResponseDeleteItem
import com.example.trabajo_final_t3.data.retrofit.RetrofitObjeto
import retrofit2.Response


class Repository {

    /**
     * @author Maria Belen
     */

    suspend fun recipesRandomAdd(number: Int) = RetrofitObjeto.retrofitInterface.rescipesRandom(number)

    suspend fun triviaRandomAdd() = RetrofitObjeto.retrofitInterface.triviaRandom()

    suspend fun recipeCard(id: Int) = RetrofitObjeto.retrofitInterface.recipeCard(id)

    /**
     * @author Jose
     */

    suspend fun getRecipe(id: Int): Response<Recipe> {
        return RetrofitObjeto.retrofitInterface.obtenerRecipe(id)
    }
    suspend fun listRecipes(id: Int) = RetrofitObjeto.retrofitInterface.obtenerRecetasSimilares(id)

    suspend fun getInstructions(id: Int) = RetrofitObjeto.retrofitInterface.obtenerInstructions(id)

    /**
     * @author Daniel
     */

    suspend fun getIngredients(ingredientName: String) = RetrofitObjeto.retrofitInterface.getIngredients(ingredientName)

    suspend fun getRecipesByIngredients(ingredientsNames: String) = RetrofitObjeto.retrofitInterface.getRecipesByIngredients(ingredientsNames)

    suspend fun getRecipesByNutrients(minCarbs: Int, maxCarbs: Int, minProtein: Int, maxProtein: Int, minFat: Int, maxFat: Int, minCalories: Int, maxCalories: Int, number: Int) = RetrofitObjeto.retrofitInterface.getRecipesByNutrients(minCarbs, maxCarbs, minProtein, maxProtein, minFat, maxFat, minCalories, maxCalories, number)

    /**
     * @author Sandra Martinez
     * peticiones get, delete y post de la lista de la compra
     * Los parametros del usuario tendran un valor por defecto
     */

    //GET
    suspend fun getShoppingList(
        username: String = "sandra-m",
        usernameQuery: String = "sandra-m",
        hash: String = "56d9160fc457a3d586fe75d3f885da91654a26cd"
    ) = RetrofitObjeto.retrofitInterface.getShoppingList(username, usernameQuery, hash)

    //DELETE
    suspend fun deleteItemShoppingList(itemId: Int): Response<ResponseDeleteItem> {
        val username = "sandra-m"
        val usernameQuery = "sandra-m"
        val hash= "56d9160fc457a3d586fe75d3f885da91654a26cd"

        return RetrofitObjeto.retrofitInterface.deleteItemShoppingList(username, itemId, usernameQuery, itemId, hash)
    }

    //POST
    suspend fun addItemShoppingList(item: PostItem): Response<Item> {
        val username = "sandra-m"
        val usernameQuery = "sandra-m"
        val hash= "56d9160fc457a3d586fe75d3f885da91654a26cd"

        return RetrofitObjeto.retrofitInterface.addItemShoppingList(item, username, usernameQuery, hash)
    }
}