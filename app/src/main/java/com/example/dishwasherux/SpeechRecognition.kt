package com.example.dishwasherux
import com.google.api.gax.core.FixedCredentialsProvider
import com.google.cloud.speech.v1.RecognitionAudio
import com.google.cloud.speech.v1.RecognitionConfig
import com.google.cloud.speech.v1.RecognizeRequest
import com.google.cloud.speech.v1.SpeechClient
import com.google.protobuf.ByteString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun transcribeSpeech(audioData: ByteArray): String = withContext(Dispatchers.IO) {
    val credentials = getCredentials()
    val client = SpeechClient.create(
        SpeechClient.Settings.newBuilder()
            .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
            .build()
    )

    val audio = RecognitionAudio.newBuilder()
        .setContent(ByteString.copyFrom(audioData))
        .build()

    val config = RecognitionConfig.newBuilder()
        .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
        .setSampleRateHertz(16000)
        .setLanguageCode("en-US")
        .build()

    val request = RecognizeRequest.newBuilder()
        .setConfig(config)
        .setAudio(audio)
        .build()

    val response = client.recognize(request)
    val result = response.resultsList[0]
    val transcript = result.alternativesList[0].transcript

    client.shutdown()
    transcript
}
