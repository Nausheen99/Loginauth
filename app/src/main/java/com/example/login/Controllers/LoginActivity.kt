package com.example.login.Controllers

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login.R
import com.example.login.support.AuthService
import com.example.login.support.AuthService.isLoggedIn
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun errorToast(text: String) {
        Toast.makeText(
            this, text,
            Toast.LENGTH_LONG
        ).show()
    }

    fun loginBtnClick(view: View){
        val email = loginEmail.text.toString()
        val password = loginPassword.text.toString()
        println(isLoggedIn)
        if (email.isNotEmpty() && password.isNotEmpty()) {
            AuthService.loginUser(this, email, password) { loginSuccess ->
                if (loginSuccess) {
                    println("logggggggin $isLoggedIn")

                    AuthService.findUserByMail(this) { findSuccess ->
                        if (findSuccess) {
                            val toBaseIntent = Intent(this, BaseActivity::class.java)
                            startActivity(toBaseIntent)
                            println(isLoggedIn)

                            //finish()
                        } else { errorToast("something went wrong | finduser") }
                    }
                } else { errorToast("something went wrong | login") }
            }
        } else { errorToast("Please enter all credentials")}
    }
}
