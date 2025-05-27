package com.stellan.challang.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stellan.challang.R
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import kotlin.math.roundToInt
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.stellan.challang.ui.theme.PaperlogyFamily
import kotlinx.coroutines.delay

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material3.Surface

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableFloatStateOf

import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity

import androidx.compose.ui.unit.IntOffset

private data class Bubble(
    val size: Dp,
    val xFraction: Float,
    val duration: Int,
    val startDelay: Int
)

private val bubbles = listOf(
    Bubble(size = 90.dp,  xFraction = 0.1f, duration = 3000, startDelay = 0),
    Bubble(size = 60.dp,  xFraction = 0.3f, duration = 3500, startDelay = 500),
    Bubble(size = 140.dp, xFraction = 0.7f, duration = 3200, startDelay = 300),
    Bubble(size = 50.dp,  xFraction = 0.9f, duration = 2800, startDelay = 800)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilesetScreen(
    onProfileComplete: () -> Unit
) {
    var step by remember { mutableIntStateOf(1) }

    when (step) {
        1 -> ProfileStepOne(onNext = { step = 2 })
        2 -> ProfileStepTwo(onNext = { step = 3 })
        3 -> ProfileStepThree(
            onValueSelected = {},
            onNext = { step = 4 }
        )
        4 -> ProfileStepFour(onNext = { step = 5 })
        5 -> ProfileStepFive(onNext = onProfileComplete)
    }
}

@Composable
fun ProfileStepOne(
    onNext: () -> Unit
) {
    val alcoholOptions = listOf("\uD83C\uDF7E소주", "\uD83C\uDF7A맥주", "\uD83C\uDF77와인",
        "\uD83E\uDD43위스키", "\uD83C\uDF78칵테일", "\uD83C\uDF76전통주")
    val selectedOptions = remember { mutableStateListOf<String>() }
    val isSelectionEnough = selectedOptions.size >= 3

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                text = "프로필 설정",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp, bottom = 6.dp),
                fontFamily = PaperlogyFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                thickness = 1.dp,
                color = Color(0xFFD9D9D9)
            )
            Spacer(modifier = Modifier.height(56.dp))
            Text(
                text = "선호하는 주종을\n세가지 알려주세요!",
                fontFamily = PaperlogyFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
                color = Color.Black,
                lineHeight = 28.sp,
                modifier = Modifier.padding(bottom = 24.dp),
            )
            Spacer(modifier = Modifier.height(40.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                alcoholOptions.forEach { option ->
                    val isSelected = selectedOptions.contains(option)
                    Surface(
                        shape = CircleShape,
                        color = if (isSelected) Color(0xFFFFC488) else Color(0xFFFFDDBA),
                        tonalElevation = if (isSelected) 4.dp else 0.dp,
                        modifier = Modifier
                            .size(width = 100.dp, height = 57.dp)
                            .clickable {
                                if (isSelected) {
                                    selectedOptions.remove(option)
                                } else if (selectedOptions.size < 3) {
                                    selectedOptions.add(option)
                                }
                            }
                            .padding(horizontal = 2.dp, vertical = 8.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = option,
                                fontFamily = PaperlogyFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 18.sp,
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                ) {
                    Button(
                        onClick = {
                            if (isSelectionEnough) {
                                onNext()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelectionEnough) Color(0xFFFFC488) else Color(0xFFFFDDBA)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    )  {
                        Text(
                            "다음",
                            fontFamily = PaperlogyFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 24.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun ProfileStepTwo(
        onNext: () -> Unit
    ) {
        val alcoholOptions = listOf("과일향", "스파이시", "스위트", "드라이", "허브", "스모키")
        val selectedOptions = remember { mutableStateListOf<String>() }
        val isSelectionEnough = selectedOptions.size >= 3

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Text(
                    text = "프로필 설정",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp, bottom = 6.dp),
                    fontFamily = PaperlogyFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 24.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    thickness = 1.dp,
                    color = Color(0xFFD9D9D9)
                )
                Spacer(modifier = Modifier.height(56.dp))
                Text(
                    text = "선호하는 맛을\n세가지 알려주세요!",
                    fontFamily = PaperlogyFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 24.sp,
                    color = Color.Black,
                    lineHeight = 28.sp,
                    modifier = Modifier.padding(bottom = 24.dp),
                )
                Spacer(modifier = Modifier.height(40.dp))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    alcoholOptions.forEach { option ->
                        val isSelected = selectedOptions.contains(option)
                        Surface(
                            shape = CircleShape,
                            color = if (isSelected) Color(0xFFFFC488) else Color(0xFFFFDDBA),
                            tonalElevation = if (isSelected) 4.dp else 0.dp,
                            modifier = Modifier
                                .size(width = 100.dp, height = 57.dp)
                                .clickable {
                                    if (isSelected) {
                                        selectedOptions.remove(option)
                                    } else if (selectedOptions.size < 3) {
                                        selectedOptions.add(option)
                                    }
                                }
                                .padding(horizontal = 2.dp, vertical = 8.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = option,
                                    fontFamily = PaperlogyFamily,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 18.sp,
                                    color = Color.Black,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                ) {
                    Button(
                        onClick = {
                            if (isSelectionEnough) {
                                onNext()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelectionEnough) Color(0xFFFFC488) else Color(0xFFFFDDBA)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text(
                            "다음",
                            fontFamily = PaperlogyFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 24.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }

@Composable
fun ProfileStepThree(
    onValueSelected: (Int) -> Unit,
    onNext: () -> Unit
) {
    val levels = listOf("고도수", "중도수", "저도수")
    var sliderValue by remember { mutableFloatStateOf(1f) }
    val selectedIndex = sliderValue.roundToInt()
    val isSelectionMade = sliderValue in 0f..2f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Text(
            text = "프로필 설정",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp, bottom = 6.dp),
            fontFamily = PaperlogyFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            thickness = 1.dp,
            color = Color(0xFFD9D9D9)
        )
        Spacer(modifier = Modifier.height(56.dp))
        Text(
            text = "선호하는 도수를\n알려주세요!",
            fontFamily = PaperlogyFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            color = Color.Black,
            lineHeight = 28.sp,
            modifier = Modifier.padding(bottom = 24.dp),
        )
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .align(Alignment.Center)
                    .offset(x = (-159).dp)
                    .background(Color(0xFFFFDDBA), shape = RoundedCornerShape(4.dp))
            )
            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                onValueChangeFinished = { onValueSelected(selectedIndex) },
                valueRange = 0f..2f,
                steps = 1,
                modifier = Modifier
                    .height(10.dp)
                    .width(220.dp)
                    .rotate(90f)
                    .align(Alignment.CenterStart)
                    .offset(y = 92.dp),
                colors = SliderDefaults.colors(
                    activeTrackColor = Color.Transparent,
                    inactiveTrackColor = Color.Transparent,
                    thumbColor = Color(0xFFFFC488)
                )
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterStart)
                    .offset(x = (-37).dp)
            ) {
                levels.forEachIndexed { index, label ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(40.dp))
                        Box(
                            modifier = Modifier
                                .size(25.dp)
                                .clip(CircleShape)
                                .background(
                                    if (selectedIndex == index)
                                        Color(0xFFFFC488)
                                    else
                                        Color(0xFFFFDDBA)
                                )
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = label,
                            fontFamily = PaperlogyFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 24.sp,
                            color = Color.Black,
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Button(
                onClick = {
                    if (isSelectionMade) onNext()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFC488)
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    "다음",
                    fontFamily = PaperlogyFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 24.sp,
                    color = Color.Black
                )
            }
        }
    }
}

//@Composable
//fun ProfileStepFour(
//    onNext: () -> Unit
//) {
//    LaunchedEffect(Unit) {
//        delay(2_000L)
//        onNext()
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//        ) {
//            Text(
//                text = "프로필 설정",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 25.dp, bottom = 6.dp),
//                fontFamily = PaperlogyFamily,
//                fontWeight = FontWeight.Normal,
//                fontSize = 24.sp,
//                color = Color.Black,
//                textAlign = TextAlign.Center
//            )
//            HorizontalDivider(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp),
//                thickness = 1.dp,
//                color = Color(0xFFD9D9D9)
//            )
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(1f),
//                contentAlignment = Alignment.Center
//            ) {
//                Box(modifier = Modifier
//                    .size(90.dp)
//                    .offset(x = (-130).dp, y = (20).dp)
//                    .background(Color(0xFFFFDDBA), shape = CircleShape)
//                )
//                Box(modifier = Modifier
//                    .size(60.dp)
//                    .offset(x = (-80).dp, y = (-65).dp)
//                    .background(Color(0xFFFFC488), shape = CircleShape)
//                )
//                Box(modifier = Modifier
//                    .size(140.dp)
//                    .offset(x = (100).dp, y = (250).dp)
//                    .background(Color(0xFFFFC488), shape = CircleShape)
//                )
//                Box(modifier = Modifier
//                    .size(50.dp)
//                    .offset(x = (20).dp, y = (325).dp)
//                    .background(Color(0xFFFFC488), shape = CircleShape)
//                )
//                Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                    Image(
//                        painter = painterResource(id = R.drawable.proflie_image_basic),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .size(200.dp)
//                            .padding(bottom = 10.dp)
//                            .offset(x = 55.dp)
//                            .offset(y = 25.dp)
//                    )
//                    Text(
//                        text = "거의\n다 왔어요!",
//                        fontFamily = PaperlogyFamily,
//                        fontWeight = FontWeight.Normal,
//                        fontSize = 40.sp,
//                        color = Color.Black,
//                        textAlign = TextAlign.Start,
//                        lineHeight = 48.sp,
//                        modifier = Modifier
//                            .offset(y = 20.dp)
//                    )
//                }
//            }
//        }
//    }
//}

@Composable
fun ProfileStepFour(
    onNext: () -> Unit
) {
//    // 3) 2초 뒤 자동으로 다음 단계
//    LaunchedEffect(Unit) {
//        delay(2_000L)
//        onNext()
//    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 4) animation 이 올라갈 영역의 실제 높이를 측정
        var containerPxHeight by remember { mutableIntStateOf(0) }
        val density = LocalDensity.current

        Box(
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned { coords ->
                    containerPxHeight = coords.size.height
                }
        ) {
            // 5) 각 bubble 애니메이션
            bubbles.forEach { bubble ->
                // 0..1 사이를 오가며 yOffset 애니메이션
                val anim = remember { Animatable(1f) }

                LaunchedEffect(bubble) {
                    delay(bubble.startDelay.toLong())
                    anim.animateTo(
                        targetValue = 0f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(bubble.duration, easing = LinearEasing),
                            repeatMode = RepeatMode.Restart
                        )
                    )
                }

                // 6) 위치 계산: anim.value=1->0 이 containerPxHeight->0 으로 매핑
                val yOffsetPx = (containerPxHeight * anim.value).toInt()

                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = bubble.xFraction)
                        .offset { IntOffset(x = 0, y = yOffsetPx) }
                        .size(bubble.size)
                        .clip(CircleShape)
                        .background(Color(0xFFFFC488).copy(alpha = 0.6f))
                )
            }
        }

        // 7) 중앙 프로필 이미지 + 텍스트
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.proflie_image_basic),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
            Text(
                text = "거의\n다 왔어요!",
                fontFamily = PaperlogyFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 40.sp,
                lineHeight = 48.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }
}

@Composable
fun ProfileStepFive(
    onNext: () -> Unit
) {
    val alcoholOptions = listOf("깔끔한", "부드러운", "탄산감", "바디감", "묵직한", "이색적인")
    val selectedOptions = remember { mutableStateListOf<String>() }
    val isSelectionEnough = selectedOptions.size >= 3

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                text = "프로필 설정",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp, bottom = 6.dp),
                fontFamily = PaperlogyFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                thickness = 1.dp,
                color = Color(0xFFD9D9D9)
            )
            Spacer(modifier = Modifier.height(56.dp))
            Text(
                text = "선호하는 스타일을\n세가지 알려주세요!",
                fontFamily = PaperlogyFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
                color = Color.Black,
                lineHeight = 28.sp,
                modifier = Modifier.padding(bottom = 24.dp),
            )
            Spacer(modifier = Modifier.height(40.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                alcoholOptions.forEach { option ->
                    val isSelected = selectedOptions.contains(option)
                    Surface(
                        shape = CircleShape,
                        color = if (isSelected) Color(0xFFFFC488) else Color(0xFFFFDDBA),
                        tonalElevation = if (isSelected) 4.dp else 0.dp,
                        modifier = Modifier
                            .size(width = 100.dp, height = 57.dp)
                            .clickable {
                                if (isSelected) {
                                    selectedOptions.remove(option)
                                } else if (selectedOptions.size < 3) {
                                    selectedOptions.add(option)
                                }
                            }
                            .padding(horizontal = 2.dp, vertical = 8.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = option,
                                fontFamily = PaperlogyFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 18.sp,
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                Button(
                    onClick = {
                        if (isSelectionEnough) {
                            onNext()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelectionEnough) Color(0xFFFFC488) else Color(0xFFFFDDBA)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                )  {
                    Text(
                        "확인",
                        fontFamily = PaperlogyFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 24.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}
