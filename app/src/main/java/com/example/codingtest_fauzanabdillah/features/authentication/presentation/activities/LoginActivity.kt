package com.example.codingtest_fauzanabdillah.features.authentication.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.widget.addTextChangedListener
import com.example.codingtest_fauzanabdillah.R
import com.example.codingtest_fauzanabdillah.core.base.BaseActivity
import com.example.codingtest_fauzanabdillah.databinding.ActivityLoginBinding
import com.example.codingtest_fauzanabdillah.features.home.presentation.activities.DashboardActivity

class LoginActivity : BaseActivity() {
    companion object {
        fun getStartIntent(context: Context): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            return intent
        }
    }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applyWindowInsetsTo(binding.root)

        setupValidation()
        setupButton()
    }

    private fun setupValidation() {
        binding.etUsername.addTextChangedListener { value ->
            binding.tvUsernameError.visibility =
                if (value.toString().isEmpty()) View.VISIBLE else View.GONE
            validateForm()
        }
        binding.etPassword.addTextChangedListener { value ->
            binding.tvPasswordError.visibility =
                if (value.toString().isEmpty()) View.VISIBLE else View.GONE
            validateForm()
        }
    }

    private fun validateForm() {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()

        binding.btnLogin.isEnabled = username.isNotBlank() && password.isNotBlank()
    }

    private fun setupButton() {
        binding.btnLogin.setOnClickListener {
            goToDashboard()
        }
    }

    private fun goToDashboard() {
        Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show()

        val username = binding.etUsername.text.toString()
        val intent = DashboardActivity.getStartIntent(this, username)
        startActivity(intent)
        finish()
    }
}