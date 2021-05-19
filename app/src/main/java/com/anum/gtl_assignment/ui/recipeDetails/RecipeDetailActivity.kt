package com.anum.gtl_assignment.ui.recipeDetails

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.anum.gtl_assignment.R
import com.anum.gtl_assignment.databinding.ActivityRecipeDetailBinding
import com.anum.gtl_assignment.ui.recipeDetails.ui.main.PlaceholderFragment
import com.anum.gtl_assignment.ui.recipeDetails.ui.main.SectionsPagerAdapter

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailBinding
    private var recipeID = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        fetchDetails()
    }

    private fun setupUI() {
        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, lifecycle,
            listOf(
                PlaceholderFragment.newInstance(),
                PlaceholderFragment.newInstance(),
                PlaceholderFragment.newInstance()
            ))
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
    }
}