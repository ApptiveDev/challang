package com.stellan.challang.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stellan.challang.R
import com.stellan.challang.ui.components.BirthdayPicker
import java.util.*
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import java.time.LocalDate
import androidx.compose.foundation.border
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalWindowInfo
import com.stellan.challang.ui.theme.PaperlogyFamily


@RequiresApi(Build.VERSION_CODES.O)
fun getKoreanAge(birthYear: Int, birthMonth: Int, birthDay: Int): Int {
    val today = LocalDate.now()
    val birthday = LocalDate.of(birthYear, birthMonth + 1, birthDay)
    var age = today.year - birthday.year
    if (today < birthday.withYear(today.year)) {
        age -= 1
    }
    return age
}

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    onSignupComplete: () -> Unit
) {
    var nickname by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("남성") }
    val calendar = Calendar.getInstance().apply {
        add(Calendar.YEAR, -19)
    }
    var year by remember { mutableIntStateOf(calendar.get(Calendar.YEAR)) }
    var month by remember { mutableIntStateOf(calendar.get(Calendar.MONTH)) }
    var day by remember { mutableIntStateOf(calendar.get(Calendar.DAY_OF_MONTH)) }

    val age = getKoreanAge(year, month, day)

    var showNicknameWarning by remember { mutableStateOf(false) }

    var selectedProfileRes by remember { mutableIntStateOf(R.drawable.proflie_image_basic) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }

    val containerSize = LocalWindowInfo.current.containerSize
    val screenWidth = containerSize.width.dp

    val profileImages = listOf(
        R.drawable.proflie_image_basic,
        R.drawable.profile_image_1,
        R.drawable.profile_image_2,
        R.drawable.profile_image_3,
        R.drawable.profile_image_4,
        R.drawable.profile_image_5
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                text = "회원가입",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 25.dp),
                fontFamily = PaperlogyFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(Color(0xFFEFEFEF), CircleShape)
                    .clickable { showBottomSheet = true },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(selectedProfileRes),
                    contentDescription = "선택된 프로필 이미지",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    contentScale = ContentScale.Crop
                )
            }
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState,
                    containerColor = Color.White,
                    tonalElevation = 4.dp,
                    dragHandle = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(28.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            HorizontalDivider(
                                modifier = Modifier
                                    .width(180.dp)
                                    .clip(RoundedCornerShape(2.dp)),
                                thickness = 5.dp,
                                color = Color(0xFFD9D9D9)
                            )
                        }
                    }
                ) {
                    Column(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(18.dp)
                        ) {
                            items(profileImages) { imageRes ->
                                Box(
                                    modifier = Modifier
                                        .width(screenWidth * 0.22f)
                                        .padding(top = 30.dp, bottom = 20.dp)
                                        .aspectRatio(1f)
                                        .clip(CircleShape)
                                        .clickable {
                                            selectedProfileRes = imageRes
                                            showBottomSheet = false
                                        }
                                        .border(
                                            1.dp,
                                            if (selectedProfileRes == imageRes) Color(0xFF838383)
                                            else Color.Transparent,
                                            shape = CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                )
                                {
                                    Image(
                                        painter = painterResource(imageRes),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(150.dp)
                                            .clip(CircleShape),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(35.dp))
            Text("닉네임",
                fontFamily = PaperlogyFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Color.Black
            )
            OutlinedTextField(
                value = nickname,
                onValueChange = {
                    nickname = it
                    if (it.isNotBlank()) {
                        showNicknameWarning = false
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFFFDDBA),
                    unfocusedBorderColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text("성별",
                fontFamily = PaperlogyFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Color.Black
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                listOf("남성", "여성").forEach { option ->
                    val isSelected = gender == option
                    Surface(
                        color = if (isSelected) Color(0xFFFFC283) else Color(0xFFFFDDBA),
                        tonalElevation = if (gender == option) 4.dp else 0.dp,
                        shape = MaterialTheme.shapes.extraSmall,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { gender = option }
                            .height(56.dp)
                    ) {
                        Text(
                            option,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(Alignment.CenterVertically),
                            fontFamily = PaperlogyFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Text(
                "성별은 최초 등록 이후 수정이 불가능합니다.",
                fontFamily = PaperlogyFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Color(0xFF838383),
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text("생년월일",
                fontFamily = PaperlogyFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Color.Black
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = if (age < 19) Color.Red else Color.Transparent,
                        shape = RoundedCornerShape(6.dp)
                    )
            ) {
                BirthdayPicker(
                    year = year,
                    month = month,
                    day = day,
                    onYearChange = { year = it },
                    onMonthChange = { month = it },
                    onDayChange = { day = it }
                )
            }
            if (age < 19) {
                Text(
                    "찰랑은 만19세 이상부터 가입할 수 있어요!",
                    fontFamily = PaperlogyFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color.Red
                )
            } else {
                Text(
                    "찰랑은 만19세 이상부터 가입할 수 있어요!",
                    fontFamily = PaperlogyFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color(0xFF838383)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                Button(
                    onClick = {
                        onSignupComplete()
                    },
                    enabled = age >= 19,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFC283),
                        disabledContainerColor = Color(0xFFFFDDBA)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("확인",
                        fontFamily = PaperlogyFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 17.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}