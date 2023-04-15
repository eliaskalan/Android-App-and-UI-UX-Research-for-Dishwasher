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
        val selectionId = intent.getStringExtra("id");
        for(selection in Selections){
            if(selection.id == selectionId){
                println(selection.id);
                val colorInt = Color.parseColor(selection.color)
                val imgContainer = findViewById<RelativeLayout>(R.id.image_container)
                imgContainer.setBackgroundColor(colorInt)
                val txtContainer = findViewById<TextView>(R.id.text_view)
                txtContainer.text = selection.title + "\n" + selection.temperature + "°C";
                val imgView = findViewById<ImageView>(R.id.image_view)
                var customImg =resources.getDrawable(selection.imagePath, theme)
                imgView.setImageDrawable(customImg)
                val textViewTitle = findViewById<TextView>(R.id.text_view_title);
                textViewTitle.text = selection.title;
                val textViewDescription = findViewById<TextView>(R.id.text_view_description);
                textViewDescription.text = selection.description;
            }
        }


    }
}