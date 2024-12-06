package com.test.urovopaymentapp.presentation.view.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun MessageDialog(
    title: String,
    text: String,
    alertType: AlertType = AlertType.Message,
    onPrimaryButton: () -> Unit = {},
    primaryButtonText: String? = null,
    secondaryButtonText: String? = null,
    onSecondaryButton: (() -> Unit)? = null,
    onDismissRequest: () -> Unit = {},
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Ícono y título
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    when (alertType) {
                        AlertType.Error -> {
                            Icon(
                                imageVector = Icons.Filled.Error,
                                contentDescription = "Error Icon",
                                tint = MaterialTheme.colorScheme.error, // Cambia el color a rojo
                                modifier = Modifier.size(24.dp) // Tamaño del icono
                            )
                        }

                        AlertType.Message -> {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Error Icon",
                                tint = MaterialTheme.colorScheme.primary, // Cambia el color a rojo
                                modifier = Modifier.size(24.dp) // Tamaño del icono
                            )
                        }

                        AlertType.Success -> {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Error Icon",
                                tint = Color(0xFF008000), // Cambia el color a rojo
                                modifier = Modifier.size(24.dp) // Tamaño del icono
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = title,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = text,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                // Botones
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    secondaryButtonText?.let {
                        OutlinedButton(
                            onClick = { onSecondaryButton!! },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = secondaryButtonText)
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    primaryButtonText?.let {
                        Button(
                            onClick = onPrimaryButton,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = primaryButtonText)
                        }
                    }
                }
            }
        }
    }
}


enum class AlertType {
    Message,
    Error,
    Success
}