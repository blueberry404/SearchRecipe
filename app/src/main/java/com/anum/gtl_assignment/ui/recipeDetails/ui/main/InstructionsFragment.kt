package com.anum.gtl_assignment.ui.recipeDetails.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.anum.gtl_assignment.databinding.FragmentInstructionsBinding
import com.anum.gtl_assignment.databinding.FragmentSummaryBinding
import com.anum.gtl_assignment.ui.recipeDetails.RecipeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InstructionsFragment : Fragment() {

    private val recipeDetailViewModel: RecipeDetailViewModel by activityViewModels()
    private var binding: FragmentInstructionsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInstructionsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun populateData() {
        recipeDetailViewModel.recipeInstructions.observe(viewLifecycleOwner, Observer<String> {
            binding?.txtSummary?.text = it
        })
    }


    companion object {

        @JvmStatic
        fun newInstance(): InstructionsFragment {
            return InstructionsFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }
}