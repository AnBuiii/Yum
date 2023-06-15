package com.anbui.yum.presentation.cart.components

import android.util.Log
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealPlanDatePicker(
    isOpen: Boolean = false,
    onSave: (Long) -> Unit,
    onChangeDatePicker: (Boolean) -> Unit = {},
    datePickerState: DatePickerState = rememberDatePickerState(),
) {

//    datePickerState.
    if (isOpen) {
        val confirmEnabled =
            remember { derivedStateOf { datePickerState.selectedDateMillis != null } }
        DatePickerDialog(
            onDismissRequest = {
                onChangeDatePicker(false)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onChangeDatePicker(false)
                        onSave(datePickerState.selectedDateMillis?.changeTimeZone() ?: 0)
                    },
                    enabled = confirmEnabled.value,
                ) {
                    Text("Pick time")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onChangeDatePicker(false)
                    },
                ) {
                    Text("Cancel")
                }
            },
        ) {
            DatePicker(state = datePickerState)
        }
    }

}

private fun Long.changeTimeZone(): Long {
    return this.minus((7 * 3600000))
}
