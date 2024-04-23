package com.amarant.apps.javainterview.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.amarant.apps.javainterview.R
import com.amarant.apps.javainterview.databinding.FragmentCategoryBinding
import com.amarant.apps.javainterview.databinding.FragmentQuestionBinding
import com.amarant.apps.javainterview.domain.Question

class QuestionFragment : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding: FragmentQuestionBinding
        get() = _binding ?: throw RuntimeException("FragmentQuestionBinding == null")

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelFactory(JavaInterviewApp.instance)
        )[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActionBar()
    }

    private fun initActionBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {

        private const val KEY_CATEGORY_ID = "category_id"

        fun newInstance(categoryId: Int): QuestionFragment {
            return QuestionFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_CATEGORY_ID, categoryId)
                }
            }
        }
    }
}