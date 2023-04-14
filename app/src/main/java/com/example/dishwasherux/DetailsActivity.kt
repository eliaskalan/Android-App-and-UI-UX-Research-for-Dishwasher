package com.example.dishwasherux

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.telecom.Call.Details
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.dishwasherux.databinding.ActivityMainBinding
import com.example.dishwasherux.databinding.DetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: DetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = DetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonDetailsBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        var customColor = "#FFFFFF"
        System.out.println(intent.getStringExtra("selection"))
        if(intent.getStringExtra("selection")=="1"){
            customColor = "#4CAF50"
            val colorInt = Color.parseColor(customColor)
            val myView = findViewById<RelativeLayout>(R.id.image_container)
            myView.setBackgroundColor(colorInt)
        }
    }
}