package com.stellan.challang.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.stellan.challang.ui.screens.MyPageScreen
import com.stellan.challang.ui.screens.HomeMainScreen
import androidx.compose.material3.Text
@Composable

fun HomeNavHost(
    tab: String,
    parentNavController: NavController
) {
    when (tab) {
        "home" -> HomeMainScreen()
        "mypage" -> MyPageScreen(parentNavController)
        "drinks" -> {
            // 아직 미구현
            Text("아카이브는 준비 중입니다!")
        }
    }
}
