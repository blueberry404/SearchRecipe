package com.anum.gtl_assignment.di

import com.anum.gtl_assignment.api.RecipeService
import com.anum.gtl_assignment.data.repository.recipe.RecipeRepository
import com.anum.gtl_assignment.data.repository.recipe.RecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides fun provideRecipeRepository(recipeService: RecipeService): RecipeRepository =
        RecipeRepositoryImpl(recipeService)

    @ApiKey
    @Singleton
    @Provides fun provideAPIKey(): String = "fb3e49784d1e48c880e2b5ae82ea90a8"
}