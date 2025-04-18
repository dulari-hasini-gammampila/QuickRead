package com.example.quickread.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quickread.screens.*
import com.example.quickread.ui.theme.Primary
import com.example.quickread.ui.theme.Secondary
import com.example.quickread.ui.theme.Background
import androidx.compose.ui.graphics.Color



@Composable
fun QuickReadApp() {
    val navController = rememberNavController()

    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Primary,
            secondary = Secondary,
            background = Background,
            surface = Color.White,
            onPrimary = Color.White,
            onSecondary = Color.Black,
            onSurface = Color.Black,
            onBackground = Color.Black
        )
    ) {
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
                    LoginScreen(
                        onLogin = {
                            navController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        },
                        onRegister = {
                            navController.navigate("register")
                        }
                    )
                }

                composable("home") {
                    HomeScreen(navController = navController)
                }

                composable("currentlyReading") {
                    CurrentlyReadingScreen(navController = navController)
                }

                composable("bookDetail/{bookId}") { backStackEntry ->
                    val bookId = backStackEntry.arguments?.getString("bookId")
                    BookDetailScreen(
                        bookId = bookId ?: "",
                        onReadBook = { bookId ->
                            navController.navigate("reading/$bookId")
                        },
                        onBack = { navController.popBackStack() }
                    )
                }

                composable("reading/{bookId}") { backStackEntry ->
                    val bookId = backStackEntry.arguments?.getString("bookId")
                    ReadingScreen(
                        bookId = bookId ?: "",
                        onBack = { navController.popBackStack() }
                    )
                }

                composable("recommendations") {
                    RecommendationsScreen(
                        onBookClick = { bookId ->
                            navController.navigate("bookDetail/$bookId")
                        },
                        onBack = { navController.popBackStack() }
                    )
                }

                composable("register") {
                    RegisterScreen(navController = navController) {
                        navController.navigate("home") {
                            popUpTo("register") { inclusive = true }
                        }
                    }
                }

            }
        }
    }
}
