package com.kotlin.onboarding.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.kotlin.onboarding.R
import com.kotlin.onboarding.databinding.ActivityMainBinding
import com.kotlin.onboarding.helper.utils.GeneralUtils.hideStatusBar
import com.kotlin.onboarding.helper.utils.GeneralUtils.showStatusBar

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var navController: NavController

    private fun initializations() {
        navController = (supportFragmentManager.findFragmentById(binding.fcvNavContainerMain.id) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializations()
        initNavListener()

    }

    private fun initNavListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragmentSplash, R.id.fragmentOnBoarding -> {
                    hideStatusBar()
                }
                else -> {
                    showStatusBar()
                }
            }
        }
    }
}