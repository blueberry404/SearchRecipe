package com.anum.gtl_assignment.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.anum.gtl_assignment.data.model.Recipe
import com.anum.gtl_assignment.databinding.ActivityMainBinding
import com.anum.gtl_assignment.utils.Status
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
    }

    private fun setupViewModel() {
        mainViewModel.recipeListObservable().observeOnce(this) {
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
                }
            }
        }
    }

    private fun handleOnItemClick(recipe: Recipe) {

    }
}