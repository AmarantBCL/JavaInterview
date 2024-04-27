package com.amarant.apps.javainterview.presentation

import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amarant.apps.javainterview.databinding.ListItemQuestionBinding
import com.amarant.apps.javainterview.domain.ProgressColor
import com.amarant.apps.javainterview.domain.Question

class QuestionAdapter :
    ListAdapter<Question, QuestionAdapter.QuestionViewHolder>(QuestionDiffCallback()) {

    var onQuestionCheckedListener: ((Question) -> Unit)? = null
    var onLongQuestionClickListener: ((Question) -> Unit)? = null

    private val itemStates = SparseBooleanArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding =
            ListItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val item = getItem(position)
        val context = holder.itemView.context
        val headerString = context.resources.getIdentifier(item.header, "string", context.packageName)
        val questionString = context.resources.getIdentifier(item.description, "string", context.packageName)
        val answerString = context.resources.getIdentifier(item.answer, "string", context.packageName)
        holder.binding.tvTitle.text = context.resources.getString(headerString)
        holder.binding.tvTitle.setBackgroundColor(setBarColor(context, item.progressColor))
        holder.binding.tvQuestion.text = context.resources.getString(questionString)
        holder.binding.tvAnswer.text = context.resources.getString(answerString)
        holder.binding.tvAnswer.visibility = setItemState(position)
        holder.binding.cboxLearned.isChecked = item.isAnswered
        holder.binding.constraint.setOnClickListener {
            val newState = !itemStates.get(position, false)
            itemStates.put(position, newState)
            holder.binding.tvAnswer.visibility = setItemState(position)
        }
        holder.binding.cboxLearned.setOnClickListener {
            onQuestionCheckedListener?.invoke(item)
        }
        holder.binding.tvTitle.setOnLongClickListener {
            onLongQuestionClickListener?.invoke(item)
            true
        }
    }

    private fun setItemState(position: Int): Int {
        return if (itemStates.get(position, false)) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun setBarColor(context: Context, progressColor: ProgressColor): Int {
        return when(progressColor) {
            ProgressColor.EASY -> ContextCompat.getColor(context, android.R.color.holo_green_light)
            ProgressColor.MEDIUM -> ContextCompat.getColor(context, android.R.color.holo_orange_light)
            ProgressColor.DIFFICULT -> ContextCompat.getColor(context, android.R.color.holo_red_light)
        }
    }

    class QuestionViewHolder(val binding: ListItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.root)

    class QuestionDiffCallback : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem == newItem
        }
    }
}