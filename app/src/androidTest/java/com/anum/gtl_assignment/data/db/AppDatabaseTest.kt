package com.anum.gtl_assignment.data.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anum.gtl_assignment.data.model.Recipe
import com.anum.gtl_assignment.data.model.RecipeInformation
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.Throws

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest : TestCase() {

    private lateinit var appDatabase: AppDatabase
    private lateinit var recipeDao: RecipeDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        recipeDao = appDatabase.recipeDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        appDatabase.close()
    }

    @Test
    fun writeAndReadRecipe() = runBlocking {
        val recipe = RecipeInformation(
            id = 1,
            title = "Easy Homemade Rice and Beans",
            image = "easy-homemade-rice-and-beans-716627.jpg",
            summary = "Summary"
        )
        recipeDao.save(recipe)
        assertEquals(recipeDao.getRecipe(1), recipe)
    }
}