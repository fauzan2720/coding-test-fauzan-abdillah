package com.example.codingtest_fauzanabdillah

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.codingtest_fauzanabdillah.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
    }
}