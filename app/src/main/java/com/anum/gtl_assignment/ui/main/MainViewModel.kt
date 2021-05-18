package com.anum.gtl_assignment.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anum.gtl_assignment.data.repository.recipe.RecipeRepository
import com.anum.gtl_assignment.di.ApiKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    @ApiKey val apiKey: String
) : ViewModel() {

    init {
        getRecipes()
    }

    private fun getRecipes() {
        val handler = CoroutineExceptionHandler { _, exception ->
            //Handle your exception
//            loginResponse.value = LoginResponse(message = exception.message?: "Unable to login")
            Log.e("TAG", exception.message.toString())
        }
        viewModelScope.launch(handler) {
            try {
                recipeRepository.getRecipes("", apiKey)
            } catch (exc: Exception) {
                Log.e("TAG", exc.message.toString())
            }
        }
    }
}