package com.kotlin.onboarding.ui.fragments.intro

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.kotlin.onboarding.R
import com.kotlin.onboarding.databinding.FragmentWalkThroughBinding
import com.kotlin.onboarding.helper.data_provider.DPWalkThrough

class FragmentWalkThrough(private val position: Int) : Fragment() {

    private var _binding: FragmentWalkThroughBinding? = null
    private val binding get() = _binding!!
    private lateinit var globalContext: Context

    private fun initializations(view: View) {
        globalContext = view.context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_walk_through, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializations(view)
        setUI()
    }

    private fun setUI() {
        val walkThroughList = DPWalkThrough.getWalkThroughList(globalContext)
        binding.walkThroughList = walkThroughList
        binding.position = position
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}