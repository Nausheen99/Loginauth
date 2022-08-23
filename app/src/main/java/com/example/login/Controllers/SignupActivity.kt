package com.example.login.Controllers

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.login.R
import com.example.login.support.AuthService
import com.example.login.support.UserDataService
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

    }

    fun errorToast(text: String) {
        Toast.makeText(
            this, text,
            Toast.LENGTH_LONG
        ).show()
    }


    fun signupBtnClick(view: View){
        val username = signupName.text.toString()
        val email = signupEmail.text.toString()
        val password = signupPassword.text.toString()

        if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            AuthService.registerUser(this, email, password) { registerSuccess ->
                if (registerSuccess) {
                    AuthService.loginUser(this, email, password) { loginSuccess ->
                        if (loginSuccess) {
                            AuthService.createUser(
                                this,
                                username,
                                email
                            ) { createSuccess ->
                                if (createSuccess) {

                                    /*val userDataChange = Intent(BROADCAST_USER_DATA_CHANGE)
                                    LocalBroadcastManager.getInstance(this)
                                        .sendBroadcast(userDataChange)


                                     */
                                    val toBaseIntent = Intent(this, BaseActivity::class.java)
                                    startActivity(toBaseIntent)

                                    println(UserDataService.name)
                                    println(UserDataService.avatarName)

                                    finish()
                                } else {
                                    errorToast("Something went wrong, please try again. | createfail")
                                }
                            }
                        } else {
                            errorToast("Something went wrong, please try again. | loginfail")
                        }
                    }
                } else {
                    errorToast("Something went wrong, please try again. | registerfail")
                }
            }
        } else {
            errorToast("Please enter all required fields.")

        }
    }
}