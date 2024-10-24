package com.drabatx.urovopaymentapp.presentation.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import com.drabatx.urovopaymentapp.R
import com.drabatx.urovopaymentapp.presentation.view.viewmodels.CardReaderViewModel
import com.drabatx.urovopaymentapp.presentation.view.widgets.MyTopBar

class CardScreen : Screen {
    @Composable
    override fun Content() {
        val monto by remember { mutableStateOf("0.00") }
        val noTarjeta by remember { mutableStateOf("") }
        val viewModel = viewModel<CardReaderViewModel>()
        Scaffold(topBar = { MyTopBar("Leer Tarjeta") }) { peddingValues ->
            Column(
                modifier = Modifier.padding(
                    top = peddingValues.calculateTopPadding(),
                    start = 16.dp,
                    end = 16.dp
                )
            ) {
                Row {
                    Text(text = "Monto")
                    Text(text = monto)
                }
                Row {
                    Text(text = "No. de Tarjeta")
                    Text(text = noTarjeta)
                }
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.icon_card_9100_en),
                        contentDescription = stringResource(R.string.please_read_the_card_in_the_following_way),
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                }
                Button(onClick = {
                    viewModel.insertCard()
                }) {
                    Text(text = "Leer Tarjeta")
                }
            }
        }
    }
}