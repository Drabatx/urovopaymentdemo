package com.test.urovopaymentapp.presentation.view.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.DialogProperties

@Composable
fun MessageDialog(
    title: String,
    text: String,
    icon: @Composable (() -> Unit)? = null,
    showDialog: Boolean,
    onConfirm: () -> Unit = {},
    primaryButtonText: String? = null,
    secondaryButtonText: String? = null,
    onSecondaryButton: (() -> Unit)? = null
) {
    var showDialogR by remember { mutableStateOf(showDialog) }
    if (showDialogR) {
        AlertDialog(
            icon = {
                icon?.invoke()
            },
            title = {
                Text(text = title)
            },
            text = {
                Text(text = text)
            },
            onDismissRequest = {
                showDialogR = false
            }, confirmButton = {
                primaryButtonText?.let {
                    TextButton(
                        onClick = {
                            onConfirm()
                            showDialogR = false
                        }
                    ) {
                        Text(it)
                    }
                }

            },
            dismissButton = {
                secondaryButtonText?.let {
                    TextButton(
                        onClick = {
                            onSecondaryButton?.invoke() // Ejecuta la acción del botón secundario
                            showDialogR = false
                        }
                    ) {
                        Text(it)
                    }
                }
            },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        )
    }
}