package com.aiyu.cybersecurity.ui.main_activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.viewbinding.library.activity.viewBinding
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.aiyu.cybersecurity.R
import com.aiyu.cybersecurity.databinding.ActivityMainBinding
import com.aiyu.cybersecurity.util.isDark
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            setSupportActionBar(binding.toolbar)
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.homeFragment,
                    R.id.welcomeScreenFragment,
                    R.id.filterFragment
                )
            )
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            navController = navHostFragment.findNavController()
            binding.bottomNavigation.setupWithNavController(navController)
            setupActionBarWithNavController(navController, appBarConfiguration)
        }
        destinationChange()
    }

    private fun destinationChange() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.welcomeScreenFragment -> {
                    binding.toolbar.isVisible = false
                    binding.bottomLayout.isVisible = false
                    statusBarTransparent()
                }
                else -> {
                    binding.toolbar.isVisible = true
                    binding.bottomLayout.isVisible = true
                    statusBarDefault()
                }
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun statusBarDefault() {
        window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window?.statusBarColor = ContextCompat.getColor(this, R.color.background)
        lightStatusBar()
    }

    @Suppress("DEPRECATION")
    private fun statusBarTransparent() {
        window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window?.statusBarColor = Color.TRANSPARENT
        lightStatusBar(true)
    }

    private fun lightStatusBar(isDarkValue: Boolean? = null) {
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars =
            isDarkValue ?: !isDark()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}