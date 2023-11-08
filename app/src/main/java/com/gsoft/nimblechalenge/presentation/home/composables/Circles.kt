package com.gsoft.nimblechalenge.presentation.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gsoft.nimblechalenge.ui.sharedComposables.NavCircle

@Composable
fun Circles(
    index : Int = 0,
    size : Int = 0
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ){
        repeat(size){
            NavCircle(
                isFilled = it == index
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun CirclesPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Circles(
            index = 0,
            size = 2
        )
    }

}