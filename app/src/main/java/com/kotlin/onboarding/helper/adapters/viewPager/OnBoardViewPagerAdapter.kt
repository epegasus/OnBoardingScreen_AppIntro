package com.kotlin.onboarding.helper.adapters.viewPager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kotlin.onboarding.ui.fragments.intro.FragmentWalkThrough

class OnBoardViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    companion object {
        const val TOTAL_PAGES = 3
    }

    override fun getItemCount(): Int {
        return TOTAL_PAGES
    }

    override fun createFragment(position: Int): Fragment {
        return FragmentWalkThrough(position)
    }

}