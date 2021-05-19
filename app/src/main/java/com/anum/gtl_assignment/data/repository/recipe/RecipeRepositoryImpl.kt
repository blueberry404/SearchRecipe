package com.anum.gtl_assignment.data.repository.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.anum.gtl_assignment.api.RecipeService
import com.anum.gtl_assignment.data.model.Recipe
import com.anum.gtl_assignment.data.model.RecipeInformation
import com.anum.gtl_assignment.data.model.Recipes
import com.anum.gtl_assignment.utils.Resource
import com.anum.gtl_assignment.utils.ResponseHandler
import com.anum.gtl_assignment.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(private val recipeService: RecipeService) :
    RecipeRepository {

    override suspend fun getRecipes(query: String, apiKey: String): LiveData<Resource<Recipes>> =
        liveData(Dispatchers.IO) {

            emit(Resource(Status.LOADING, null, null))
            try {
                val recipes = recipeService.getRecipes(query, apiKey)
                if(recipes?.results != null) {
                    emit(Resource(Status.SUCCESS, recipes, null))
                }
                else {
                    emit(Resource(Status.ERROR, null, "An error occured"))
                }
            }
            catch(exc: Exception) {
                emit(Resource(Status.ERROR, null, exc.message))
            }
        }

    override suspend fun getRecipesWithout(
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
    ): LiveData<Resource<RecipeInformation>> {
//        withContext(Dispatchers.IO) {
//            return@withContext recipeService.getRecipeDetails(recipeId, apiKey)
//        }
        var data = MutableLiveData<Resource<RecipeInformation>>()
        data.value = Resource(Status.LOADING, null, null)
        return data
    }
}