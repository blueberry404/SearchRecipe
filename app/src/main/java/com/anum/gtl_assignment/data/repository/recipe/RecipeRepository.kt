package com.anum.gtl_assignment.data.repository.recipe

import com.anum.gtl_assignment.data.model.RecipeInformation
import com.anum.gtl_assignment.data.model.Recipes

interface RecipeRepository {

    suspend fun getRecipes(query: String, apiKey: String): Recipes
    suspend fun getRecipeDetails(recipeId: Long, apiKey: String): RecipeInformation
}