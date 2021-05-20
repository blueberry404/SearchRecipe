package com.anum.gtl_assignment.data.repository.recipe

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anum.gtl_assignment.data.model.Recipe
import com.anum.gtl_assignment.data.model.RecipeInformation
import com.anum.gtl_assignment.data.model.Recipes
import com.anum.gtl_assignment.utils.Resource
import com.anum.gtl_assignment.utils.Status
import com.anum.gtl_assignment.utils.loadJSONFromAssets
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RecipeRepositoryMock @Inject constructor(@ApplicationContext private val context: Context,
                                               private val gson: Gson): RecipeRepository {

    override suspend fun getRecipes(query: String, apiKey: String): Resource<Recipes> {
        val recipe = Recipes(listOf(
            Recipe(1, "Easy Homemade Rice and Beans", "easy-homemade-rice-and-beans-716627.jpg"),
            Recipe(2, "Spicy Black-Eyed Pea Curry with Swiss Chard and Roasted Eggplant",
            "spicy-black-eyed-pea-curry-with-swiss-chard-and-roasted-eggplant-798400.jpg"),
            Recipe(3, "Cheesy Chicken Enchilada Quinoa Casserole",
            "cheesy-chicken-enchilada-quinoa-casserole-715421.jpg")
        ))
        return Resource(Status.SUCCESS, recipe, null)
    }

    override suspend fun getRecipeDetails(
        recipeId: Long,
        apiKey: String
    ): Resource<RecipeInformation> {
        val data = context.loadJSONFromAssets("recipeDetail.json")
        val info = gson.fromJson<RecipeInformation>(data, RecipeInformation::class.java)
        return Resource(Status.SUCCESS, info, null)
    }
}