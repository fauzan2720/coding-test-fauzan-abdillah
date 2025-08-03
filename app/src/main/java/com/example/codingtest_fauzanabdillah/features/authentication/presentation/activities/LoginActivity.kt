package com.example.codingtest_fauzanabdillah.features.authentication.presentation.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.codingtest_fauzanabdillah.R
import com.example.codingtest_fauzanabdillah.databinding.ActivityLoginBinding
import com.example.codingtest_fauzanabdillah.features.home.presentation.activities.DashboardActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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
    }
}