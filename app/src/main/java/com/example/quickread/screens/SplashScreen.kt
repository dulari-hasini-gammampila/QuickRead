package com.example.quickread.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.quickread.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashComplete: () -> Unit) {
    // Define a vertical gradient background
    val gradientColors = listOf(Color(0xFF6A5ACD), Color(0xFF87CEEB)) // Blue and pink gradient
    val brush = Brush.verticalGradient(gradientColors)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush),
        contentAlignment = Alignment.Center
    ) {
        // Display the logo image (make sure to put your image in res/drawable folder)
        Image(
            painter = painterResource(id = R.drawable.logo),  // Replace with your image's resource ID
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp),  // Adjust the size of your logo
            contentScale = ContentScale.Fit // Ensure the image is properly scaled
        )

    }

    // Navigate to the next screen after a delay
    LaunchedEffect(Unit) {
        delay(3000) // 3 seconds delay
        onSplashComplete()
    }
}
