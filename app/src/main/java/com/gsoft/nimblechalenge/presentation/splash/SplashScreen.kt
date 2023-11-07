package com.gsoft.nimblechalenge.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gsoft.nimblechalenge.R
import com.gsoft.nimblechalenge.ui.sharedComposables.FullScreenBackground

@Composable
fun SplashScreen(
    state: SplashState,
    navController: NavController
) {
    FullScreenBackground(
        backgroundImage = painterResource(id = R.drawable.bgimage)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logowhite),
                contentDescription = "logo",
                modifier = Modifier.size(200.dp)
            )
        }
    }


    if (state.goToLogin){
        navController.navigate("login")
    }

    if(state.goToHome){
        navController.navigate("home")
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(state = SplashState(), navController = rememberNavController())
}