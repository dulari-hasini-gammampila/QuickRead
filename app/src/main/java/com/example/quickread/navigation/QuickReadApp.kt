package com.example.quickread.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quickread.screens.BookDetailScreen
import com.example.quickread.screens.CurrentlyReadingScreen
import com.example.quickread.screens.HomeScreen
import com.example.quickread.screens.LoginScreen
import com.example.quickread.screens.ReadingScreen
import com.example.quickread.screens.RecommendationsScreen
import com.example.quickread.screens.RegisterScreen
import com.example.quickread.screens.SplashScreen
import com.example.quickread.ui.theme.Background
import com.example.quickread.ui.theme.Primary
import com.example.quickread.ui.theme.Secondary
import com.example.quickread.viewmodel.ThemeViewModel

@Composable
fun QuickReadApp(themeViewModel: ThemeViewModel) {
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
                        onReadBook = { id ->
                            navController.navigate("reading/$id")
                        },
                        onBack = { navController.popBackStack() }
                    )
                }

                composable("reading/{bookId}") { backStackEntry ->
                    val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
                    ReadingScreen(
                        bookId = bookId,
                        onBack = { navController.popBackStack() },
                        themeViewModel = themeViewModel // ✅ Passed correctly here
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
