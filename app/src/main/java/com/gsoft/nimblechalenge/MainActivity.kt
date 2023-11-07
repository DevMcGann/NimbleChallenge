package com.gsoft.nimblechalenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gsoft.nimblechalenge.presentation.home.HomeScreen
import com.gsoft.nimblechalenge.presentation.login.LoginScreen
import com.gsoft.nimblechalenge.presentation.login.LoginViewModel
import com.gsoft.nimblechalenge.presentation.splash.SplashScreen
import com.gsoft.nimblechalenge.presentation.splash.SplashViewModel
import com.gsoft.nimblechalenge.ui.theme.NimbleChalengeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        setContent {
            val navController = rememberNavController()

            val loginViewModel = hiltViewModel<LoginViewModel>()
            val loginState = loginViewModel.state

            val splashViewModel = hiltViewModel<SplashViewModel>()
            val splashState = splashViewModel.state

            NimbleChalengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "splash") {

                        composable("splash") {
                            SplashScreen(
                                state = splashState.value,
                                navController = navController
                            )
                            BackHandler(false){}
                        }

                        composable("login") {
                            LoginScreen(
                                state = loginState.value,
                                login = loginViewModel::login,
                                navController = navController
                            )
                            BackHandler(false){}
                        }

                        composable("home") {
                            HomeScreen(
                                navController = navController
                            )
                            BackHandler(false){}
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NimbleChalengeTheme {
        Greeting("Android")
    }
}