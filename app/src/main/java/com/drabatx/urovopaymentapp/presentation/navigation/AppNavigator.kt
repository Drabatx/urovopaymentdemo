package com.drabatx.urovopaymentapp.presentation.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.drabatx.urovopaymentapp.presentation.view.screens.InputAmountScreen

@Composable
fun AppNavigator(){
    Navigator(InputAmountScreen())
}