package com.gsoft.nimblechalenge.ui.sharedComposables

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gsoft.nimblechalenge.ui.theme.customFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomField(
    modifier: Modifier = Modifier,
    textState: MutableState<String>,
    roundedRadius: Dp = 20.dp,
    textColor: Color = Color.White,
    containerColor: Color = Color.DarkGray,
    isPassword: Boolean = false,
) {
    TextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        shape = RoundedCornerShape(roundedRadius),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.textFieldColors(
            textColor = textColor,
            containerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,

            ),
        textStyle = TextStyle(
            fontFamily = customFontFamily,
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal,
        ),
        modifier = modifier
            .background(Color.Transparent, shape = RoundedCornerShape(20.dp))
            .fillMaxWidth(.9f)
            .height(60.dp)

    )
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewCustomField() {
    CustomField(textState = mutableStateOf("Testing"))
}