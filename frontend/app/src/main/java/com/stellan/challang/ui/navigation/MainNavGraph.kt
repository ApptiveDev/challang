package com.stellan.challang.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.stellan.challang.ui.screen.archive.ArchiveScreen
import com.stellan.challang.ui.screen.home.CuratingScreen

fun NavGraphBuilder.mainNavGraph(rootNavController: NavHostController,
                                 bottomNavController: NavHostController) {
    composable("home") {
        CuratingScreen()
    }
    composable("archive") {
        ArchiveScreen()
    }
    myPageNavGraph(rootNavController, bottomNavController)
}
