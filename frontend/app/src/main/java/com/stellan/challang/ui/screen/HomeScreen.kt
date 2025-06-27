package com.stellan.challang.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.stellan.challang.ui.navigation.HomeNavHost
import com.stellan.challang.ui.theme.PaperlogyFamily

@Composable
fun HomeScreen(navController: NavController) {
    val tabs = listOf( "home", "archive", "mypage")
    var selectedTab by remember { mutableStateOf("home") }

    Scaffold(
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
                                    "archive" -> Icons.Outlined.BookmarkBorder
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
            ) {
                HomeNavHost(tab = selectedTab, parentNavController = navController)
            }
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 2.dp,
                color = Color(0xFFD9D9D9)
            )
        }
    }
}

fun tabToLabel(tab: String): String = when (tab) {
    "home" -> "Home"
    "archive" -> "Archive"
    "mypage" -> "My Page"
    else -> tab
}