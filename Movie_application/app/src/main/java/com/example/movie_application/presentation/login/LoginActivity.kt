package com.example.movie_application.presentation.login


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.movie_application.R
import com.example.movie_application.presentation.MainActivity
import com.example.movie_application.utils.AppPreferences
//import com.example.movieapp.R
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin.setOnClickListener{
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()
            loginViewModel.login(
                "Zholdibay", "1999"
            )
//            if (username != "" && password != "") {
//                loginViewModel.login(
//                    username, password
//                )
//            } else {
//                Toast.makeText(this, "Enter username and password!", Toast.LENGTH_SHORT).show()
//            }
        }
        setData()
    }
    private fun setData() {
        loginViewModel.liveData.observe(this, Observer { state ->
            when(state) {
                is LoginViewModel.State.ShowLoading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is LoginViewModel.State.HideLoading -> {
                    progressBar.visibility = View.INVISIBLE
                }
                is LoginViewModel.State.ApiResult -> {
                    if (state.success && state.session_id != "") {
                        val intent = Intent(this, MainActivity::class.java)
                        AppPreferences.setAccountId(this, state.account_id)
                        AppPreferences.setSessionId(this, state.session_id)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Invalid username, password credentials", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}