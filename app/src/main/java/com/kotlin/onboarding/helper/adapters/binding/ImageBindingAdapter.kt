package com.kotlin.onboarding.helper.adapters.binding

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("imageId")
fun ShapeableImageView.setImageFromDrawable(imageId: Int) {
    Glide.with(this).load(imageId).into(this)
}