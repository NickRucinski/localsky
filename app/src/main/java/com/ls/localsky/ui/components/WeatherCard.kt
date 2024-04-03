package com.ls.localsky.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ls.localsky.R
import com.ls.localsky.WeatherAPI
import java.text.SimpleDateFormat
import java.util.Calendar


@Composable
fun WeatherCard(
    backgroundColor: Color,
    modifier: Modifier = Modifier
){
    Card(
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            /*Time of day*/
            Text(
                text = "Today ${
                    SimpleDateFormat("HH:mm").format(Calendar.getInstance().time)
                }",
                modifier = Modifier.align(Alignment.End),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            /*Weather icon*/
            Image(
                painter = painterResource(id = R.drawable.snow),
                contentDescription = null,
                modifier = Modifier.width(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            /*Temperature*/
            Text(
                /*Retrieve from weather data*/
                text = "Temperature: ",
                fontSize = 50.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            /*Weather description*/
            Text(
                /*Retrieve from weather data*/
                text = "Description: ",
                fontSize = 50.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(32.dp))


        }

    }

}
