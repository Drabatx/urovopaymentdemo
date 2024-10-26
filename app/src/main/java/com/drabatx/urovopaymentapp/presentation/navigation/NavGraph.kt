package com.drabatx.urovopaymentapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.drabatx.urovopaymentapp.presentation.view.screens.CardReaderScreen
import com.drabatx.urovopaymentapp.presentation.view.screens.InputAmountScreen
import com.drabatx.urovopaymentapp.presentation.view.viewmodels.CardReaderViewModel
import com.drabatx.urovopaymentapp.presentation.view.viewmodels.InputAmountViewModel

sealed class Screen(val route: String) {
    object InputAmount : Screen("input_amount_screen")
    object CardReader : Screen("card_reader_screen/{posInputDataString}") {
        fun createRoute(posInputDataString: String): String {
            return "card_reader_screen/$posInputDataString"
        }
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController) {


    NavHost(navController = navController, startDestination = Screen.InputAmount.route) {
        composable(route = Screen.InputAmount.route) {
            val inputAmountViewModel: InputAmountViewModel = hiltViewModel()
            InputAmountScreen(navController, inputAmountViewModel)
        }
        composable(route = Screen.CardReader.route) { backStackEntry ->
            val cardReaderViewModel: CardReaderViewModel = hiltViewModel()
            val posInputDataString = backStackEntry.arguments?.getString("posInputDataString") ?: ""
            CardReaderScreen(navController, posInputDataString, viewModel = cardReaderViewModel)
        }
    }
}