package com.stellan.challang.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stellan.challang.R
import com.stellan.challang.ui.component.SpeechBubble
import com.stellan.challang.ui.theme.PaperlogyFamily

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.login_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(
                    Color.Black.copy(alpha = 0.3f),
                    blendMode = BlendMode.Darken
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 230.dp)
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "당신이 술을 필요로 할때, 찰랑",
                        fontFamily = PaperlogyFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 30.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "나만을 위한 주류 큐레이팅 서비스",
                        fontFamily = PaperlogyFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SpeechBubble(text = "3초만에 회원가입")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = onLoginSuccess,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD940)),
                        modifier = Modifier.size(width = 300.dp, height = 50.dp)
                    ) {
                        Text(
                            text = "카카오톡으로 계속하기",
                            fontFamily = PaperlogyFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 17.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}