package com.example.dishwasherux

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.ui.AppBarConfiguration
import com.example.dishwasherux.databinding.ActivityTimeBinding

class TimeActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityTimeBinding

    private var minutes = 0
    private var hours = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonDetailsBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val selectionId = intent.getStringExtra("id");
        for (selection in Selections) {
            if (selection.id == selectionId) {
                val colorInt = Color.parseColor(selection.color)
                val imgContainer = findViewById<RelativeLayout>(R.id.image_container)
                imgContainer.setBackgroundColor(colorInt)
                val txtContainer = findViewById<TextView>(R.id.text_view_soft)
                txtContainer.text = selection.title + "\n" + selection.temperature + "°C"
                val imgView = findViewById<ImageView>(R.id.image_view)
                val customImg = resources.getDrawable(selection.imagePath, theme)
                imgView.setImageDrawable(customImg)
            }
        }

        binding.buttonMinutesUp.setOnClickListener {
            minutes += 30
            if (minutes >= 60) {
                hours += minutes / 60
                minutes %= 60
            }
            updateDisplay()
        }

        binding.buttonMinutesDown.setOnClickListener {
            if (minutes > 0) {
                minutes -= 30
                if (minutes < 0) {
                    hours--
                    minutes += 60
                }
            } else if (hours > 0) {
                hours--
                minutes = 30
            }
            updateDisplay()
        }

        binding.buttonHoursUp.setOnClickListener {
            hours++
            if (hours >= 24) {
                hours = 0
            }
            updateDisplay()
        }

        binding.buttonHoursDown.setOnClickListener {
            hours--
            if (hours < 0) {
                hours = 23
            }
            updateDisplay()
        }
    }

    private fun updateDisplay() {
        binding.textView5.text = String.format("%02d", minutes)
        binding.textView7.text = String.format("%02d", hours)
        if (minutes != 0 || hours != 0) {
            val txtContainer = findViewById<Button>(R.id.start_button)
            txtContainer.text =
                "Ξεκίνα σε " + String.format("%02d", hours) + ":" + String.format("%02d", minutes)
        }else {
            val txtContainer = findViewById<Button>(R.id.start_button)
            txtContainer.text = "Ξεκίνα Τωρα "
        }
    }
}
