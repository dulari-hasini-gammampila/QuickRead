package com.example.quickread.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    // Gradient background: Light blue to soft pink
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color(0xFFBBDEFB), Color(0xFFF8BBD0)) // Light blue to soft pink
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground) // Apply gradient background
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // Header Text: Welcome with book emoji
            Text(
                text = "📚 Welcome to QuickRead",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A237E) // Deep navy blue
                )
            )

            Spacer(modifier = Modifier.height(32.dp)) // Space between header and button

            // Button 1: Currently Reading
            Button(
                onClick = { navController.navigate("currentlyReading") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784)), // Fresh green
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "📖 Currently Reading", // Text with book emoji
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
            }

            Spacer(modifier = Modifier.height(20.dp)) // Space between buttons

            // Button 2: Browse Books
            Button(
                onClick = { navController.navigate("recommendations") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD54F)), // Bright yellow
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Browse Books", // Bold black text
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
            }
        }
    }
}
