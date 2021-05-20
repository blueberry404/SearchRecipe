package com.anum.gtl_assignment.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

data class Recipes(var results: List<Recipe>? = null)

data class Recipe(var id: Long = 0, var title: String = "N/A", var image: String? = null)

@Entity
data class RecipeInformation(
    @PrimaryKey val id: Long = 0,
    val image: String? = null,
    val instructions: String? = null,
    val preparationMinutes: Int? = null,
    val pricePerServing: Double? = null,
    val readyInMinutes: Int? = null,
    val servings: Int? = null,
    val sourceName: String? = null,
    val sourceUrl: String? = null,
    val spoonacularScore: Double? = null,
    val spoonacularSourceUrl: String? = null,
    val summary: String? = null,
    val title: String? = null,
    val vegan: Boolean? = null,
    val vegetarian: Boolean? = null,
    val veryHealthy: Boolean? = null,
    val veryPopular: Boolean? = null,
    val weightWatcherSmartPoints: Int? = null,
    val dateCreated: Date = Date(),
)
