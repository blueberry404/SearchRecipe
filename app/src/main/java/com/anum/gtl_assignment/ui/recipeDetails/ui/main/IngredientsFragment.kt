package com.anum.gtl_assignment.ui.recipeDetails.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.anum.gtl_assignment.databinding.FragmentIngredientsBinding
import com.anum.gtl_assignment.databinding.FragmentSummaryBinding
import com.anum.gtl_assignment.ui.recipeDetails.RecipeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IngredientsFragment : Fragment() {

    private val recipeDetailViewModel: RecipeDetailViewModel by activityViewModels()
    private var binding: FragmentIngredientsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(): IngredientsFragment {
            return IngredientsFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }
}