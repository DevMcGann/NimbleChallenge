package com.gsoft.nimblechalenge.ui.sharedComposables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NavCircle(
    isFilled : Boolean = false
) {

    fun fillColor () : Color {
        return if (isFilled) Color.White else Color.Transparent
    }

    Spacer(
        modifier = Modifier
            .size(12.dp)
            .clip(CircleShape)
            .background(fillColor())
            .border(1.dp, Color.White, CircleShape)
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFF)
@Composable
fun NavCirclePreview() {
    NavCircle()
}

