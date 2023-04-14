package com.example.dishwasherux

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.telecom.Call.Details
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
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
        var customTxt = "NaN"
        var customImg =resources.getDrawable(R.drawable.plate_soft, theme)
        System.out.println(intent.getStringExtra("selection"))
        if(intent.getStringExtra("selection")=="1"){
            customColor = "#4CAF50"
            customTxt= "Soft\n40 °C"
            customImg = resources.getDrawable(R.drawable.plate_soft, theme)
        }
        if(intent.getStringExtra("selection")=="2"){
            customColor = "#FF9800"
            customTxt= "Classic\n60 °C"
            customImg = resources.getDrawable(R.drawable.plate_classic, theme)
        }
        if(intent.getStringExtra("selection")=="3"){
            customColor = "#F44336"
            customTxt= "Soft\n80 °C"
            customImg = resources.getDrawable(R.drawable.plate_strong, theme)
        }
        val colorInt = Color.parseColor(customColor)
        val imgContainer = findViewById<RelativeLayout>(R.id.image_container)
        imgContainer.setBackgroundColor(colorInt)
        val txtContainer = findViewById<TextView>(R.id.text_view_soft)
        txtContainer.setText(customTxt)
        val imgView = findViewById<ImageView>(R.id.image_view)
        imgView.setImageDrawable(customImg)

    }
}