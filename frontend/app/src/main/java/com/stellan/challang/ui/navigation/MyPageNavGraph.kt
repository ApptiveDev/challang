package com.stellan.challang.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.stellan.challang.ui.screen.mypage.*

fun NavGraphBuilder.myPageNavGraph(
    rootNavController: NavHostController,
    bottomNavController: NavHostController
) {
    navigation(startDestination = "mypageMain", route = "mypage") {
        composable("mypageMain") {
            MyPageScreen(
                onHelpClick    = { bottomNavController.navigate("help") },
                onLogoutClick  = {
                    rootNavController.navigate("login") {
                        popUpTo("main") { inclusive = true }
                    }
                                 },
                onWithdrawClick = { bottomNavController.navigate("withdraw") }
            )
        }
        composable("help") {
            HelpScreen(onPrivacy = { bottomNavController.navigate("privacy") },
                onTerms   = { bottomNavController.navigate("terms") })
        }
        composable("privacy") {
            PrivacyPolicyScreen(onBack = { bottomNavController.popBackStack() })
        }
        composable("terms") {
            TermsOfServiceScreen(onBack = { bottomNavController.popBackStack() })
        }
        composable("withdraw") {
            WithdrawScreen(onDone = {
                rootNavController.navigate("login") {
                    popUpTo("main") { inclusive = true }
                    launchSingleTop = true
                }
            })
        }
    }
}