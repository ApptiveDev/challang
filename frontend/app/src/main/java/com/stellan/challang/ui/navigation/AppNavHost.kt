package com.stellan.challang.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.stellan.challang.ui.screens.HomeScreen
import com.stellan.challang.ui.screens.LoginScreen
import com.stellan.challang.ui.screens.SignupScreen
import com.stellan.challang.ui.screens.ProfilesetScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("signup") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("signup") {
            SignupScreen(onSignupComplete = {
                navController.navigate("profileset") {
                    popUpTo("signup") { inclusive = true }
                }
            })
        }

        composable("profileset") {
            ProfilesetScreen(onProfileComplete = {
                navController.navigate("home") {
                    popUpTo("profileset") { inclusive = true }
                }
            })
        }

        composable("home") {
            HomeScreen()
        }
    }
}