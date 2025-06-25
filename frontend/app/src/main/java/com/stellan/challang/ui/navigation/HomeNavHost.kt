package com.stellan.challang.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.stellan.challang.ui.screen.ArchiveScreen
import com.stellan.challang.ui.screen.CuratingScreen
import com.stellan.challang.ui.screen.MyPageScreen
import com.stellan.challang.ui.screen.SearchScreen

@Composable
fun HomeNavHost(
    tab: String,
    parentNavController: NavController
) {
    when (tab) {
        "home" -> CuratingScreen()
        "mypage" -> MyPageScreen(parentNavController)
        "archive" -> ArchiveScreen()
        "search" -> SearchScreen()
    }
}
