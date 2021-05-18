package com.anum.gtl_assignment.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anum.gtl_assignment.R
import com.anum.gtl_assignment.data.model.Recipe

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var list: List<Recipe> = listOf()
    var onClick: ((recipe: Recipe) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val aView = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return MainViewHolder(aView, onClick)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    class MainViewHolder(itemView: View, onClick: ((recipe: Recipe) -> Unit)?): RecyclerView.ViewHolder(itemView) {

        private val txtRecipeName: TextView = itemView.findViewById(R.id.txtRecipeName)
        private lateinit var recipe: Recipe

        init {
            itemView.setOnClickListener { onClick?.invoke(recipe) }
        }

        fun bindData(rec: Recipe) {
            recipe = rec
            txtRecipeName.text = recipe.title
        }
    }
}