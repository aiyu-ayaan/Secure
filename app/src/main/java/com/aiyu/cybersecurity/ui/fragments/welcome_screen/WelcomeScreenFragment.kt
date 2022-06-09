package com.aiyu.cybersecurity.ui.fragments.welcome_screen

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aiyu.cybersecurity.NavGraphDirections
import com.aiyu.cybersecurity.R
import com.aiyu.cybersecurity.databinding.FragmentWelcomeScreenBinding
import com.aiyu.cybersecurity.ui.fragments.welcome_screen.fragments.*
import com.aiyu.cybersecurity.util.FIRST_START
import com.aiyu.cybersecurity.util.handleCustomBackPressed
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeScreenFragment : Fragment(R.layout.fragment_welcome_screen) {

    private val binding: FragmentWelcomeScreenBinding by viewBinding()

    @Inject
    lateinit var pref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, /* forward= */ true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, /* forward= */ false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = arrayListOf<Fragment>(
            FragmentWelcomeScreen1(),
            FragmentWelcomeScreen2(),
            FragmentWelcomeScreen3(),
            FragmentWelcomeScreen4(),
            FragmentWelcomeScreen5(),
        )

        binding.apply {

            val pagerAdapter = WelcomeScreenViewPagerAdapter(
                fragmentList,
                childFragmentManager,
                lifecycle
            )
            binding.viewPagerWelcome.adapter = pagerAdapter
        }

        checkFirstStart()


        handleCustomBackPressed {
            if (binding.viewPagerWelcome.currentItem != 0) {
                binding.viewPagerWelcome.currentItem = binding.viewPagerWelcome.currentItem - 1
            } else {
                requireActivity().finishAffinity()
            }
        }
    }

    private fun checkFirstStart() {
        val isFirstStart = pref.getBoolean(FIRST_START, false)
        if (isFirstStart)
            navigateToHome()
    }

    private fun navigateToHome() {
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, /* forward= */ true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, /* forward= */ false)
        findNavController().navigate(NavGraphDirections.actionGlobalHomeFragment())
    }
}
