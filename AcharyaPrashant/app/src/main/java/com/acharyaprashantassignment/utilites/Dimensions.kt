package com.acharyaprashantassignment.utilites

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val dp_2: Dp = 2.dp,
    val dp_8: Dp = 8.dp,
    val dp_20: Dp = 20.dp

)

val LocalSpacing = compositionLocalOf { Dimensions() }
