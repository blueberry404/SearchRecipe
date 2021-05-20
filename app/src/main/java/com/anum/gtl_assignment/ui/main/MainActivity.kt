package com.anum.gtl_assignment.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.anum.gtl_assignment.data.model.Recipe
import com.anum.gtl_assignment.databinding.ActivityMainBinding
import com.anum.gtl_assignment.ui.recipeDetails.RecipeDetailActivity
import com.anum.gtl_assignment.utils.Status
import com.anum.gtl_assignment.utils.hideKeyboard
import com.anum.gtl_assignment.utils.observeOnce
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModels()
    @Inject lateinit var adapter: MainAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupViewModel()
    }

    private fun setupUI() {
        binding.recyclerRecipes.addItemDecoration(DividerItemDecoration(this,
            DividerItemDecoration.VERTICAL))
        binding.recyclerRecipes.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerRecipes.adapter = adapter
        adapter.onClick = {
            handleOnItemClick(it)
        }

        binding.btnSearch.setOnClickListener {
            hideKeyboard(it)
            mainViewModel.searchRecipe(binding.editSearch.text.toString())
        }
    }

    private fun setupViewModel() {
        mainViewModel.recipeListObservable().observe(this, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.results?.let { data -> adapter.addData(data) }
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun handleOnItemClick(recipe: Recipe) {
        Intent(this, RecipeDetailActivity::class.java).apply {
            putExtra("recipeID", recipe.id)
            startActivity(this)
        }
    }
}