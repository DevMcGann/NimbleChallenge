package com.gsoft.nimblechalenge.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.gsoft.nimblechalenge.R
import com.gsoft.nimblechalenge.presentation.home.composables.DateAndAvatar
import com.gsoft.nimblechalenge.presentation.home.composables.TitleAndSubtitle

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController, //if someday implement the survey... Will need this.
    state : HomeScreenState,
    getSurvey : () -> Unit,
    getAllSurveys : (page:Int) -> Unit,
    getDate : () -> String
) {

    LaunchedEffect(key1 = null) {
       // getSurvey()
        getAllSurveys(state.page)
    }


    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        state.surveys?.size ?: 0
    }

    if(state.isLoading){
        AnimatedShimmer()
    }else{
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            HorizontalPager(
                modifier = Modifier,
                state = pagerState,
                pageSpacing = 0.dp,
                userScrollEnabled = true,
                reverseLayout = false,
                contentPadding = PaddingValues(0.dp),
                beyondBoundsPageCount = 0,
                pageSize = PageSize.Fill,
                flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
                key = { index -> state.surveys?.get(index)?.id ?: 0 },
                pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                    Orientation.Horizontal
                ),
                pageContent = { index ->

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black)
                    ){
                        AsyncImage(
                            model = state.surveys?.get(index)?.cover_image_url,
                            contentDescription = "cover image",
                            placeholder = painterResource(id = R.drawable.bgimage),
                            error = painterResource(id = R.drawable.bgimage),
                            fallback = painterResource(id = R.drawable.bgimage),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .alpha(0.4f)
                        )

                        Column(
                            modifier = Modifier.fillMaxSize()
                                .padding(horizontal = 20.dp, vertical = 50.dp)
                                .clickable { navController.navigate("Details?title=${state.surveyData?.data?.get(index)?.attributes?.title?:""}&subtitle=${state.surveyData?.data?.get(index)?.attributes?.description?:""}&image=${state.surveyData?.data?.get(index)?.attributes?.cover_image_url?:"" } ") },
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            DateAndAvatar(date = getDate())
                            TitleAndSubtitle(
                                title = state.surveys?.get(index)?.title?:"",
                                subtitle = state.surveys?.get(index)?.description?:"",
                                index = index,
                                size = state.surveys?.size ?: 0
                            )
                        }//column
                    }//box
                }//pageContent
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewFeedScreen() {
    HomeScreen(
        navController = rememberNavController(),
        state = HomeScreenState(),
        getSurvey = {},
        getAllSurveys = {},
        getDate = {"Tuesday, November 7"}
    )
}