package com.stellan.challang.ui.components

import android.os.Build
import android.widget.NumberPicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.unit.dp
import java.util.*

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun BirthdayPicker(
    year: Int,
    month: Int,
    day: Int,
    onYearChange: (Int) -> Unit,
    onMonthChange: (Int) -> Unit,
    onDayChange: (Int) -> Unit
) {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                color = Color(0xFFFFDDBA),
                shape = RoundedCornerShape(6.dp)
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AndroidView(
            factory = { ctx ->
                NumberPicker(ctx).apply {
                    minValue = 1900
                    maxValue = currentYear - 19
                    value = year
                    setOnValueChangedListener { _, _, new ->
                        onYearChange(new)
                    }
                    descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
                    wrapSelectorWheel = false
                    selectionDividerHeight = 0
                }
            },
            modifier = Modifier
                .weight(1f)
        )
        VerticalDivider(
            modifier = Modifier
                .height(40.dp),
            thickness = 2.dp,
            color = Color(0xFFD9D9D9)
        )
        AndroidView(
            factory = { ctx ->
                NumberPicker(ctx).apply {
                    minValue = 1
                    maxValue = 12
                    value = month + 1
                    setFormatter { i -> "%02d".format(i) }
                    setOnValueChangedListener { _, _, new ->
                        onMonthChange(new - 1)
                    }
                    descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
                    wrapSelectorWheel = false
                    selectionDividerHeight = 0
                }
            },
            modifier = Modifier
                .weight(1f)
        )
        VerticalDivider(
            modifier = Modifier
                .height(40.dp),
            thickness = 2.dp,
            color = Color(0xFFD9D9D9)
        )
        AndroidView(
            factory = { ctx ->
                NumberPicker(ctx).apply {
                    minValue = 1
                    maxValue = 31
                    value = day
                    setFormatter { i -> "%02d".format(i) }
                    setOnValueChangedListener { _, _, new ->
                        onDayChange(new)
                    }
                    descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
                    wrapSelectorWheel = false
                    selectionDividerHeight = 0
                }
            },
            modifier = Modifier
                .weight(1f)
        )
    }
}