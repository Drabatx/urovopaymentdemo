package com.test.urovopaymentapp.presentation.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.test.urovopaymentapp.R
import com.test.urovopaymentapp.data.model.pos2.constants.PosTransType
import com.test.urovopaymentapp.data.model.pos2.models.PosInputDatas
import com.test.urovopaymentapp.data.model.pos2.models.toPosInputDatas
import com.test.urovopaymentapp.presentation.navigation.Screen
import com.test.urovopaymentapp.presentation.view.widgets.MyTopBar
import com.test.urovopaymentapp.utils.getCurrentDateTime
import com.test.urovopaymentapp.utils.getPan

@Composable
fun TransactionResultScreen(navController: NavController, inputData: String) {
    val posInputData = inputData.toPosInputDatas()

    Scaffold(topBar = { MyTopBar("Transaction Result") }) { peddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = peddingValues.calculateTopPadding())
                .background(Color(0xFFF0F0F0))
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                // Merchant Name Section
                item {
                    InitView(posInputData)
                }
            }

            // Confirm Button
            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue) // Replace with your custom color
            ) {
                Text(
                    text = stringResource(id = R.string.confirm),
                    color = Color.White
                )
            }
        }

    }


}

@Composable
private fun InitView(posInputData: PosInputDatas) {
    DetailSection(
        label = stringResource(id = R.string.merchant_name),
        value = "Urovo Test" // Replace with dynamic value
    )
    Divider(
        color = Color.Gray,
        thickness = 0.5.dp,
        modifier = Modifier.padding(horizontal = 10.dp)
    )

    // Merchant ID Section
    DetailSection(
        label = stringResource(id = R.string.merchant_id),
        value = "000000000000001" // Replace with dynamic value
    )
    Divider(
        color = Color.Gray,
        thickness = 0.5.dp,
        modifier = Modifier.padding(horizontal = 10.dp)
    )

    // Terminal ID Section
    DetailSection(
        label = stringResource(id = R.string.terminal_id),
        value = "88888888" // Replace with dynamic value
    )
    Spacer(modifier = Modifier.height(10.dp))

    // Card Number Section
    DetailSection(
        label = stringResource(id = R.string.card_no),
        value = getPan(posInputData.pan) // Replace with dynamic value
    )
    Divider(
        color = Color.Gray,
        thickness = 0.5.dp,
        modifier = Modifier.padding(horizontal = 10.dp)
    )

    // Trace Number Section
    DetailSection(
        label = stringResource(id = R.string.trace_no),
        value = posInputData.stIso8583?.field11 ?: "" // Replace with dynamic value
    )
    Divider(
        color = Color.Gray,
        thickness = 0.5.dp,
        modifier = Modifier.padding(horizontal = 10.dp)
    )

    // Payment Type Section
    DetailSection(
        label = stringResource(id = R.string.pay_type),
        value = PosTransType.getPosTransTypeName(
            context = LocalContext.current,
            posInputData.iTransNo
        ) // Replace with dynamic value
    )
    Divider(
        color = Color.Gray,
        thickness = 0.5.dp,
        modifier = Modifier.padding(horizontal = 10.dp)
    )

    // Authorization Code Section
    DetailSection(
        label = stringResource(id = R.string.auth_code),
        value = posInputData.stIso8583?.field38 ?: "" // Replace with dynamic value
    )
    Divider(
        color = Color.Gray,
        thickness = 0.5.dp,
        modifier = Modifier.padding(horizontal = 10.dp)
    )

    // RRN Code Section
    DetailSection(
        label = stringResource(id = R.string.rrn_code),
        value = posInputData.stIso8583?.field37 ?: "" // Replace with dynamic value
    )
    Divider(
        color = Color.Gray,
        thickness = 0.5.dp,
        modifier = Modifier.padding(horizontal = 10.dp)
    )

    // Date and Time Section
    DetailSection(
        label = stringResource(id = R.string.date_time),
        value = getCurrentDateTime() // Replace with dynamic value
    )
    Divider(
        color = Color.Gray, thickness = 0.5.dp
    )

    // Amount Section
    DetailSection(
        label = stringResource(id = R.string.amount),
        value = posInputData.amt, // Replace with dynamic value
        valueTextColor = Color.Red,
        valueTextSize = 18.sp
    )
}

@Composable
fun DetailSection(
    label: String,
    value: String,
    valueTextColor: Color = Color.Black,
    valueTextSize: TextUnit = TextUnit.Unspecified
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = value,
            color = valueTextColor,
            fontSize = valueTextSize,
            modifier = Modifier.weight(1f)
        )
    }
}