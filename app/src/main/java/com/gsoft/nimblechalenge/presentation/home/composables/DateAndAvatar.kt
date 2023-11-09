package com.gsoft.nimblechalenge.presentation.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gsoft.nimblechalenge.ui.theme.customFontFamily

@Composable
fun DateAndAvatar(
    date : String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        //date
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = date,//getDate(),
                color = Color.White,
                fontFamily = customFontFamily,
                fontSize = 13.sp
            )
        }

        //Today and Avatar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "TODAY",
                    color = Color.White,
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 34.sp
                )
            }

            Column() {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription ="Avatar",
                    tint = Color.White,
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun DateAndAvatarPreview() {
    DateAndAvatar(date = "Tuesday, Nombemver 4")
}