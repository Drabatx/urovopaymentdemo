package com.test.urovopaymentapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.test.urovopaymentapp.presentation.view.screens.CardReaderScreen
import com.test.urovopaymentapp.presentation.view.screens.HomeScreen
import com.test.urovopaymentapp.presentation.view.screens.InputAmountScreen
import com.test.urovopaymentapp.presentation.view.screens.TransactionResultScreen
import com.test.urovopaymentapp.presentation.view.viewmodels.CardReaderViewModel
import com.test.urovopaymentapp.presentation.view.viewmodels.InputAmountViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object InputAmount : Screen("input_amount_screen")
    object CardReader : Screen("card_reader_screen/{posInputDataString}") {
        fun createRoute(posInputDataString: String): String {
            return "card_reader_screen/$posInputDataString"
        }
    }
    object ResultTransacction : Screen("result_transaction_screen/{posInputDataString}") {
        fun createRoute(posInputDataString: String): String {
            return "result_transaction_screen/$posInputDataString"
        }
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.InputAmount.route) {
            val inputAmountViewModel: InputAmountViewModel = hiltViewModel()
            InputAmountScreen(navController, inputAmountViewModel)
        }
        composable(route = Screen.CardReader.route) { backStackEntry ->
            val cardReaderViewModel: CardReaderViewModel = hiltViewModel()
            val posInputDataString = backStackEntry.arguments?.getString("posInputDataString") ?: ""
            CardReaderScreen(navController, posInputDataString, viewModel = cardReaderViewModel)
        }
        composable(route = Screen.ResultTransacction.route) { backStackEntry ->
            val posInputDataString = backStackEntry.arguments?.getString("posInputDataString") ?: ""
            TransactionResultScreen(navController, posInputDataString)
        }
    }
}