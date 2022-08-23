package com.example.login.Controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toLoginBtn.setOnClickListener{
            val toLoginIntent = Intent(this, LoginActivity::class.java)
            startActivity(toLoginIntent)
        }

        toSignupBtn.setOnClickListener{
            val toSignupIntent = Intent(this, SignupActivity::class.java)
            startActivity(toSignupIntent)
        }
    }


}