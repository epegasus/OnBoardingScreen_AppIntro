package com.kotlin.onboarding.helper.data_provider

import android.content.Context
import androidx.core.content.ContextCompat
import com.kotlin.onboarding.R
import com.kotlin.onboarding.helper.data_classes.WalkThrough

object DPWalkThrough {

    fun getWalkThroughList(context: Context): ArrayList<WalkThrough> {
        val resources = context.resources
        val walkThroughList = ArrayList<WalkThrough>()
        walkThroughList.add(WalkThrough(R.drawable.dummy, resources.getString(R.string.walk_through_title_1), resources.getString(R.string.walk_through_body_1), ContextCompat.getColor(context, R.color.onboarding_color_1)))
        walkThroughList.add(WalkThrough(R.drawable.dummy, resources.getString(R.string.walk_through_title_2), resources.getString(R.string.walk_through_body_2), ContextCompat.getColor(context, R.color.onboarding_color_2)))
        walkThroughList.add(WalkThrough(R.drawable.dummy, resources.getString(R.string.walk_through_title_3), resources.getString(R.string.walk_through_body_3), ContextCompat.getColor(context, R.color.onboarding_color_3)))
        return walkThroughList
    }
}