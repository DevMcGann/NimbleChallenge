package com.gsoft.nimblechalenge.presentation.resetPassword

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.gsoft.nimblechalenge.R
import com.gsoft.nimblechalenge.domain.services.MyNotificationService
import com.gsoft.nimblechalenge.ui.sharedComposables.CustomField
import com.gsoft.nimblechalenge.ui.sharedComposables.FullScreenBackground
import com.gsoft.nimblechalenge.ui.theme.customFontFamily

@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ResetScreen(
    navController: NavController,
    reset: (email: String) -> Unit,
    state : ResetPasswordState
) {

    val context = LocalContext.current

    val username = remember { mutableStateOf("dev.mcgann@gmail.com") }

    val postNotificationPermission =
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

    val notificationService = MyNotificationService(context = context)

    LaunchedEffect(key1 = true) {
        if (!postNotificationPermission.status.isGranted) {
            postNotificationPermission.launchPermissionRequest()
        }
    }

    FullScreenBackground(
        backgroundImage = painterResource(id = R.drawable.overlay)
    ) {

        if(state.showNotification){
            notificationService.showBasicNotification()
        }

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (logo, email, loginButton, loading, error, text) = createRefs()

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

                Text("Enter your email to receive instructions for resetting your password. ",
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.constrainAs(text) {
                    top.linkTo(logo.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

                CustomField(
                    modifier = Modifier
                        .constrainAs(email) {
                            top.linkTo(logo.bottom, margin = 90.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    textState = username
                )


                Button(
                    onClick = {reset(username.value) },
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .constrainAs(loginButton) {
                            top.linkTo(email.bottom, margin = 20.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .height(60.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ) {
                    Text("Reset", color = Color.Black, fontSize = 17.sp, fontFamily = customFontFamily, fontWeight = FontWeight.Bold)
                }


                if(state.isError){
                    state.error?.let {
                        Text(
                            text = it,
                            color = Color.Red,
                            fontFamily = customFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            modifier = Modifier.constrainAs(error) {
                                top.linkTo(loginButton.bottom, margin = 20.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                        )
                    }
                }//error

            }
        }
    }

    if (state.goToLogin){
        navController.navigate("home") {
            popUpTo("home") { inclusive = true }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun ResetScreenPreview() {
    ResetScreen(navController = rememberNavController(), reset = {}, state = ResetPasswordState())
}