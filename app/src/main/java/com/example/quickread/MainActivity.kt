package com.example.quickread

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.quickread.navigation.QuickReadApp
import com.example.quickread.ui.theme.QuickReadTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickReadTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    QuickReadApp()
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun PreviewQuickReadApp() {
    QuickReadTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            QuickReadApp() // Preview the main composable
        }
    }
}
