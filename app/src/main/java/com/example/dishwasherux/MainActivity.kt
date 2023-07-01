package com.example.dishwasherux


import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.dishwasherux.databinding.ActivityMainBinding
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.widget.Toast
import java.lang.Thread.sleep
import java.util.Locale

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
            intent.putExtra("id", SoftSelection.id)
            startActivity(intent)
        }
        binding.cardClassic.setOnClickListener {
            val intent = Intent(this, TimeActivity::class.java)
            intent.putExtra("id", NormalSection.id)
            startActivity(intent)
        }
        binding.cardStrong.setOnClickListener {
            val intent = Intent(this, TimeActivity::class.java)
            intent.putExtra("id", StrongSection.id)
            startActivity(intent)
        }
        binding.poweroff.setOnClickListener {
            // Start MainActivity
            val intent = Intent(this, SleepAction::class.java)
            startActivity(intent)
        }
        setupTextToSpeech();
        //startSpeechRecognizer();



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
    private val REQUEST_SPEECH_RECOGNIZER = 3000
    private lateinit var textToSpeech: TextToSpeech

    private val speechRecognizerLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                // Handle the speech recognition result here
                val cityOfInterest = "Your city of interest" // Replace this with the actual value

                // Convert the recognized city to speech
                textToSpeech.speak(cityOfInterest, TextToSpeech.QUEUE_FLUSH, null, "utteranceId")
            }
        }

    private fun startSpeechRecognizer() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "City of Interest?")
        speechRecognizerLauncher.launch(intent)
    }

    private fun setupTextToSpeech() {

        textToSpeech = TextToSpeech(this) { status ->
            println("GIANNIS: " + status);
            if (status == TextToSpeech.SUCCESS) {
                Toast.makeText(this, "TextToSpeech success", Toast.LENGTH_SHORT).show()
                println("GIANNIS: " + "SUCCESS SOUND ");
                // Set the language for text-to-speech
                val result = textToSpeech.setLanguage(Locale("el", "GR"))
                println("GIANNIS: result" + result);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    println("GIANNIS: ERROR TEXT TO SPEECH");
                }
            } else {
                println("GIANNIS: FAILED TEXT TO SPEECH");
            }
            println("GIOANNIS: " + textToSpeech.voice.name)
            println("GIOANNIS: " + textToSpeech.voice.name)
            saySomething("Τι θα πλήνουμε σήμερα;")
            saySomething("Πρώτο πρόγραμμα ελαφρύ 40 βαθμοί, πράσινο");
            sleep(500)
            saySomething("Δεύτερο πρόγραμμα κανονικό 60 βαθμοί, πορτοκαλί");
            sleep(500)
            saySomething("Τρίτο πρόγραμμα βαρύ 80 βαθμοί, κόκκινο");
            sleep(500)
            saySomething("Για πληροφορίες πάτα το μωβ κουμπί κάτω από τις καρτέλες");
        }



    }

    private fun saySomething(something: String, queueMode: Int = TextToSpeech.QUEUE_ADD) {
        val speechStatus = textToSpeech.speak(something, queueMode, null, "ID")
        if (speechStatus == TextToSpeech.ERROR) {
            Toast.makeText(this, "Cant use the Text to speech." + something, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        textToSpeech.shutdown()
    }


}

