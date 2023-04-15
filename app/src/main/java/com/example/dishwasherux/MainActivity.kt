package com.example.dishwasherux


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.dishwasherux.databinding.ActivityMainBinding
import org.w3c.dom.Text
import java.lang.reflect.Modifier

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Soft Selection
        var softImageView = findViewById<ImageView>(R.id.image_view_soft)
        softImageView.setBackgroundColor(Color.parseColor(SoftSelection.color))
        var softImageViewImage = resources.getDrawable(SoftSelection.imagePath, theme)
        softImageView.setImageDrawable(softImageViewImage);
        var softText = findViewById<TextView>(R.id.text_view_soft);
        softText.text = SoftSelection.title + "\n" + SoftSelection.temperature + "°C";

        // Normal Selection
        var normalImageView = findViewById<ImageView>(R.id.image_view_normal)
        normalImageView.setBackgroundColor(Color.parseColor(NormalSection.color))
        var normalImageViewImage = resources.getDrawable(NormalSection.imagePath, theme)
        normalImageView.setImageDrawable(normalImageViewImage);
        var normalText = findViewById<TextView>(R.id.text_view_normal);
        normalText.text = NormalSection.title + "\n" + NormalSection.temperature + "°C";

        // Strong Selection
        var strongImageView = findViewById<ImageView>(R.id.image_view_strong)
        strongImageView.setBackgroundColor(Color.parseColor(StrongSection.color))
        var strongImageViewImage = resources.getDrawable(StrongSection.imagePath, theme)
        strongImageView.setImageDrawable(strongImageViewImage);
        var strongText = findViewById<TextView>(R.id.text_view_strong);
        strongText.text = StrongSection.title + "\n" + StrongSection.temperature + "°C";

        binding.buttonSoftDetail.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("id", SoftSelection.id)
            startActivity(intent)
        }
        binding.buttonClassicDetail.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("id", NormalSection.id)
            startActivity(intent)
        }
        binding.buttonStrongDetail3.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("id", StrongSection.id)
            startActivity(intent)
        }
        binding.cardSoft.setOnClickListener {
            val intent = Intent(this, TimeActivity::class.java)
            intent.putExtra("selection", "1")
            startActivity(intent)
        }


    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}

