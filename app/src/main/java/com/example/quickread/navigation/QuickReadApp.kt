package com.example.quickread.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quickread.screens.*
import com.example.quickread.ui.theme.QuickReadTheme
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun QuickReadApp() {
    val navController = rememberNavController()

    QuickReadTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            NavHost(navController = navController, startDestination = "splash") {
                composable("splash") {
                    SplashScreen(onSplashComplete = {
                        navController.navigate("login") {
                            popUpTo("splash") { inclusive = true }
                        }
                    })
                }

                composable("login") {
                    LoginScreen {
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                }

                composable("home") {
                    HomeScreen(navController)
                }

                composable("bookDetail/{bookId}") { backStackEntry ->
                    val bookId = backStackEntry.arguments?.getString("bookId")
                    BookDetailScreen(bookId = bookId ?: "", navController = navController)
                }

                composable("currentlyReading/{bookId}") { backStackEntry ->
                    val bookId = backStackEntry.arguments?.getString("bookId")
                    CurrentlyReadingScreen(bookId = bookId ?: "")
                }

                composable("recommendations") {
                    RecommendationsScreen()
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewQuickReadApp() {
    QuickReadApp()
}
