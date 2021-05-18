package com.anum.gtl_assignment.data.repository.recipe

import com.anum.gtl_assignment.api.RecipeService
import com.anum.gtl_assignment.data.model.RecipeInformation
import com.anum.gtl_assignment.data.model.Recipes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(private val recipeService: RecipeService): RecipeRepository {

    override suspend fun getRecipes(query: String, apiKey: String): Recipes =
        withContext(Dispatchers.IO) {
            return@withContext recipeService.getRecipes(query, apiKey)
        }

    override suspend fun getRecipeDetails(recipeId: Long, apiKey: String): RecipeInformation =
        withContext(Dispatchers.IO) {
            return@withContext recipeService.getRecipeDetails(recipeId, apiKey)
        }
}