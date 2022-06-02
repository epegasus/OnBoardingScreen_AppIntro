package com.kotlin.onboarding.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kotlin.onboarding.R
import com.kotlin.onboarding.databinding.FragmentSplashBinding
import com.kotlin.onboarding.helper.utils.GeneralUtils.isCurrentDestination
import com.kotlin.onboarding.helper.utils.GeneralUtils.withDelay

class FragmentSplash : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        withDelay(1000) { navigateScreen() }
    }

    private fun navigateScreen() {
        if (isCurrentDestination(R.id.fragmentSplash))
            findNavController().navigate(R.id.action_fragmentSplash_to_fragmentOnBoarding)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}