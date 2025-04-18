package com.example.quickread.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickread.datastore.ThemePreferenceManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ThemeViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> get() = _isDarkMode

    init {
        viewModelScope.launch {
            _isDarkMode.value = ThemePreferenceManager.getDarkMode(context).first()
        }
    }

    fun toggleTheme() {
        viewModelScope.launch {
            val newValue = !_isDarkMode.value
            ThemePreferenceManager.setDarkMode(context, newValue)
            _isDarkMode.value = newValue
        }
    }
}
