package com.anum.gtl_assignment.di

import android.content.Context
import com.anum.gtl_assignment.api.RecipeService
import com.anum.gtl_assignment.data.db.RecipeDao
import com.anum.gtl_assignment.data.repository.recipe.RecipeRepository
import com.anum.gtl_assignment.data.repository.recipe.RecipeRepositoryImpl
import com.anum.gtl_assignment.data.repository.recipe.RecipeRepositoryMock
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides fun provideRecipeRepository(recipeService: RecipeService,
                                          @ApplicationContext context: Context,
                                          recipeDao: RecipeDao,
                                          gson: Gson): RecipeRepository =
        RecipeRepositoryImpl(recipeService, recipeDao)
//        RecipeRepositoryMock(context, gson)

    @ApiKey
    @Singleton
    @Provides fun provideAPIKey(): String = "fb3e49784d1e48c880e2b5ae82ea90a8"
}