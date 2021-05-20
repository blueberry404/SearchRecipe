package com.anum.gtl_assignment.ui.recipeDetails

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anum.gtl_assignment.databinding.ActivityRecipeDetailBinding
import com.anum.gtl_assignment.ui.recipeDetails.ui.main.IngredientsFragment
import com.anum.gtl_assignment.ui.recipeDetails.ui.main.InstructionsFragment
import com.anum.gtl_assignment.ui.recipeDetails.ui.main.SectionsPagerAdapter
import com.anum.gtl_assignment.ui.recipeDetails.ui.main.SummaryFragment
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailBinding
    private val viewModel: RecipeDetailViewModel by viewModels()
    private var recipeID = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        fetchDetails()
    }

    private fun setupUI() {
        //TODO: To be injected via Hilt
        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                supportFragmentManager, lifecycle,
                listOf(
                    SummaryFragment.newInstance(),
                    InstructionsFragment.newInstance(),
                    IngredientsFragment.newInstance()
                )
            )
        binding.viewPager.adapter = sectionsPagerAdapter

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                binding.viewPager.currentItem = binding.tabs.selectedTabPosition
            }
        })
    }

    private fun fetchDetails() {
        recipeID = intent?.getLongExtra("recipeID", 0)?: kotlin.run { 0L }
        viewModel.fetchRecipe(recipeID)
    }
}