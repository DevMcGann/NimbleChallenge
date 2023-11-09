package com.gsoft.nimblechalenge.presentation.home

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun AnimatedShimmer() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    ShimmerGridItem(brush = brush)
}

@Composable
fun ShimmerGridItem(brush: Brush) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        val (date, avatar, today, bullets, title, subtitle) = createRefs()

        Spacer(
            modifier = Modifier
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth(fraction = 0.4f)
                .background(brush)
                .constrainAs(date) {
                    top.linkTo(parent.top, margin = 60.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                }
        )
        Spacer(
            modifier = Modifier
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth(fraction = 0.2f)
                .background(brush)
                .constrainAs(today) {
                    top.linkTo(date.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                }
        )
        Spacer(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(brush)
                .constrainAs(avatar) {
                    top.linkTo(date.top)
                    end.linkTo(parent.end, margin = 20.dp)
                }
        )



        Spacer(
            modifier = Modifier
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth(fraction = 0.2f)
                .background(brush)
                .constrainAs(bullets) {
                    bottom.linkTo(title.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                })


        Column(
            modifier = Modifier.constrainAs(title){
                bottom.linkTo(subtitle.top, margin = 20.dp)
                start.linkTo(parent.start, margin = 20.dp)
            }
        ) {
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth(fraction = 0.6f)
                    .background(brush)
            )
            Spacer(Modifier.height(10.dp))
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth(fraction = 0.3f)
                    .background(brush)
            )
        }

        Column(
            modifier = Modifier.constrainAs(subtitle){
                bottom.linkTo(parent.bottom, margin = 60.dp)
                start.linkTo(parent.start, margin = 20.dp)
            }
        ) {
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth(fraction = 0.8f)
                    .background(brush)
            )
            Spacer(Modifier.height(10.dp))
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth(fraction = 0.5f)
                    .background(brush)
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun ShimmerGridItemPreview() {
    ShimmerGridItem(
        brush = Brush.linearGradient(
            listOf(
                Color.LightGray.copy(alpha = 0.6f),
                Color.LightGray.copy(alpha = 0.2f),
                Color.LightGray.copy(alpha = 0.6f),
            )
        )
    )
}

@Composable
@Preview(showBackground = true)
fun AnimatedShimmerPreview() {
    AnimatedShimmer()
}
