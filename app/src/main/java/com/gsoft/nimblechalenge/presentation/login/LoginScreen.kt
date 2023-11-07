package com.gsoft.nimblechalenge.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gsoft.nimblechalenge.R
import com.gsoft.nimblechalenge.ui.sharedComposables.CustomField
import com.gsoft.nimblechalenge.ui.sharedComposables.FullScreenBackground
import com.gsoft.nimblechalenge.ui.theme.customFontFamily

@Composable
fun LoginScreen(
     state: LoginScreenState,
     login: (email: String, password: String) -> Unit,
     navController: NavController
) {
    val username = remember { mutableStateOf("dev.mcgann@gmail.com") }
    val password = remember { mutableStateOf("123456") }

    FullScreenBackground(
        backgroundImage = painterResource(id = R.drawable.overlay)
    ) {

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (logo, email, pass, loginButton, loading, error) = createRefs()

            if (state.isLoading){
                CircularProgressIndicator(
                    modifier = Modifier
                        .constrainAs(loading) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                        .size(40.dp),
                    color = Color.White
                )
            }else{
                Image(
                    painter = painterResource(id = R.drawable.logowhite),
                    contentDescription = "logo",
                    modifier = Modifier
                        .width(160.dp)
                        .height(60.dp)
                        .constrainAs(logo) {
                            top.linkTo(parent.top, margin = 160.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                CustomField(
                    modifier = Modifier
                        .constrainAs(email) {
                            top.linkTo(logo.bottom, margin = 90.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    textState = username
                )

                CustomField(
                    modifier = Modifier
                        .constrainAs(pass) {
                            top.linkTo(email.bottom, margin = 20.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    textState = password
                )

                Button(
                    onClick = {login(username.value, password.value) },
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .constrainAs(loginButton) {
                            top.linkTo(pass.bottom, margin = 20.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .height(60.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ) {
                    Text("Log in", color = Color.Black, fontSize = 17.sp, fontFamily = customFontFamily, fontWeight = FontWeight.Bold)
                }

                if(state.isError){
                    Text(
                        text = state.message,
                        color = Color.Red,
                        fontFamily = customFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        modifier = Modifier.constrainAs(error){
                            top.linkTo(loginButton.bottom, margin = 20.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    )
                }//error

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        state = LoginScreenState(),
        login = {email, password ->  {}},
        navController = rememberNavController()
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenLoadingPreview() {
    LoginScreen(
        state = LoginScreenState(isLoading = true),
        login = {email, password ->  {}},
        navController = rememberNavController()
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenErrorPreview() {
    LoginScreen(
        state = LoginScreenState(isError = true, message = "Incorrect credentials"),
        login = {email, password ->  {}},
        navController = rememberNavController()
    )
}

