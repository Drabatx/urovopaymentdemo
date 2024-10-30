package com.test.urovopaymentapp.presentation.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.test.urovopaymentapp.presentation.navigation.Screen
import com.test.urovopaymentapp.presentation.view.widgets.MyTopBar

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = {
        MyTopBar("Bienvenido")
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.weight(1f))
            Button(onClick = {
                navController.navigate(Screen.InputAmount.route)
            }) {
                Text(text = "Pay withUrovo")
            }
            Spacer(Modifier.weight(1f))
        }
    }
}
