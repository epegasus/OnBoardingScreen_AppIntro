package com.kotlin.onboarding.ui.fragments.intro

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.kotlin.onboarding.R
import com.kotlin.onboarding.databinding.FragmentOnBoardingBinding
import com.kotlin.onboarding.helper.adapters.viewPager.OnBoardViewPagerAdapter
import com.kotlin.onboarding.helper.adapters.viewPager.OnBoardViewPagerAdapter.Companion.TOTAL_PAGES
import com.kotlin.onboarding.helper.data_classes.WalkThrough
import com.kotlin.onboarding.helper.data_provider.DPWalkThrough
import com.kotlin.onboarding.helper.utils.GeneralUtils.isCurrentDestination
import java.util.*

class FragmentOnBoarding : Fragment() {

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!

    private lateinit var globalContext: Context
    private lateinit var viewPagerAdapter: OnBoardViewPagerAdapter
    private lateinit var walkThroughList: ArrayList<WalkThrough>
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    private val dots by lazy {
        arrayOfNulls<ImageView>(TOTAL_PAGES)
    }

    private fun initializations(view: View) {
        globalContext = view.context
        viewPagerAdapter = OnBoardViewPagerAdapter(this)
        walkThroughList = DPWalkThrough.getWalkThroughList(globalContext)
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable { changePage() }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializations(view)
        setUI()
        initViewPagerListener()

        binding.mbSkipOnBoarding.setOnClickListener { onSkipClick() }
        binding.mbContinueOnBoarding.setOnClickListener { onNextClick() }
    }

    private fun setUI() {
        binding.vpContainerOnBoarding.adapter = viewPagerAdapter
        for (i in 0 until TOTAL_PAGES) {
            dots[i] = ImageView(globalContext)
            dots[i]?.setImageDrawable(ContextCompat.getDrawable(globalContext, R.drawable.dot_inactive))
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(8, 0, 8, 0)
            binding.llDotsOnBoarding.addView(dots[i], params)
        }
        selectDotAndStyle(0)
    }

    fun selectDotAndStyle(position: Int) {
        var selectedDrawable = R.drawable.dot_active_one
        when (position) {
            0 -> {
                selectedDrawable = R.drawable.dot_active_one
            }
            1 -> {
                selectedDrawable = R.drawable.dot_active_two
            }
            2 -> {
                selectedDrawable = R.drawable.dot_active_three
            }
        }
        for (i in 0 until TOTAL_PAGES) {
            val drawableId: Int = if (i == position) selectedDrawable else R.drawable.dot_inactive
            dots[i]?.setImageResource(drawableId)
        }
        if ((position + 1) < TOTAL_PAGES) {
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 2000)
        }
    }

    private fun initViewPagerListener() {
        binding.vpContainerOnBoarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tvCurrentNumberOnBoarding.text = String.format(Locale.getDefault(), globalContext.resources.getString(R.string.current_page), position + 1)
                updateUI(position)
                selectDotAndStyle(position)
            }
        })
    }

    private fun updateUI(position: Int) {
        binding.mbContinueOnBoarding.setBackgroundColor(walkThroughList[position].colorId)
        when (position) {
            2 -> {
                binding.mbSkipOnBoarding.visibility = View.GONE
                binding.mbContinueOnBoarding.text = globalContext.resources.getString(R.string.get_started)
            }
            else -> {
                binding.mbSkipOnBoarding.visibility = View.VISIBLE
                binding.mbContinueOnBoarding.text = globalContext.resources.getString(R.string.next)
            }
        }
    }

    private fun changePage() {
        if (isAdded) {
            binding.vpContainerOnBoarding.setCurrentItem(binding.vpContainerOnBoarding.currentItem + 1, true)
        }
    }

    private fun onSkipClick() {
        binding.vpContainerOnBoarding.setCurrentItem(TOTAL_PAGES - 1, true)
    }

    private fun onNextClick() {
        if (binding.vpContainerOnBoarding.currentItem >= (TOTAL_PAGES - 1)) {
            navigateToHomeScreen()
            return
        }
        changePage()
    }

    private fun navigateToHomeScreen() {
        if (isCurrentDestination(R.id.fragmentOnBoarding))
            findNavController().navigate(R.id.action_fragmentOnBoarding_to_fragmentHome)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}