package com.shankarlohar.teamvinayak.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.shankarlohar.teamvinayak.util.Utils


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInput(
    date: MutableState<DatePickerState>,
    title: @Composable() (() -> Unit)? = null,
    onValueChange: (String) -> Unit
) {
    Column() {
        DatePicker(
            state = date.value,
            modifier = Modifier.padding(16.dp),
            title = title,
        )
        val dateString = date.value.selectedDateMillis?.let {
            Utils.convertMillisToDateString(
                it,
                "yyyy-MM-dd HH:mm:ss"
            )
        }
        if (dateString != null) {
            onValueChange(dateString)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputChipWithAvatar(
    currentSelected: Boolean,
    selected: MutableState<Boolean>,
    icon: ImageVector,
    text:String,
    onValueChange: (String) -> Unit
) {

    InputChip(
        selected = currentSelected,
        onClick = {
            selected.value = !selected.value
            onValueChange(
                if (selected.value) "Male" else "Female"
            )
       },
        label = { Text(text) },
        avatar = {
            Icon(
                icon,
                contentDescription = text,
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        }
    )
}