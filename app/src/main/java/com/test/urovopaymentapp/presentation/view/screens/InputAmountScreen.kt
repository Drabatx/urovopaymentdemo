package com.test.urovopaymentapp.presentation.view.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.test.urovopaymentapp.R
import com.test.urovopaymentapp.data.model.pos2.models.toJson
import com.test.urovopaymentapp.presentation.navigation.Screen
import com.test.urovopaymentapp.presentation.view.viewmodels.InputAmountViewModel
import com.test.urovopaymentapp.presentation.view.widgets.ACTION_CLEAR
import com.test.urovopaymentapp.presentation.view.widgets.ACTION_DELETE
import com.test.urovopaymentapp.presentation.view.widgets.ACTION_ENTER
import com.test.urovopaymentapp.presentation.view.widgets.ACTION_NUM
import com.test.urovopaymentapp.presentation.view.widgets.InputAmountKeyboard
import com.test.urovopaymentapp.presentation.view.widgets.MyTopBar

val DEFAULT_AMOUNT = "0.00"
val MAX_AMOUNT = 9999999


@Composable
fun InputAmountScreen(navController: NavController, viewModel: InputAmountViewModel) {
    var text by remember { mutableStateOf(DEFAULT_AMOUNT) }
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
            Text(
                text = stringResource(id = R.string.amount_input), // Reemplaza R.string.amount_input con el ID de tu recurso de string
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .align(Alignment.End)
                    .padding(bottom = 20.dp, end = 10.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "$",
                    fontSize = 48.sp,
                    color = Color.DarkGray, // Reemplaza con el color deseado
                    modifier = Modifier
                        .wrapContentSize(align = Alignment.Center)
                        .alignByBaseline()
                        .padding(end = 5.dp)
                )
                Text(
                    text = text,
                    fontSize = 48.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray, // Reemplaza con el color deseado
                    modifier = Modifier
                        .wrapContentSize(align = Alignment.Center)
                        .alignByBaseline()
                        .padding(end = 5.dp)
                )
            }

            InputAmountKeyboard(modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f), onButtonClick = { outText, action ->
                when (action) {
                    ACTION_DELETE -> {
                        if (text.length > 1) {
                            text = removeLastDigit(text)
                        } else {
                            text = DEFAULT_AMOUNT
                        }
                    }

                    ACTION_NUM -> {
                        if (text.toDouble() <= MAX_AMOUNT)
                            text = updateAmount(text, outText[0])
                    }

                    ACTION_CLEAR -> {
                        text = DEFAULT_AMOUNT
                    }

                    ACTION_ENTER -> {
                        if (text.toDouble() > 0) {
                            viewModel.setAmount(text.toDouble())
                            navController.navigate(Screen.CardReader.createRoute(viewModel.inputData.value.toJson()))
                        }
                    }
                }
            })
        }
    }
}

fun updateAmount(currentAmount: String, inputDigit: Char): String {
    // Remover el punto decimal y agregar el nuevo dígito
    val newAmount = currentAmount.replace(".", "") + inputDigit
    // Insertar el punto decimal antes de los últimos dos dígitos y formatear correctamente
    val formattedAmount = newAmount.trimStart('0').padStart(3, '0').let {
        it.substring(0, it.length - 2) + "." + it.takeLast(2)
    }
    Log.i("TAG", "updateAmount: Entrada: $currentAmount salida: $formattedAmount")
    return formattedAmount
}

fun removeLastDigit(currentAmount: String): String {
    // Eliminar el punto y el último dígito de la cantidad
    val newAmount = currentAmount.replace(".", "").dropLast(1)
    // Insertar el punto decimal antes de los dos últimos dígitos y formatear
    val formattedAmount = newAmount.padStart(3, '0').let {
        it.substring(0, it.length - 2) + "." + it.takeLast(2)
    }
    Log.i("TAG", "removeLastDigit: Entrada: $currentAmount salida: $formattedAmount")
    return formattedAmount
}
