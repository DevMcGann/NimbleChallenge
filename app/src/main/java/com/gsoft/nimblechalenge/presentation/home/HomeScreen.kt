package com.gsoft.nimblechalenge.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.gsoft.nimblechalenge.presentation.error.ErrorScreen
import com.gsoft.nimblechalenge.presentation.home.composables.DateAndAvatar
import com.gsoft.nimblechalenge.presentation.home.composables.NoContent
import com.gsoft.nimblechalenge.presentation.home.composables.TitleAndSubtitle


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    state : HomeScreenState,
    getSurvey : () -> Unit,
    getDate : () -> String,
    nextPage : () -> Unit
) {

    LaunchedEffect(key1 = null) {
        getSurvey()
    }


    val pullRefreshState = rememberPullRefreshState(state.isLoading, { nextPage() })


    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        state.surveys.size
    }


    if(state.isLoading){
        AnimatedShimmer()
    }else{

        if(state.isError){
            ErrorScreen(navController = navController)
            return
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            if(state.surveys.isEmpty()){
                NoContent {
                    getSurvey()
                }
            }else{
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
                    //key = { index -> state.surveys[index]?.id ?: 0 },
                    pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(orientation = Orientation.Horizontal),
                    pageContent = { index ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black)
                        ){
                            AsyncImage(
                                model = state.surveys[index]?.cover_image_url,
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
                                modifier = Modifier
                                    .fillMaxSize()
                                    .pullRefresh(pullRefreshState, true)
                                    .verticalScroll(rememberScrollState())
                                    .padding(horizontal = 20.dp, vertical = 50.dp),
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                DateAndAvatar(date = getDate())
                                TitleAndSubtitle(
                                    title = state.surveys[index]?.title?:"",
                                    subtitle = state.surveys[index]?.description?:"",
                                    index = index,
                                    size = state.surveys.size,
                                    navController = navController,
                                    coverImage = state.surveys[index]?.cover_image_url?:"",
                                )
                            }//column
                        }//box
                    }//pageContent
                )
            }

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
        getDate = {"Tuesday, November 7"},
        nextPage = {}
    )
}