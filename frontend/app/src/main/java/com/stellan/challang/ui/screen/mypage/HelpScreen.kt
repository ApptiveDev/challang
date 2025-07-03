package com.stellan.challang.ui.screen.mypage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stellan.challang.ui.theme.PaperlogyFamily

@Composable
fun HelpScreen(
    onPrivacy: () -> Unit,
    onTerms: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "도움말",
            fontFamily = PaperlogyFamily,
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 200.dp)
        )

        HelpItem(
            text = "개인정보처리방침",
            onClick = onPrivacy
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color(0xFFE3F0F0)
        )
        HelpItem(
            text = "서비스 이용약관",
            onClick = onTerms
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color(0xFFE3F0F0)
        )
    }
}

@Composable
fun HelpItem(text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontFamily = PaperlogyFamily,
            fontSize = 16.sp,
            color = Color.Black
        )
        Text(
            text = ">",
            fontFamily = PaperlogyFamily,
            fontSize = 16.sp,
            color = Color(0xFFADADAD)
        )
    }
}