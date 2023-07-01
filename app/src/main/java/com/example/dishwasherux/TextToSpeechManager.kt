import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import java.util.Locale

class TextToSpeechManager(private val context: Context) {

    private var textToSpeech: TextToSpeech? = null
    private var isInitialized = false
    private var queueSize = 1;
    init {
        initializeTextToSpeech()
    }

    private fun initializeTextToSpeech() {
        textToSpeech = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = textToSpeech?.setLanguage(Locale("el", "GR"))
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TextToSpeechManager", "ERROR: Language is not supported.")
                } else {
                    textToSpeech?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                        override fun onStart(utteranceId: String?) {
                            Log.d("TextToSpeechManager", "onStart: $utteranceId")
                        }

                        override fun onDone(utteranceId: String?) {
                            Log.d("TextToSpeechManager", "onDone: $utteranceId")
                        }

                        override fun onError(utteranceId: String?) {
                            Log.e("TextToSpeechManager", "onError: $utteranceId")
                        }
                    })
                    isInitialized = true
                }
            } else {
                Log.e("TextToSpeechManager", "ERROR: TextToSpeech initialization failed.")
            }
        })
    }

    fun speak(text: String, queueMode: Int = TextToSpeech.QUEUE_FLUSH) {
            queueSize++;
        if (isInitialized) {
            textToSpeech?.speak(text, queueSize, null, "ID")
        } else {
            Log.e("TextToSpeechManager", "ERROR: TextToSpeech is not initialized.")
        }
    }

    fun shutdown() {
        textToSpeech?.shutdown()
    }
}
