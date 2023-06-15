package com.anbui.yum.presentation.cart.components

import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealPlanTimePicker(
    isOpen: Boolean = false,
    onSave: (Int, Int) -> Unit,
    onChangeTimePicker: (Boolean) -> Unit = {},
    timePickerState: TimePickerState = rememberTimePickerState(),
) {
    if (isOpen) {
        DatePickerDialog(
            onDismissRequest = {
                onChangeTimePicker(false)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onChangeTimePicker(false)
                        onSave(
                            timePickerState.hour,
                            timePickerState.minute,
                        )
                    },
                ) {
                    Text("Done")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onChangeTimePicker(false)
                    },
                ) {
                    Text("Back")
                }
            },
        ) {
            TimePicker(state = timePickerState)
        }
    }

}
