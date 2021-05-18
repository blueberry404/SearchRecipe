package com.anum.gtl_assignment.data.model

data class Recipes(var results: List<Recipe>? = null)

data class Recipe(var id: Long = 0, var title: String = "N/A", var image: String? = null)

data class RecipeInformation(var details: String)