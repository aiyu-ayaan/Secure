package com.aiyu.cybersecurity.ui.fragments.welcome_screen.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.aiyu.cybersecurity.NavGraphDirections
import com.aiyu.cybersecurity.R
import com.aiyu.cybersecurity.databinding.FragmentWelcomeScreen5Binding
import com.aiyu.cybersecurity.util.FIRST_START
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentWelcomeScreen5 : Fragment(R.layout.fragment_welcome_screen_5) {
    private val binding: FragmentWelcomeScreen5Binding by viewBinding()

    @Inject
    lateinit var pref: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonDone.setOnClickListener {
                setSharedPref()

            }
            buttonPrev.setOnClickListener {
                val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager_welcome)
                viewPager?.currentItem = 3
            }
        }
    }

    private fun navigateToHome() {
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, /* forward= */ true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, /* forward= */ false)
        findNavController().navigate(NavGraphDirections.actionGlobalHomeFragment())
    }

    private fun setSharedPref() {
        pref.edit()
            .putBoolean(FIRST_START, true)
            .commit()
        navigateToHome()
    }
}