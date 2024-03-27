package com.ls.localsky.loginScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ls.localsky.R
import com.ls.localsky.components.EmailInput
import com.ls.localsky.components.PasswordInput
import com.ls.localsky.components.TitleText

@Composable
fun LoginScreen() {

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ){
        Column(modifier = Modifier.fillMaxSize()){

            TitleText(value = "Login to " + stringResource(R.string.app_name))
            Spacer(modifier=Modifier.height(50.dp))
            EmailInput(labelValue = stringResource(id = R.string.login))

            PasswordInput(labelValue = stringResource(id = R.string.password))

        }

    }

}

@Preview
@Composable
fun DefaultPreviewOfLoginScreen() {
    LoginScreen()
}