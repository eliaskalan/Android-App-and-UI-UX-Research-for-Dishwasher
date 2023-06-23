package com.example.dishwasherux

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.dishwasherux.databinding.ActivityFinalBinding

class FinalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listener for Button 10
        binding.button10.setOnClickListener {
            // Start MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
