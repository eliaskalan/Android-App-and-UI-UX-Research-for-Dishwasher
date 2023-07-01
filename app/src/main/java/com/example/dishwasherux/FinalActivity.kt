package com.example.dishwasherux

import TextToSpeechManager
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

import com.example.dishwasherux.databinding.ActivityFinalBinding

class FinalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinalBinding
    private lateinit var textToSpeechManager: TextToSpeechManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        textToSpeechManager = TextToSpeechManager(this)
        Handler(Looper.getMainLooper()).postDelayed({
            textToSpeechManager.speak("Το πλήσιμο τελείωσε");
        }, 1000)

        // Set click listener for Button 10
        binding.button10.setOnClickListener {
            // Start MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.button3.setOnClickListener {
            // Start MainActivity
            val intent = Intent(this, SleepAction::class.java)
            startActivity(intent)
        }
    }
}
