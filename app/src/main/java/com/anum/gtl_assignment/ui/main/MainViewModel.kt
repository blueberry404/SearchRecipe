package com.anum.gtl_assignment.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.anum.gtl_assignment.data.model.Recipe
import com.anum.gtl_assignment.data.model.Recipes
import com.anum.gtl_assignment.data.repository.recipe.RecipeRepository
import com.anum.gtl_assignment.data.repository.recipe.RecipeRepositoryImpl
import com.anum.gtl_assignment.data.repository.recipe.RecipeRepositoryMock
import com.anum.gtl_assignment.di.ApiKey
import com.anum.gtl_assignment.utils.Resource
import com.anum.gtl_assignment.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    @ApiKey val apiKey: String
) : ViewModel() {

    private var _recipes: MutableLiveData<Resource<Recipes>> = MutableLiveData()
    fun recipeListObservable(): LiveData<Resource<Recipes>> = _recipes

    init {
        searchRecipe()
    }

    fun searchRecipe(search: String = "") {
        try {
            viewModelScope.launch {
                _recipes.value = recipeRepository.getRecipes(search, apiKey)
            }
        } catch (ex: Exception) {
            _recipes.value = Resource(Status.ERROR, null, ex.message)
        }
    }
}