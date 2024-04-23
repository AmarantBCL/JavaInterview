package com.amarant.apps.javainterview.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amarant.apps.javainterview.databinding.ListItemCategoryBinding
import com.amarant.apps.javainterview.domain.Category

class CategoryAdapter :
    ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            ListItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = getItem(position)
        val context = holder.itemView.context
        val stringResId = context.resources.getIdentifier(item.title, "string", context.packageName)
        holder.binding.tvTitle.text = context.resources.getString(stringResId)
        holder.binding.tvProgress.text = "${item.numberOfQuestions}/${item.maxQuestions}" // TODO Correct number of questions
    }

    class CategoryViewHolder(val binding: ListItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
}