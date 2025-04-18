package com.example.quickread

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.quickread.navigation.QuickReadApp
import com.example.quickread.ui.theme.QuickReadTheme
import com.example.quickread.viewmodel.ThemeViewModel


class MainActivity : ComponentActivity() {
    private val themeViewModel: ThemeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isDarkMode by themeViewModel.isDarkMode.collectAsState()

            QuickReadTheme(darkTheme = isDarkMode) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    QuickReadApp(themeViewModel = themeViewModel)
                }
            }
        }
    }

}
