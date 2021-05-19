package com.anum.gtl_assignment.ui.recipeDetails.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anum.gtl_assignment.R

class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_recipe_detail, container, false)
//        val textView: TextView = root.findViewById(R.id.section_label)
//        pageViewModel.text.observe(viewLifecycleOwner, Observer<String> {
//            textView.text = it
//        })
        return root
    }

    companion object {

        @JvmStatic
        fun newInstance(): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }
}