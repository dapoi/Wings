package com.dapascript.wings.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val HAS_LOGIN = booleanPreferencesKey("has_login")
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store")

@Singleton
class DataPreference @Inject constructor(context: Context) {

    private val dataStore = context.dataStore

    suspend fun saveHasLogin(hasLogin: Boolean) {
        dataStore.edit { preferences ->
            preferences[HAS_LOGIN] = hasLogin
        }
    }

    fun getHasLogin() = dataStore.data.map { preferences ->
        preferences[HAS_LOGIN] ?: false
    }
}