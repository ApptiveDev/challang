package com.stellan.challang.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.filled.LocalBar
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.stellan.challang.ui.navigation.HomeNavHost
import com.stellan.challang.ui.theme.PaperlogyFamily

@Composable
fun HomeScreen(navController: NavController) {
    val tabs = listOf( "home", "drinks", "mypage")
    var selectedTab by remember { mutableStateOf("home") }
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Surface(
                tonalElevation = 4.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .height(56.dp),
                    trailingIcon = {
                        IconButton(onClick = { /* 검색 실행 로직 */ }) {
                            Icon(
                                imageVector     = Icons.Default.Search,
                                contentDescription = "검색"
                            )
                        }
                    },
                    singleLine = true,
                )
            }
        },
        bottomBar = {
            NavigationBar (
              containerColor = Color.White
            ) {
                tabs.forEach { tab ->
                    NavigationBarItem(
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab },
                        label = { Text(
                            tabToLabel(tab),
                            fontFamily = PaperlogyFamily,
                            fontWeight = FontWeight.Bold,
                            modifier   = Modifier.offset(y = (-6).dp)
                            ) },
                        icon = {
                            Icon(
                                imageVector = when(tab) {
                                    "home"   -> Icons.Outlined.Home
                                    "drinks" -> Icons.Default.LocalBar
                                    "mypage" -> Icons.Default.Menu
                                    else     -> Icons.Outlined.Home
                                },
                                contentDescription = tabToLabel(tab),
                                modifier = Modifier.size(34.dp)
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            selectedTextColor = Color.Black,
                            unselectedIconColor = Color(0xFFC8C8C8),
                            unselectedTextColor = Color(0xFFC8C8C8),
                            indicatorColor = Color.Transparent
                        )
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
    "home" -> "Home"
    "drinks" -> "Drinks"
    "mypage" -> "My Page"
    else -> tab
}


@Composable
fun HomeMainScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("여기는 홈 메인 화면!")
    }
}
