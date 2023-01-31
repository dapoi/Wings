package com.dapascript.wings.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dapascript.wings.databinding.ActivityLoginBinding
import com.dapascript.wings.presentation.ui.DataPreferenceViewModel
import com.dapascript.wings.presentation.ui.product.WingsProductActivity
import com.dapascript.wings.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val loginViewModel: LoginViewModel by viewModels()
    private val dataPrefViewModel: DataPreferenceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            btnLogin.setOnClickListener {
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()

                when {
                    username.isEmpty() -> {
                        tilUsername.error = "Username is required"
                    }
                    password.isEmpty() -> {
                        tilPassword.error = "Password is required"
                    }
                    else -> {
                        tilUsername.error = null
                        tilPassword.error = null
                        userLogin(username, password)
                    }
                }
            }
        }
    }

    private fun userLogin(username: String, password: String) {
        loginViewModel.login(username, password).observe(this) { response ->
            when (response) {
                is Resource.Loading -> stateLoading(true)
                is Resource.Success -> {
                    stateLoading(false)
                    dataPrefViewModel.saveHasLogin(true)
                    startActivity(Intent(this@LoginActivity, WingsProductActivity::class.java))
                    finish()
                }
                is Resource.Error -> {
                    stateLoading(false)
                    Toast.makeText(
                        this@LoginActivity,
                        response.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun stateLoading(state: Boolean) {
        binding.apply {
            if (state) {
                progressBar.visibility = View.VISIBLE
                btnLoginText.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                btnLoginText.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        dataPrefViewModel.hasLogin.observe(this) { hasLogin ->
            if (hasLogin) {
                startActivity(Intent(this@LoginActivity, WingsProductActivity::class.java))
                finish()
            }
        }
    }
}