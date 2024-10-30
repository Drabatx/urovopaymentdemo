package com.test.urovopaymentapp.presentation.view.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.test.urovopaymentapp.data.model.pos2.models.toPosInputDatas
import com.test.urovopaymentapp.domain.model.EmvReason
import com.test.urovopaymentapp.presentation.view.dialogs.LoadingDialog
import com.test.urovopaymentapp.presentation.view.dialogs.MessageDialog
import com.test.urovopaymentapp.presentation.view.viewmodels.CardReaderViewModel
import com.test.urovopaymentapp.presentation.view.widgets.MyTopBar
import com.test.urovopaymentapp.utils.formatCardNumber
import kotlinx.coroutines.Dispatchers

private const val TAG = "CardReaderScreen"
@Composable
fun CardReaderScreen(
    navController: NavController,
    inpuDatas: String,
    viewModel: CardReaderViewModel
) {
    val posInputData by viewModel.livePosInputDatas.observeAsState(inpuDatas.toPosInputDatas())
    val reasonsState by viewModel.reasonsEMV.observeAsState()

    Scaffold(topBar = { MyTopBar("Leer Tarjeta") }) { peddingValues ->
        reasonsState?.let {
            Log.i(TAG, "CardReaderScreen: ${it.reason}")
            when (it.reason) {
                EmvReason.MESSAGE_CARD_MESSAGE -> {
                    LoadingDialog(isLoading = true)
                }
                EmvReason.MESSAGE_REQUEST_PIN -> {
                    MessageDialog(
                        title = it.reason.name,
                        text = it.message,
                        showDialog = true,
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Notifications,
                                contentDescription = "Error Icon",
                                tint = Color.Red, // Cambia el color a rojo
                                modifier = Modifier.size(24.dp) // Tamaño del icono
                            )
                        },
                        primaryButtonText = stringResource(
                            R.string.accept
                        ),
                        onConfirm = {
                        }
                    )
                }
                EmvReason.MESSAGE_PAN -> {

                }
                EmvReason.MESSAGE_ERROR, EmvReason.MESSAGE_TIMEOUT -> {
                    MessageDialog(
                        title = it.reason.name,
                        text = it.message,
                        showDialog = true,
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Error,
                                contentDescription = "Error Icon",
                                tint = Color.Red, // Cambia el color a rojo
                                modifier = Modifier.size(24.dp) // Tamaño del icono
                            )
                        },
                        primaryButtonText = stringResource(
                            R.string.accept
                        ),
                        onConfirm = {
                            navController.popBackStack()
                        }
                    )
                }

                EmvReason.MESSAGE_MSR -> {
                    MessageDialog(
                        title = it.reason.name,
                        text = it.message,
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Info Icon",
                                tint = Color.Blue, // Cambia el color a rojo
                                modifier = Modifier.size(24.dp) // Tamaño del icono
                            )
                        },
                        showDialog = true,

                        primaryButtonText = stringResource(
                            R.string.accept
                        ),
                        onConfirm = {
                            navController.popBackStack()
                        }
                    )
                }

                else -> {
                }
            }
        }
        CardReaderBody(peddingValues, posInputData)
    }
    LaunchedEffect(Dispatchers.IO) {
        viewModel.initEmvListener(
            posInputDatas = posInputData
        )
    }
}

@Composable
private fun CardReaderBody(
    peddingValues: PaddingValues,
    posInputData: PosInputDatas
) {
    Column(
        modifier = Modifier.padding(
            vertical = peddingValues.calculateTopPadding(), horizontal = 16.dp
        )
    ) {
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
            Text(text = "Hola")
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
    CardReaderBody(peddingValues = dummyPaddingValues, posInputData = dummyPosInputDatas)
}

fun Double.formatDouble(): String {
    return String.format("%.2f", this)
}