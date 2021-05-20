package com.anum.gtl_assignment.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anum.gtl_assignment.data.model.RecipeInformation
import java.util.*

@Dao
interface RecipeDao {
    @Query("SELECT * FROM RecipeInformation WHERE id = :id")
    fun getRecipe(id: Long): RecipeInformation?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(recipe: RecipeInformation)
}