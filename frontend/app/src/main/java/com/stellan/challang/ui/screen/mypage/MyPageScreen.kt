package com.stellan.challang.ui.screen.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import com.stellan.challang.ui.theme.PaperlogyFamily
import androidx.compose.runtime.*
import androidx.compose.material3.Card
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.window.Dialog
import androidx.compose.material3.HorizontalDivider


@Composable
fun MyPageScreen(
    onHelpClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onWithdrawClick: () -> Unit
) {
    var showLogoutDialog by remember { mutableStateOf(false) }
    var showLoggedOutDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "마이페이지",
                fontFamily = PaperlogyFamily,
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        "귀여운 소주 124",
                        fontFamily = PaperlogyFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Text(
                        "challang.naver.com",
                        fontFamily = PaperlogyFamily,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        "2004.08.06",
                        fontFamily = PaperlogyFamily,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        "여성",
                        fontFamily = PaperlogyFamily,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.weight(2f))
                Surface(
                    shape = CircleShape,
                    color = Color(0xFFE8F1F1),
                    modifier = Modifier.size(72.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        tint = Color(0xFFADADAD)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 4.dp,
            color = Color(0xFFE3F0F0)
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            MyPageItem(text = "도움말", onClick = onHelpClick)
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = Color(0xFFE3F0F0)
            )
            MyPageItem(text = "로그아웃", onClick = { showLogoutDialog = true })
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = Color(0xFFE3F0F0)
            )
            MyPageItem(text = "회원탈퇴", onClick = onWithdrawClick )
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = Color(0xFFE3F0F0)
            )
        }
    }

    if (showLogoutDialog) {
        LogoutDialog(
            onConfirm = {
                showLogoutDialog = false
                showLoggedOutDialog = true
            },
            onDismiss = {
                showLogoutDialog = false
            }
        )
    }

    if (showLoggedOutDialog) {
        ShowLoggedOutDialog(
            onDismiss = { showLoggedOutDialog = false },
            onConfirm = {
                showLoggedOutDialog = false
                onLogoutClick()
            }
        )
    }
}


@Composable
private fun MyPageItem(text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontFamily = PaperlogyFamily,
            fontSize = 18.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = ">",
            fontFamily = PaperlogyFamily,
            fontSize = 18.sp,
            color = Color(0xFFADADAD)
        )
    }
}

@Composable
fun LogoutDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "로그아웃",
                    fontFamily = PaperlogyFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "로그아웃 하시겠습니까?",
                    fontFamily = PaperlogyFamily,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(Color(0xFFE0F2F1))
                            .clickable { onDismiss() }
                            .padding(vertical = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("취소",
                            fontFamily = PaperlogyFamily,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black)
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(Color(0xFFB2DADA))
                            .clickable { onConfirm() }
                            .padding(vertical = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("확인",
                            fontFamily = PaperlogyFamily,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black)
                    }
                }
            }
        }
    }
}

@Composable
fun ShowLoggedOutDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "로그아웃",
                    fontFamily = PaperlogyFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "로그아웃 되었습니다.",
                    fontFamily = PaperlogyFamily,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(Color(0xFFB2DADA))
                            .clickable { onConfirm() }
                            .padding(vertical = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("확인", fontFamily = PaperlogyFamily, fontWeight = FontWeight.Bold, color = Color.Black)
                    }
                }
            }
        }
    }
}


