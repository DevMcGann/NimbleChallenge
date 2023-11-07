package com.gsoft.nimblechalenge.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.gsoft.nimblechalenge.ui.sharedComposables.FullScreenBackground
import com.gsoft.nimblechalenge.ui.sharedComposables.NavCircle
import com.gsoft.nimblechalenge.ui.theme.customFontFamily

@Composable
fun HomeScreen(
    navController: NavController,
    state : HomeScreenState
){
    FullScreenBackground(
        backgroundImage = painterResource(id = R.drawable.bgimage)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 40.dp),
        ) {
            val (date, surveyData) = createRefs()

            Box(
                modifier = Modifier.constrainAs(date){
                top.linkTo(parent.top)
                start.linkTo(parent.start, margin = 12.dp)
                end.linkTo(parent.end, margin = 12.dp)
                }
            ){
                Column(modifier = Modifier.fillMaxWidth()) {
                    //date
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Text(
                            text = "TUESDAY, NOVEMBER 7",
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

            Box(
                modifier = Modifier.constrainAs(surveyData){
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                }
            ){
                Column() {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        repeat(3){
                            NavCircle()
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))


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
                            Text(
                                text = "Career training and development",
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
                                text = "We would like to know what are your goals and skills you wanted...",
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
                                    )
                                }
                            }
                        }
                    }


                }
            }

        }
    }
}




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(
        navController = rememberNavController(),
        state = HomeScreenState()
    )
}