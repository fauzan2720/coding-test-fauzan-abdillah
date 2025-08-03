package com.example.codingtest_fauzanabdillah.core.base

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.codingtest_fauzanabdillah.features.authentication.presentation.activities.LoginActivity

abstract class BaseActivity : AppCompatActivity() {

    private val inactivityTimeout = 180000L
    private val handler = Handler(Looper.getMainLooper())
    private val inactivityRunnable = Runnable {
        onUserInactivityDetected()
    }

    fun applyWindowInsetsTo(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    open fun onUserInactivityDetected() {
        // pengecualian untuk halm login
        if (this !is LoginActivity) {
            navigateToLogin()
        }
    }

    private fun resetInactivityTimer() {
        handler.removeCallbacks(inactivityRunnable)
        handler.postDelayed(inactivityRunnable, inactivityTimeout)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        resetInactivityTimer()
        return super.dispatchTouchEvent(ev)
    }

    override fun onResume() {
        super.onResume()
        resetInactivityTimer()
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(inactivityRunnable)
    }

    open fun navigateToLogin() {
        val intent = LoginActivity.getStartIntent(this)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
