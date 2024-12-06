package com.test.urovopaymentapp.presentation.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.test.urovopaymentapp.R
import com.test.urovopaymentapp.data.model.pos2.models.PosInputDatas
import com.test.urovopaymentapp.data.model.pos2.models.toJson
import com.test.urovopaymentapp.data.model.pos2.models.toPosInputDatas
import com.test.urovopaymentapp.presentation.navigation.Screen
import com.test.urovopaymentapp.presentation.view.dialogs.AlertType
import com.test.urovopaymentapp.presentation.view.dialogs.LoadingDialog
import com.test.urovopaymentapp.presentation.view.dialogs.MessageDialog
import com.test.urovopaymentapp.presentation.view.viewmodels.CardReaderViewModel
import com.test.urovopaymentapp.presentation.view.widgets.MyTopBar
import com.test.urovopaymentapp.utils.Result
import com.test.urovopaymentapp.utils.formatCardNumber
import kotlinx.coroutines.Dispatchers

private const val TAG = "CardReaderScreen"

@Composable
fun CardReaderScreen(
    navController: NavController,
    inpuDatas: String,
    viewModel: CardReaderViewModel
) {
    val posInputData by viewModel.posInputDatas.observeAsState(inpuDatas.toPosInputDatas())
    val resultPayment by viewModel.result.collectAsState()
    LaunchedEffect(Dispatchers.IO) {
        viewModel.initEmvListener(
            posInputDatas = posInputData
        )
    }
    Scaffold(topBar = { MyTopBar(stringResource(R.string.read_card)) }) { peddingValues ->
        Box(
            modifier = Modifier.padding(
                vertical = peddingValues.calculateTopPadding(), horizontal = 16.dp
            )
        ) {
            CardReaderBody(posInputData = posInputData)
            resultPayment.let {
                when (it) {
                    is Result.Success -> {
                        if (viewModel.hasNavigated.value == false) {
                            navController.navigate(Screen.ResultTransacction.createRoute(it.data.toJson())) {
                                popUpTo(Screen.Home.route)
                                launchSingleTop = true
                                viewModel.setHasNavigated()
                            }
                        }
                    }

                    is Result.Error -> {
                        MessageDialog(
                            title = stringResource(id = R.string.error_title),
                            text = it.exception.message
                                ?: stringResource(id = R.string.error_generic),
                            alertType = AlertType.Error,
                            onPrimaryButton = {
                                navController.popBackStack()
                            },
                            primaryButtonText = stringResource(
                                R.string.accept
                            )
                        )
                    }

                    is Result.Loading -> {
                        if (it.message.isNotEmpty()) {
                            LoadingDialog(isLoading = true, message = it.message)
                        }
                    }
                }
            }
        }
    }

}

@Composable
private fun CardReaderBody(
    posInputData: PosInputDatas
) {
    Column {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Total a pagar",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp, // Ajusta según el diseño
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = "$ ${posInputData.amt.toDouble().formatDouble()}",
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            Text(
                text = "Numero de Tarjeta",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp, // Ajusta según el diseño
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${posInputData.pan.formatCardNumber()}",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            InsertCardComponent(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun InsertCardComponent(modifier: Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.inser_card))
    LottieAnimation(
        modifier = Modifier
            .aspectRatio(1f),
        contentScale = ContentScale.Fit,
        composition = composition,
        iterations = LottieConstants.IterateForever,
        speed = 1.5f,
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewCardReaderScreen() {
    val dummyPaddingValues = PaddingValues(16.dp)
    val dummyPosInputDatas =
        PosInputDatas.Builder().setAmt("1500").setPan("1111222233334444").build()
    CardReaderBody(posInputData = dummyPosInputDatas)
}

fun Double.formatDouble(): String {
    return String.format("%.2f", this)
}