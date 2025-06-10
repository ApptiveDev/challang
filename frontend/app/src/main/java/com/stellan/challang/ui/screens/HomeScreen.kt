package com.stellan.challang.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.stellan.challang.ui.navigation.HomeNavHost


@Composable
fun HomeScreen(navController: NavController) {
    val tabs = listOf("archive", "home", "mypage")
    var selectedTab by remember { mutableStateOf("home") }

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEach { tab ->
                    NavigationBarItem(
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab },
                        label = { Text(tabToLabel(tab)) },
                        icon = {}
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {
            HomeNavHost(tab = selectedTab, parentNavController = navController)
        }
    }
}

fun tabToLabel(tab: String): String = when (tab) {
    "archive" -> "아카이브"
    "home" -> "홈"
    "mypage" -> "마이페이지"
    else -> tab
}


@Composable
fun HomeMainScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("여기는 홈 메인 화면!")
    }
}
