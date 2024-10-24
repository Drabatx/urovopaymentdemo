package com.drabatx.urovopaymentapp.presentation.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.drabatx.urovopaymentapp.presentation.view.screens.CardScreen
import com.drabatx.urovopaymentapp.presentation.view.screens.HomeScreen

@Composable
fun AppNavigator(){
    Navigator(CardScreen())
}