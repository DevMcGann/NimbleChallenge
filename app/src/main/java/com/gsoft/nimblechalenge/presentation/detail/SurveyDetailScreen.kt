package com.gsoft.nimblechalenge.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gsoft.nimblechalenge.data.model.SurveyResponse
import com.gsoft.nimblechalenge.presentation.home.composables.TitleAndSubtitle
import com.gsoft.nimblechalenge.ui.sharedComposables.SurveyFullScreenBackground

@Composable
fun SurveyDetailScreen(
    title : String = "Survey Title",
    subtitle : String = "Survey Subtitle",
    image : String = "",
    navController: NavController
) {
    SurveyFullScreenBackground(
        backgroundImage = image
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, color = Color.White, fontSize = 30.sp)
            Text(text = subtitle, color = Color.White, fontSize = 20.sp)
            Spacer(Modifier.height(20.dp))
            Text(text = "This feature is not implemented for this Challenge", color = Color.White, fontSize = 16.sp, textAlign = TextAlign.Center)
            Spacer(Modifier.height(30.dp))
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription ="navigate back", tint = Color.White, modifier = Modifier.size(50.dp))
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun SurveyDetailScreenPreview() {
    SurveyDetailScreen(
        navController = rememberNavController(),
    )
}