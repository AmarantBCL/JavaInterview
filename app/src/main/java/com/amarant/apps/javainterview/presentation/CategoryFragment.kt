package com.amarant.apps.javainterview.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.amarant.apps.javainterview.R
import com.amarant.apps.javainterview.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding: FragmentCategoryBinding
        get() = _binding ?: throw RuntimeException("FragmentCategoryBinding == null")

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelFactory(JavaInterviewApp.instance)
        )[MainViewModel::class.java]
    }

    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActionBar()
        initRecyclerView()
        observeViewModel()
    }

    private fun initActionBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun initRecyclerView() {
        adapter = CategoryAdapter()
        binding.rwCategories.adapter = adapter
        adapter.onCategoryClickListener = {
            navigateToQuestionFragment(it.id)
        }
    }

    private fun observeViewModel() {
        mainViewModel.getAllCategories().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun navigateToQuestionFragment(id: Int) {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.slide_out,
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
            .replace(R.id.container, QuestionFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
    }
}