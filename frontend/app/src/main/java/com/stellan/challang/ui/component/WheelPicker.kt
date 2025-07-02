package com.stellan.challang.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stellan.challang.ui.theme.PaperlogyFamily

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WheelPicker(
    values: List<String>,
    selectedIndex: Int,
    onValueChange: (Int) -> Unit,
    itemHeight: Dp = 56.dp,
    modifier: Modifier = Modifier
) {
    val state = rememberLazyListState(initialFirstVisibleItemIndex = selectedIndex)
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = state)

    Box(
        modifier
            .height(itemHeight)
            .fillMaxWidth()
            .clipToBounds()
    ) {
        LazyColumn(
            state = state,
            flingBehavior = flingBehavior,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(values) { index, value ->
                Box(
                    Modifier
                        .fillParentMaxWidth()
                        .height(itemHeight),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        value,
                        fontFamily = PaperlogyFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }

    LaunchedEffect(state) {
        snapshotFlow { state.firstVisibleItemIndex }
            .collect { onValueChange(it) }
    }
}