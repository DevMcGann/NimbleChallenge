package com.gsoft.nimblechalenge.presentation.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gsoft.nimblechalenge.ui.theme.customFontFamily

@Composable
fun TitleAndSubtitle(
    title : String = "Title",
    subtitle : String = "Subtitle",
    index : Int = 0,
    size : Int = 0,
    navController: NavController,
    coverImage : String? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        //title & Subtitle
        Column(
            modifier = Modifier.fillMaxWidth(fraction = .65f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            //title

            Circles(
                index = index,
                size = size
            )

            Text(
                text = title,
                color = Color.White,
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                lineHeight = 34.sp,
                letterSpacing = (-.5).sp
            )


            Spacer(modifier = Modifier.height(10.dp))

            //subtitle
            Text(
                text = subtitle,
                color = Color.White,
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
                lineHeight = 22.sp,
                letterSpacing = (-.4).sp
            )
        }

        //button
        Column(
            modifier = Modifier.fillMaxWidth(fraction = .35f),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = {  }) {
                Box (
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = "see survey",
                        tint = Color.Black,
                        modifier = Modifier.size(50.dp)
                            .clickable { navController.navigate("Details?title=${title}&subtitle=${subtitle}&image=${coverImage} ") },
                        )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFF)
@Composable
fun TitleAndSubtitlePreview() {
    TitleAndSubtitle(
        navController = rememberNavController(),
    )
}