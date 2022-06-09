package com.aiyu.cybersecurity.util

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


const val SHARED_PREFERENCES_NAME ="Secure_pref"
const val FIRST_START ="first_start"


fun Activity.changeStatusBarToolbarColor(@ColorRes colorCode: Int) =
    this.apply {
        try {
            val window = window
            window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window?.statusBarColor = ContextCompat.getColor(this, colorCode)
//            this.?.setBackgroundColor(ContextCompat.getColor(this, colorCode))
        } catch (e: Exception) {
            Toast.makeText(this, "${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

fun Context.isDark() =
    resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

fun Fragment.handleCustomBackPressed(
    onBackPressed: OnBackPressedCallback.() -> Unit
) {
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
        onBackPressed()
    }
}
