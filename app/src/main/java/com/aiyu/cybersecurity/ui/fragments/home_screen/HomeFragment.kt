package com.aiyu.cybersecurity.ui.fragments.home_screen

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aiyu.cybersecurity.NavGraphDirections
import com.aiyu.cybersecurity.R
import com.aiyu.cybersecurity.databinding.FragmentHomeBinding
import com.aiyu.cybersecurity.util.FIRST_START
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding: FragmentHomeBinding by viewBinding()

    @Inject
    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, /* forward= */ true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, /* forward= */ false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonReset.setOnClickListener {
                resetFirstStart()
                navigateToWelcomeFragment()
            }
        }
    }

    private fun navigateToWelcomeFragment() {
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, /* forward= */ true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, /* forward= */ false)
        findNavController().navigate(NavGraphDirections.actionGlobalWelcomeScreenFragment())
    }

    private fun resetFirstStart() {
        pref.edit()
            .putBoolean(FIRST_START, false)
            .commit()
    }
}