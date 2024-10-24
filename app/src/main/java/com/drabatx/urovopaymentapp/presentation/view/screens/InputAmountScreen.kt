package com.drabatx.urovopaymentapp.presentation.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.drabatx.urovopaymentapp.presentation.view.widgets.InputAmtView
import com.drabatx.urovopaymentapp.presentation.view.widgets.MyTopBar

class InputAmountScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        var text by remember { mutableStateOf("") }
        Scaffold(topBar = {
            MyTopBar("Ingrese monto")
        }) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = text,
                    onValueChange = { newText ->
                        // Filtrar para permitir solo números, punto decimal ysigno negativo
                        val filteredText = newText.filter { it.isDigit() || it == '.' || it == '-' }
                        // Permitir solo un punto decimal y un signo negativo al principio
                        if (filteredText.count { it == '.' } > 1 ||
                            (filteredText.contains('-') && filteredText.indexOf('-') != 0)
                        ) {
                            return@TextField // No permitir la entrada si hay más de un punto o un signo negativo en una posición inválida
                        }
                        text = filteredText
                    },
                    label = { Text("Monto", fontSize = 48.sp) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color.Transparent,
                            shape = RoundedCornerShape(0.dp)
                        ) // Sin borde
                        .padding(0.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 48.sp)
                )
                InputAmtView(modifier = Modifier.fillMaxWidth(), onButtonClick = { outtext, action ->
                    text = outtext
                })
//                Row {
//                    Button(
//                        onClick = {
//                            text = "" /* Acción al hacer clic en Limpiar */
//                        },
//                        modifier = Modifier
//                            .weight(1f)
//                            .padding(8.dp),
//                        colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
//                    ) {
//                        Text(
//                            "Limpiar",
//                            fontSize = 24.sp,
//                            color = MaterialTheme.colorScheme.primary
//                        ) // Tamaño de texto grande
//                    }
//
//                    Button(
//                        onClick = {
//                            /**
//                             * TODO Agrear una validación de monto
//                             */
//                            navigator.push(CardScreen())
//                        },
//                        modifier = Modifier
//                            .weight(1f)
//                            .padding(8.dp),
//                        colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
//                    ) {
//                        Text(
//                            "Confirmar",
//                            fontSize = 24.sp,
//                            color = MaterialTheme.colorScheme.primary
//                        ) // Tamaño de texto grande
//                    }
//                }
                Spacer(Modifier.weight(1f))
            }
        }
    }
}