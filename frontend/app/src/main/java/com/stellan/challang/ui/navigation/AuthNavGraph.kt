package com.stellan.challang.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.stellan.challang.ui.screen.auth.LoginScreen
import com.stellan.challang.ui.screen.auth.ProfileSettingScreen
import com.stellan.challang.ui.screen.auth.SignupScreen


@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        startDestination = "login",
        route = "auth"
    ) {
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
            SignupScreen(
                onSignupComplete = {
                    navController.navigate("profilesetting") {
                        popUpTo("signup") { inclusive = true }
                    }
                }
            )
        }
        composable("profilesetting") {
            ProfileSettingScreen(
                onProfileComplete = {
                    navController.navigate("main") {
                        popUpTo("auth") { inclusive = true }
                    }
                }
            )
        }
    }
}