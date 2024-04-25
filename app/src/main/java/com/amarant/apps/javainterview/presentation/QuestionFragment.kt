package com.amarant.apps.javainterview.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
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

    private lateinit var adapter: QuestionAdapter

    private var categoryId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryId = requireArguments().getInt(KEY_CATEGORY_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionBinding.inflate(inflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActionBar()
        initRecyclerView()
        observeViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_questions, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initActionBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initRecyclerView() {
        adapter = QuestionAdapter()
        binding.rwQuestions.adapter = adapter
        adapter.onQuestionCheckedListener = {
            mainViewModel.setQuestionAnswered(it.copy(
                isAnswered = !it.isAnswered
            ))
        }
    }

    private fun observeViewModel() {
        mainViewModel.getQuestionsByCategory(categoryId).observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
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