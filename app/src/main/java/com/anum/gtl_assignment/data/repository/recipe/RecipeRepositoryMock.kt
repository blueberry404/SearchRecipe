package com.anum.gtl_assignment.data.repository.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anum.gtl_assignment.data.model.Recipe
import com.anum.gtl_assignment.data.model.RecipeInformation
import com.anum.gtl_assignment.data.model.Recipes
import com.anum.gtl_assignment.utils.Resource
import com.anum.gtl_assignment.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class RecipeRepositoryMock: RecipeRepository {

    override suspend fun getRecipes(query: String, apiKey: String): LiveData<Resource<Recipes>> {
        var data = MutableLiveData<Resource<Recipes>>()
        data.value = Resource(Status.LOADING, null, null)

//        withContext(Dispatchers.Main) {
//            delay(3000L)
            val recipe = Recipes(listOf(
                Recipe(1, "Easy Homemade Rice and Beans", "easy-homemade-rice-and-beans-716627.jpg"),
                Recipe(2, "Spicy Black-Eyed Pea Curry with Swiss Chard and Roasted Eggplant",
                    "spicy-black-eyed-pea-curry-with-swiss-chard-and-roasted-eggplant-798400.jpg"),
                Recipe(3, "Cheesy Chicken Enchilada Quinoa Casserole",
                    "cheesy-chicken-enchilada-quinoa-casserole-715421.jpg")
            ))
            data.value = Resource(Status.SUCCESS, recipe, null)
//        }

        return data
    }

    override suspend fun getRecipesWithout(query: String, apiKey: String): Resource<Recipes> {
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
    ): LiveData<Resource<RecipeInformation>> {
        TODO("Not yet implemented")
    }


}