package com.gsoft.nimblechalenge.ui.sharedComposables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.gsoft.nimblechalenge.R

@Composable
fun SurveyFullScreenBackground(
    color : Color = Color.Black,
    backgroundImage: String? = null,
    content : @Composable () -> Unit

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color),
        ) {
            backgroundImage?.let {
                AsyncImage(
                    model = backgroundImage,
                    contentDescription = "cover image",
                    placeholder = painterResource(id = R.drawable.bgimage),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.4f)
                )
            }
            content()
        }
}