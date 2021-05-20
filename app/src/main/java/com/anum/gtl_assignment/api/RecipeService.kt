package com.anum.gtl_assignment.api

import com.anum.gtl_assignment.data.model.RecipeInformation
import com.anum.gtl_assignment.data.model.Recipes
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeService {

    @GET("recipes/search")
    suspend fun getRecipes(
        @Query("query") query: String,
        @Query("apiKey") apiKey: String
    ): Recipes?

    @GET("recipes/{recipeID}/information")
    suspend fun getRecipeDetails(
        @Path("recipeID") recipeID: Long,
        @Query("apiKey") apiKey: String
    ): RecipeInformation?
}