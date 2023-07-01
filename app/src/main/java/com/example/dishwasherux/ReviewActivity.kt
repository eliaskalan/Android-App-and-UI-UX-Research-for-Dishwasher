package com.example.dishwasherux


import TextToSpeechManager
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
import androidx.navigation.ui.AppBarConfiguration
import com.example.dishwasherux.databinding.ActivityReviewBinding
import com.example.dishwasherux.databinding.ActivityTimeBinding
import com.example.dishwasherux.databinding.DetailsBinding
import java.util.Calendar

class ReviewActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityReviewBinding
    private lateinit var textToSpeechManager: TextToSpeechManager
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        textToSpeechManager = TextToSpeechManager(this)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        var durationHours = 0
        var durationMinutes = 0
        var demo_hour_time = 0
        var demo_second_time = 0


        setContentView(binding.root)
        val selectionId = intent.getStringExtra("id")
        val hours = intent.getIntExtra("hours", 0)
        val minutes = intent.getIntExtra("minutes", 0)

        for (selection in Selections) {
            if (selection.id == selectionId) {
                val colorInt = Color.parseColor(selection.color)
                val imgContainer = findViewById<RelativeLayout>(R.id.image_container)
                imgContainer.setBackgroundColor(colorInt)
                val txtContainer = findViewById<TextView>(R.id.text_view)
                txtContainer.text = selection.title + "\n" + selection.temperature + "°C"
                val imgView = findViewById<ImageView>(R.id.image_view)
                val customImg = resources.getDrawable(selection.imagePath, theme)
                imgView.setImageDrawable(customImg)
                durationHours = selection.duration_hours
                durationMinutes = selection.duration_minutes
                demo_hour_time = selection.demo_hour_time
                demo_second_time = selection.demo_second_time

            }
        }
        binding.buttonDetailsBack.setOnClickListener {
            val intent = Intent(this, TimeActivity::class.java)
            intent.putExtra("id", selectionId)
            intent.putExtra("hours", hours)
            intent.putExtra("minutes", minutes)
            startActivity(intent)
        }
        binding.Cancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.start.setOnClickListener {
            val intent = Intent(this, WorkingActivity::class.java)
            intent.putExtra("id", selectionId)
            intent.putExtra("demo_hour_time", demo_hour_time.toString())
            intent.putExtra("demo_second_time", demo_second_time.toString())
            startActivity(intent)
        }

        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        // Add delay to the current hour and minute
        val finalHour = (currentHour + hours) % 24
        val finalMinute = (currentMinute + minutes) % 60
        val programFinishMinutes = (currentMinute + minutes + durationMinutes) % 60
        val programFinishHours = (currentHour + hours + durationHours + (currentMinute + minutes + durationMinutes) / 60) % 24
        binding.startHour.text = String.format("%02d", finalHour)
        binding.startMinutes.text = String.format("%02d", finalMinute)
        binding.finishHour.text = String.format("%02d", programFinishHours)
        binding.finishMinutes.text = String.format("%02d", programFinishMinutes)

        Handler(Looper.getMainLooper()).postDelayed({
            textToSpeechManager.speak("Το πλήσιμο θα ξεκινήσει στις " +
                    String.format("%2d", finalHour) + "και" + String.format("%02d", finalMinute) + " Θα λήξη στις" +   String.format("%2d", programFinishHours) + "και" + String.format("%02d", programFinishMinutes) )
            textToSpeechManager.speak("Πάτα το πράσινο κουμπί για να ξεκινήσεις, αλλιώς πάτα το κουμπί ακύρωση ή το κουμπί που " +
                    "πηγαίνεις πίσω" )
        }, 1000)
        var homeNavigation = findViewById<Button>(R.id.homeNavigationBarButton);
        homeNavigation.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        var selectTimeNavigation = findViewById<Button>(R.id.selectTimeNavigationBar);
        selectTimeNavigation.setOnClickListener {
            val intent = Intent(this, TimeActivity::class.java)
            startActivity(intent)
        }

    }
}
