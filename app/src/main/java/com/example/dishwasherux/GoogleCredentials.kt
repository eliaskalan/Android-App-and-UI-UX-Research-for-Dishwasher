package com.example.dishwasherux

import com.google.auth.oauth2.GoogleCredentials

    fun getCredentials(): GoogleCredentials {
    return GoogleCredentials.fromStream(
        applicationContext.assets.open("google-service.json")
    )
}
