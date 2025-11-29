package com.example.planpal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.planpal.databinding.ActivityMain2Binding
class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityMain2Binding.inflate(layoutInflater)
        // Set the content view to be the root of the binding
        setContentView(binding.root)

        // Set up button click listeners
        binding.logInButton.setOnClickListener {
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
        }

        binding.signInButton.setOnClickListener {
            // TODO: Start your SignInActivity
            // val intent = Intent(this, SignInActivity::class.java)
            // startActivity(intent)
        }
    }
}
