package com.test.urovopaymentapp.presentation.view.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Backspace
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun InputAmountKeyboard(modifier: Modifier, onButtonClick: (String, Int) -> Unit) {
    val num = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "", "Clear", "OK")
    val buttonActions = listOf(
        ACTION_NUM, ACTION_NUM, ACTION_NUM,
        ACTION_NUM, ACTION_NUM, ACTION_NUM,
        ACTION_NUM, ACTION_NUM, ACTION_NUM,
        ACTION_NUM, ACTION_NUM, ACTION_DELETE,
        ACTION_CLEAR, ACTION_ENTER
    )
    Column(modifier = modifier.background(Color.White)) {
        Row(modifier = Modifier.weight(1f)) {
            ButtonKeyBoard(
                modifier = Modifier.weight(1f),
                onClick = { onButtonClick(num[0], buttonActions[0]) }) {
                Text(text = num[0])
            }
            ButtonKeyBoard(
                modifier = Modifier.weight(1f),
                onClick = { onButtonClick(num[1], buttonActions[1]) }) {
                Text(text = num[1])
            }
            ButtonKeyBoard(
                modifier = Modifier.weight(1f),
                onClick = { onButtonClick(num[2], buttonActions[2]) }) {
                Text(text = num[2])
            }
            ButtonKeyBoard(
                modifier = Modifier.weight(1f),
                backgroundColor = Color(0xFFCD3D3D),
                onClick = { onButtonClick(num[11], buttonActions[11]) }) {
                Icon(imageVector = Icons.AutoMirrored.Outlined.Backspace, contentDescription = "borrar", tint = Color.White)
            }
        }
        Row(modifier = Modifier.weight(1f)) {
            ButtonKeyBoard(
                modifier = Modifier.weight(1f),
                onClick = { onButtonClick(num[3], buttonActions[3]) }) {
                Text(text = num[3])
            }
            ButtonKeyBoard(
                modifier = Modifier.weight(1f),
                onClick = { onButtonClick(num[4], buttonActions[4]) }) {
                Text(text = num[4])
            }
            ButtonKeyBoard(
                modifier = Modifier.weight(1f),
                onClick = { onButtonClick(num[5], buttonActions[5]) }) {
                Text(text = num[5])
            }
            ButtonKeyBoard(
                modifier = Modifier.weight(1f),
                contentColor = Color.White,
                backgroundColor = Color(0xFFF7CE19),
                onClick = { onButtonClick(num[12], buttonActions[12]) }) {
                Text(text = num[12])
            }
        }
        Row(modifier = Modifier.weight(2f)){
            Column(modifier = Modifier.weight(3f)) {
                Row(modifier = Modifier.weight(1f)) {
                    ButtonKeyBoard(
                        modifier = Modifier.weight(1f),
                        onClick = { onButtonClick(num[6], buttonActions[6]) }) {
                        Text(text = num[6])
                    }
                    ButtonKeyBoard(
                        modifier = Modifier.weight(1f),
                        onClick = { onButtonClick(num[7], buttonActions[7]) }) {
                        Text(text = num[7])
                    }
                    ButtonKeyBoard(
                        modifier = Modifier.weight(1f),
                        onClick = { onButtonClick(num[8], buttonActions[8]) }) {
                        Text(text = num[8])
                    }
                }
                Row(modifier = Modifier.weight(1f)) {
                    ButtonKeyBoard(
                        modifier = Modifier.weight(1f),
                        onClick = { onButtonClick(num[9], buttonActions[9]) }) {
                        Text(text = num[9])
                    }

                }
            }
            ButtonKeyBoard(
                modifier = Modifier.weight(1f),
                backgroundColor = Color(0xFF2ACC29),
                contentColor = Color.White,
                onClick = { onButtonClick(num[13], buttonActions[13]) }) {
                Text(text = num[13])
            }
        }
    }
}


@Composable
fun ButtonKeyBoard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = Color(0xFFAAAAAA), // Nuevoparámetro para el color del borde
    backgroundColor: Color = Color.White, // Nuevo parámetro para el color de fondo
    contentColor: Color = Color(0xFF333333), // Nuevo parámetro para el color del contenido
    content: @Composable () -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .border(width = 1.dp, color = borderColor, shape = RectangleShape)
            .background(color = backgroundColor) // Aplica el color de fondo
            .padding(0.dp),
        shape = RectangleShape,
        colors = ButtonDefaults.outlinedButtonColors(contentColor = contentColor), // Usa el color de contenido proporcionado        contentPadding = PaddingValues(0.dp) // Sin relleno interno
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            content()
        }
    }
}

// Constants for the actions
const val ACTION_DELETE = 0x2016
const val ACTION_CANCEL = 0x2017
const val ACTION_CLEAR = 0x2018
const val ACTION_ENTER = 0x2019
const val ACTION_NUM = 0x2020
