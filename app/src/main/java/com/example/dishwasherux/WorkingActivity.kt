package com.example.dishwasherux

import TextToSpeechManager
import android.content.Intent

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class WorkingActivity : AppCompatActivity() {

    private lateinit var timerHourTextView: TextView
    private lateinit var timerSecondTextView: TextView
    private lateinit var startButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var imageView: ImageView

    private lateinit var countDownTimer: CountDownTimer
    private lateinit var textToSpeechManager: TextToSpeechManager
    private var isTimerRunning = false
    private var initialTime: Long = 0
    private var timeRemaining: Long = 0 // Time remaining in milliseconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_working)
        textToSpeechManager = TextToSpeechManager(this)
        timerHourTextView = findViewById(R.id.textView6)
        timerSecondTextView = findViewById(R.id.textViewSec)
        startButton = findViewById(R.id.button)
        progressBar = findViewById(R.id.progressBar)
        imageView = findViewById(R.id.imageView)

        val demoHourTime = intent.getStringExtra("demo_hour_time")?.toInt()
        val demoSecondTime = intent.getStringExtra("demo_second_time")?.toInt()

        Handler(Looper.getMainLooper()).postDelayed({
            textToSpeechManager.speak("Το πλήσιμο ξεκίνησε");
        }, 1000)

        if (demoHourTime != null && demoSecondTime != null) {
            initialTime = ((demoHourTime * 60 ) + (demoSecondTime )) * 1000L
        } else {
            // Handle the case when the intent extras don't contain valid integer values
            // You can display an error message or use default values in such cases
            // For example, setting initialTime to a default value of 1 minute:
            initialTime = 2 * 60 * 1000L
        }

        startButton.setOnClickListener {
            if (isTimerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }

        // Start the timer automatically when the activity is created
        startTimer()
    }


    private fun startTimer() {
        countDownTimer = object : CountDownTimer(initialTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeRemaining = millisUntilFinished
                updateTimer()
                updateProgressBar()

                val totalThird = initialTime / 3
                val timeElapsed = initialTime - millisUntilFinished

                val imageResId = when {
                    timeElapsed >= totalThird * 2 -> R.drawable.dry
                    timeElapsed >= totalThird -> R.drawable.plate
                    else -> R.drawable.sink
                }

                imageView.setImageResource(imageResId)
                imageView.visibility = ImageView.VISIBLE
            }

            override fun onFinish() {

                isTimerRunning = false
                startButton.text = "Start"
                timerHourTextView.text = "00"
                timerSecondTextView.text = "00"
                progressBar.progress = 0
                imageView.visibility = ImageView.GONE
                pauseTimer()

            }
        }

        countDownTimer.start()


        isTimerRunning = true
        startButton.text = "Pause"



    }

    private fun pauseTimer() {
        val intent = Intent(this,FinalActivity::class.java)
        startActivity(intent)
    }

    private fun updateTimer() {
        val minutes = (timeRemaining / 1000) / 60
        val seconds = (timeRemaining / 1000) % 60
        val timeSecondLeftFormatted = String.format("%02d", seconds)
        val timeHourLeftFormatted = String.format("%02d", minutes)
        timerHourTextView.text = timeHourLeftFormatted
        timerSecondTextView.text = timeSecondLeftFormatted
    }

    private fun updateProgressBar() {
        val progress = ((initialTime - timeRemaining) * 100 / initialTime).toInt()
        progressBar.progress = progress
    }
}
