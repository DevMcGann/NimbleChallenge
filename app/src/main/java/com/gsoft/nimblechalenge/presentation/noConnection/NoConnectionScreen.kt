package com.gsoft.nimblechalenge.presentation.noConnection

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gsoft.nimblechalenge.R
import com.gsoft.nimblechalenge.ui.sharedComposables.FullScreenBackground

@Composable
fun NoConnectionScreen() {
    FullScreenBackground(
        backgroundImage = painterResource(id = R.drawable.noconnection)
    ) {

        val context = LocalContext.current


        Column(
            modifier = Modifier.fillMaxSize()
                .padding(vertical = 30.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { (context as? Activity)?.finish() }) {
                Text("Exit App")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoConnectionScreenPreview() {
    NoConnectionScreen()
}