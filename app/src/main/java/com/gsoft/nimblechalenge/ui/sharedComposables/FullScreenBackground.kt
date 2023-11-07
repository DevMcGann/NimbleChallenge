package com.gsoft.nimblechalenge.ui.sharedComposables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.gsoft.nimblechalenge.R

@Composable
fun FullScreenBackground(
    color : Color = Color.Black,
    backgroundImage: Painter? = null,
    content : @Composable () -> Unit

    ) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color),
    ) {
        backgroundImage?.let {
                Image(
                    painter = backgroundImage,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
        }
        content()
    }

}

@Preview(showBackground = true)
@Composable
fun FullScreenBackgroundPreview() {
    FullScreenBackground(
        backgroundImage = painterResource(id = R.drawable.overlay)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Hello World", color = Color.White, fontSize = 40.sp)
        }
    }
}