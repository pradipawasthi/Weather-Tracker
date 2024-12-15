package com.pradip.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val PreferencesName = "weather_tracker_preferences"

private val Context.dataStore by preferencesDataStore(name = PreferencesName)

@Singleton
class LocalPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val weatherTrackerDataStore = context.dataStore

    suspend fun saveCity(value: String) {
        weatherTrackerDataStore.edit { pref ->
            pref[CITY_NAME] = value
        }
    }

    val readCity: Flow<String?>
        get() = weatherTrackerDataStore.data.map { pref ->
            pref[CITY_NAME]
        }

    companion object {
        private val CITY_NAME = stringPreferencesKey("city_name")
    }
}
