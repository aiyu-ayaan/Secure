package com.aiyu.cybersecurity.ui.fragments.welcome_screen.fragments

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.aiyu.cybersecurity.R
import com.aiyu.cybersecurity.databinding.FragmentWelcomeScreen4Binding
import com.aiyu.cybersecurity.util.changeStatusBarToolbarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentWelcomeScreen4 : Fragment(R.layout.fragment_welcome_screen_4) {
    private val binding: FragmentWelcomeScreen4Binding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonPrev.setOnClickListener {
                val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager_welcome)
                viewPager?.currentItem = 2
            }
            buttonNext.setOnClickListener {
                val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager_welcome)
                viewPager?.currentItem = 4
            }
        }

    }
}