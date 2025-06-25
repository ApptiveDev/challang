package com.stellan.challang.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.stellan.challang.ui.theme.PaperlogyFamily

@Composable
fun SearchScreen() {
    var searchQuery by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .background(Color.White),
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { /* 검색 실행 로직 */ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "검색"
                    )
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color(0xFFCEEFF2),
                unfocusedContainerColor = Color(0xFFCEEFF2),
                focusedContainerColor = Color(0xFFCEEFF2),

                focusedIndicatorColor    = Color.Transparent,
                unfocusedIndicatorColor  = Color.Transparent,
                disabledIndicatorColor   = Color.Transparent,
            ),
            textStyle = TextStyle(
                fontFamily = PaperlogyFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
        )
        Text("검색")
    }
}