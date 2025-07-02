package com.stellan.challang.ui.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun loadTextFromAsset(context: Context, fileName: String): String {
    val text = remember(fileName) {
        context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
    return text
}