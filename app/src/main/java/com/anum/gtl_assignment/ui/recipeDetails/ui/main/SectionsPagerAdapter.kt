package com.anum.gtl_assignment.ui.recipeDetails.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

//TODO: To be injected via Hilt
class SectionsPagerAdapter(fm: FragmentManager,
                           lifecycle: Lifecycle, private val fragmentsList: List<Fragment>)
    : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = fragmentsList.size

    override fun createFragment(position: Int): Fragment = fragmentsList[position]
}