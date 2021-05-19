package com.anum.gtl_assignment.ui.recipeDetails

import androidx.lifecycle.*
import com.anum.gtl_assignment.data.model.RecipeInformation
import com.anum.gtl_assignment.data.repository.recipe.RecipeRepository
import com.anum.gtl_assignment.di.ApiKey
import com.anum.gtl_assignment.utils.Resource
import com.anum.gtl_assignment.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    @ApiKey val apiKey: String
) : ViewModel() {

    private var _recipeInfo: MutableLiveData<Resource<RecipeInformation>> = MutableLiveData()
    var recipeSummary: LiveData<String> = Transformations.map(_recipeInfo) { it.data?.summary }
    var recipeInstructions: LiveData<String> = Transformations.map(_recipeInfo) { it.data?.instructions }

    private var _recipeID = MutableLiveData<Long>()

    fun fetchRecipe(id: Long) {
        _recipeID.value = id

        try {
            viewModelScope.launch {
                _recipeInfo.value = recipeRepository.getRecipeDetails(id, apiKey)
            }
        }
        catch(ex: Exception) {
            _recipeInfo.value = Resource(Status.ERROR, null, ex.message)
        }
    }


}