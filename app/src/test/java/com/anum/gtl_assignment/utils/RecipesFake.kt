package com.anum.gtl_assignment.utils

import com.anum.gtl_assignment.data.model.Recipe
import com.anum.gtl_assignment.data.model.Recipes

object RecipesFake {
    fun getRecipes(): Recipes =
        Recipes(listOf(
            Recipe(1, "Easy Homemade Rice and Beans", "easy-homemade-rice-and-beans-716627.jpg"),
            Recipe(2, "Spicy Black-Eyed Pea Curry with Swiss Chard and Roasted Eggplant",
                "spicy-black-eyed-pea-curry-with-swiss-chard-and-roasted-eggplant-798400.jpg"),
            Recipe(3, "Cheesy Chicken Enchilada Quinoa Casserole",
                "cheesy-chicken-enchilada-quinoa-casserole-715421.jpg")
        ))

    fun getEmptyListOfRecipes(): Recipes = Recipes(listOf())
}