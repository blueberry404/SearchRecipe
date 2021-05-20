package com.anum.gtl_assignment.data.repository.recipe

import com.anum.gtl_assignment.api.RecipeService
import com.anum.gtl_assignment.data.db.RecipeDao
import com.anum.gtl_assignment.data.model.RecipeInformation
import com.anum.gtl_assignment.data.model.Recipes
import com.anum.gtl_assignment.utils.Resource
import com.anum.gtl_assignment.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(private val recipeService: RecipeService,
                                               private val recipeDao: RecipeDao) :
    RecipeRepository {

    override suspend fun getRecipes(
        query: String,
        apiKey: String
    ): Resource<Recipes> =
        withContext(Dispatchers.IO) {
            try {
                val recipes = recipeService.getRecipes(query, apiKey)
                if(recipes?.results != null) {
                    return@withContext Resource(Status.SUCCESS, recipes, null)
                }
                else {
                    return@withContext Resource(Status.ERROR, null, "An error occured")
                }
            }
            catch(exc: Exception) {
                return@withContext Resource(Status.ERROR, null, exc.message)
            }
        }

    override suspend fun getRecipeDetails(
        recipeId: Long,
        apiKey: String
    ): Resource<RecipeInformation> =
        withContext(Dispatchers.IO) {
            try {
                val localRecipe = recipeDao.getRecipe(recipeId)
                if(localRecipe != null) {
                    val calendar = Calendar.getInstance()
                    calendar.time = localRecipe.dateCreated
                    calendar.add(Calendar.DAY_OF_YEAR, 7)
//                    calendar.add(Calendar.MINUTE, 1)
                    if(Date().time < calendar.time.time) {
                        return@withContext Resource(Status.SUCCESS, localRecipe, null)
                    }
                }

                val recipeInfo = recipeService.getRecipeDetails(recipeId, apiKey)
                if(recipeInfo != null) {
                    recipeDao.save(recipeInfo)
                    return@withContext Resource(Status.SUCCESS, recipeInfo, null)
                }
                else {
                    return@withContext Resource(Status.ERROR, null, "An error occured")
                }
            }
            catch(exc: Exception) {
                return@withContext Resource(Status.ERROR, null, exc.message)
            }
        }
}