package com.example.data.offlineData.cache

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class CacheAuthToken(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Auth")

    private object Keys {
        val TOKEN_KEY = stringPreferencesKey("token_key")
        val EXPIRATION_KEY = longPreferencesKey("expiration_key")

    }

    suspend fun getToken(): String? {
        val preferences = context.dataStore.data.first()
        val token = preferences[Keys.TOKEN_KEY]
        val expiration = preferences[Keys.EXPIRATION_KEY]
        if (expiration != null) {
            if (expiration >= System.currentTimeMillis()) {
                return token
            }
        }
        return null
    }

    suspend fun saveToken(token: String, expiration: Long) {
        context.dataStore.edit { preferences ->
            preferences[Keys.TOKEN_KEY] = token
            preferences[Keys.EXPIRATION_KEY] = (expiration*1000)+System.currentTimeMillis()
        }

    }

}