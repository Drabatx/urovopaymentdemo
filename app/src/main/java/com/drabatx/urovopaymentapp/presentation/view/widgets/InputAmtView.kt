package com.drabatx.urovopaymentapp.presentation.view.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.drabatx.urovopaymentapp.R

@Composable
fun InputAmtView(modifier: Modifier,onButtonClick: (String, Int) -> Unit) {
    val num = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "", "Clear", "OK")
    val buttonActions = listOf(
        ACTION_NUM, ACTION_NUM, ACTION_NUM,
        ACTION_NUM, ACTION_NUM, ACTION_NUM,
        ACTION_NUM, ACTION_NUM, ACTION_NUM,
        ACTION_NUM, ACTION_NUM, ACTION_DELETE,
        ACTION_CLEAR, ACTION_ENTER
    )
    Column(modifier = modifier.padding(16.dp)) {
        // Creating a 4x4 grid of buttons
        for (i in 0..3) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {


                for (j in 0..3) {
                    val index = i * 4 + j
                    if (index < num.size) {
                        val text = num[index]
                        val action = buttonActions[index]

                        if (index == 11) {
                            // Special case for the image button
                            IconButtonClear(onClick = { onButtonClick("", action) })
                        } else {
                            Button(
                                onClick = { onButtonClick(text, action) },
                                modifier = Modifier
                                    .size(80.dp)
                                    .background(
                                        color = when (index) {
                                            12 -> Color.Red // Background for 'Clear'
                                            13 -> Color.Green // Background for 'OK'
                                            else -> Color.LightGray
                                        },
                                        shape = RoundedCornerShape(8.dp)
                                    )
                            ) {
                                Text(
                                    text = text,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun IconButtonClear(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Red
        ),
        modifier = Modifier
            .size(80.dp)
            .background(Color.Transparent, shape = RoundedCornerShape(8.dp))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.shouyin_del), // Replace with your icon resource
            contentDescription = "Delete",
            modifier = Modifier.fillMaxSize(),
            tint = Color.Red
        )
    }
}

// Constants for the actions
const val ACTION_DELETE = 0x2016
const val ACTION_CANCEL = 0x2017
const val ACTION_CLEAR = 0x2018
const val ACTION_ENTER = 0x2019
const val ACTION_NUM = 0x2020
