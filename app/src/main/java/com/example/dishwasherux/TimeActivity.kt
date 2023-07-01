package com.example.dishwasherux

import TextToSpeechManager
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import com.example.dishwasherux.databinding.ActivityTimeBinding

class TimeActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityTimeBinding

    private var minutes = 0
    private var hours = 0
    private lateinit var textToSpeechManager: TextToSpeechManager
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        textToSpeechManager = TextToSpeechManager(this)
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
                Handler(Looper.getMainLooper()).postDelayed({
                    textToSpeechManager.speak("Έχεις επιλέξει το" + selection.title + " πρόγραμμα. Τώρα, επέλεξε σε πόσο χρόνο θέλεις να ξεκινήσει to πλύσιμο. " +
                        "Άμα θέλεις να ξεκινήσει τώρα, πάτα το πράσινο κουμπί που βρίσκεται κάτω δεξιά στην οθόνη. Άμα θέλεις να ορίσεις, εσύ σε πόση " +
                            "ώρα θα ξεκινήσει πάτα το σύμβολο του '+' και του '-'")
                }, 1000)
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
        binding.startButton.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putExtra("id", selectionId)
            intent.putExtra("hours",hours)
            intent.putExtra("minutes", minutes)
            startActivity(intent)
        }

        var homeNavigation = findViewById<Button>(R.id.homeNavigationBarButton);
        homeNavigation.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

    private fun updateDisplay() {
        binding.textView5.text = String.format("%02d", minutes)
        binding.textView7.text = String.format("%02d", hours)
        if (minutes != 0 || hours != 0) {
            val txtContainer = findViewById<Button>(R.id.start_button)
            txtContainer.text =
                "Ξεκίνα σε " + String.format("%02d", hours) + ":" + String.format("%02d", minutes)
            val tellHours = if (hours == 1) "μία ώρα" else String.format("%2d", hours) + "ώρες"
            textToSpeechManager.speak("Έχεις ορίσει να ξεκινήσει σε" + tellHours + "και" +  String.format("%2d", minutes) + "λεπτά")

        }else {
            val txtContainer = findViewById<Button>(R.id.start_button)
            txtContainer.text = "Ξεκίνα Τωρα "
            textToSpeechManager.speak("Έχεις ορίσει να ξεκινήσει τώρα")
        }

    }

}
