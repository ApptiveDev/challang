package com.stellan.challang.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.*

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
    val years  = (1900..currentYear - 19).map { it.toString() }
    val months = (1..12).map { "%02d".format(it) }
    val days   = (1..31).map { "%02d".format(it) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = Color(0xFFDDF0F0), shape = RoundedCornerShape(6.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        WheelPicker(
            values        = years,
            selectedIndex = years.indexOf(year.toString()),
            onValueChange = { idx -> onYearChange(years[idx].toInt()) },
            itemHeight    = 56.dp,
            modifier      = Modifier.weight(1f)
        )
        VerticalDivider(
            color     = Color(0xFFD9D9D9),
            thickness = 2.dp,
            modifier  = Modifier.height(40.dp)
        )
        WheelPicker(
            values        = months,
            selectedIndex = month,
            onValueChange = { idx -> onMonthChange(idx) },
            itemHeight    = 56.dp,
            modifier      = Modifier.weight(1f)
        )
        VerticalDivider(
            color     = Color(0xFFD9D9D9),
            thickness = 2.dp,
            modifier  = Modifier.height(40.dp)
        )
        WheelPicker(
            values        = days,
            selectedIndex = day - 1,
            onValueChange = { idx -> onDayChange(idx + 1) },
            itemHeight    = 56.dp,
            modifier      = Modifier.weight(1f)
        )
    }
}