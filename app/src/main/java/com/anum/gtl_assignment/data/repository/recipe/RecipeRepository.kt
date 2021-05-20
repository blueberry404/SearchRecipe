package com.anum.gtl_assignment.data.repository.recipe

import com.anum.gtl_assignment.data.model.RecipeInformation
import com.anum.gtl_assignment.data.model.Recipes
import com.anum.gtl_assignment.utils.Resource

interface RecipeRepository {

    suspend fun getRecipes(query: String, apiKey: String): Resource<Recipes>
    suspend fun getRecipeDetails(recipeId: Long, apiKey: String): Resource<RecipeInformation>
}