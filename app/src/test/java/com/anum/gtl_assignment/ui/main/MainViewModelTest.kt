package com.anum.gtl_assignment.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anum.gtl_assignment.api.RecipeService
import com.anum.gtl_assignment.data.db.RecipeDao
import com.anum.gtl_assignment.data.repository.recipe.RecipeRepository
import com.anum.gtl_assignment.utils.RecipesFake
import com.anum.gtl_assignment.utils.Resource
import com.anum.gtl_assignment.utils.Status
import com.anum.gtl_assignment.utils.getOrAwaitValue
import com.example.android.kotlincoroutines.main.utils.CoroutineScopeRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers


@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    val coroutineScope =  CoroutineScopeRule()
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var subject: MainViewModel
    private val recipeRepository: RecipeRepository = mockk(relaxed = true)

    @Before
    fun setup() {
        subject = MainViewModel(recipeRepository, ArgumentMatchers.anyString())
    }

    @Test
    fun `given success state, when search string is empty, should return data`() {

        coEvery { recipeRepository.getRecipes(any(), any()) } returns
                Resource(Status.SUCCESS, RecipesFake.getRecipes(), null)

        runBlockingTest {
            subject.searchRecipe("")
        }

        val value = subject.recipeListObservable().getOrAwaitValue()

        assertEquals(value.status, Status.SUCCESS)
        assertNull(value.message)
        assertNotNull(value.data?.results)
        assertEquals(value.data?.results?.size, 3)
    }

    @Test
    fun `given success state, when search string is not empty, should return data`() {

        coEvery { recipeRepository.getRecipes(any(), any()) } returns
                Resource(Status.SUCCESS, RecipesFake.getRecipes(), null)

        runBlockingTest {
            subject.searchRecipe("spicy")
        }

        val value = subject.recipeListObservable().getOrAwaitValue()

        assertEquals(value.status, Status.SUCCESS)
        assertNull(value.message)
        assertNotNull(value.data?.results)
        assertEquals(value.data?.results?.size, 3)
    }

    @Test
    fun `given success state, when search string is garbage, should return empty list`() {

        coEvery { recipeRepository.getRecipes(any(), any()) } returns
                Resource(Status.SUCCESS, RecipesFake.getEmptyListOfRecipes(), null)

        runBlockingTest {
            subject.searchRecipe("1234")
        }

        val value = subject.recipeListObservable().getOrAwaitValue()

        assertEquals(value.status, Status.SUCCESS)
        assertNull(value.message)
        assertNotNull(value.data?.results)
        assertEquals(value.data?.results?.size, 0)
    }

    @Test
    fun `given error state, when search string is empty, should return empty list`() {

        coEvery { recipeRepository.getRecipes(any(), any()) } returns
                Resource(Status.ERROR, null, "An error occured")

        runBlockingTest {
            subject.searchRecipe("")
        }

        val value = subject.recipeListObservable().getOrAwaitValue()

        assertEquals(value.status, Status.ERROR)
        assertNotNull(value.message)
        assertEquals(value.message, "An error occured")
        assertNull(value.data?.results)
    }
}