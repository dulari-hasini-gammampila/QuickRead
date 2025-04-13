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

                composable("register") {
                    RegisterScreen(
                        onRegisterComplete = {
                            navController.navigate("home") {
                                popUpTo("register") { inclusive = true }
                            }
                        },
                        onBackToLogin = {
                            navController.popBackStack()
                        }
                    )
                }

                composable("home") {
                    HomeScreen(navController = navController)
                }

                composable("bookDetail/{bookId}") { backStackEntry ->
                    val bookId = backStackEntry.arguments?.getString("bookId")
                    BookDetailScreen(
                        bookId = bookId ?: "",
                        onReadBook = { bookId -> navController.navigate("reading/$bookId") },
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
                        onBookClick = { bookId -> navController.navigate("bookDetail/$bookId") },
                        onBack = { navController.popBackStack() }
                    )
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
