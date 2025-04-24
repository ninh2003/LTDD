/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.tiptime

import android.os.Bundle
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.material3.Switch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.ui.res.painterResource
import org.jetbrains.annotations.VisibleForTesting
import kotlin.math.ceil
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    TipTimeLayout()
                }
            }
        }
    }
}

@Composable
fun TipTimeLayout() {
    var amountInput by remember { mutableStateOf("") }
    var tipInput by remember { mutableStateOf("") }
    var sothu1 by remember { mutableStateOf("") }
    var sothu2 by remember { mutableStateOf("") }
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    var roundUp by remember { mutableStateOf(false) }
    val tip = calculateTip(amount, tipPercent, roundUp)


    Column(
        modifier = Modifier
            .padding(40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tinh Tong",
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        EditNumberField(
            value = sothu1,
            label = R.string.sothu1,
            leadingIcon = R.drawable.money,


            onValueChange = { sothu1 = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        EditNumberField(
            label = R.string.sothu2,
            value = sothu2,
            leadingIcon = R.drawable.percent,

            onValueChange = { sothu2 = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        Row {
            Button(
                onClick = {
                    val sothu1 = sothu1.toDoubleOrNull() ?: 0.0
                    val sothu2 = sothu2.toDoubleOrNull() ?: 0.0
                    val tong = sothu1 + sothu2
                    amountInput = tong.toString()
                },
                modifier = Modifier
                    .padding(end = 8.dp)
            ) {
                Text(text = "+")
            }
            Button(
                onClick = {
                    val sothu1 = sothu1.toDoubleOrNull() ?: 0.0
                    val sothu2 = sothu2.toDoubleOrNull() ?: 0.0
                    val tong = sothu1 - sothu2
                    amountInput = tong.toString()
                },
                modifier = Modifier
                    .padding(end = 8.dp)
            ) {
                Text(text = "-")
            }
            Button(
                onClick = {
                    val sothu1 = sothu1.toDoubleOrNull() ?: 0.0
                    val sothu2 = sothu2.toDoubleOrNull() ?: 0.0
                    val tong = sothu1 * sothu2
                    amountInput = tong.toString()
                },
                modifier = Modifier
                    .padding(end = 8.dp)
            ) {
                Text(text = "*")
            }
            Button(
                onClick = {
                    val sothu1 = sothu1.toDoubleOrNull() ?: 0.0
                    val sothu2 = sothu2.toDoubleOrNull() ?: 0.0
                    val tong = sothu1 / sothu2
                    amountInput = tong.toString()
                },
                modifier = Modifier
                    .padding(end = 8.dp)
            ) {
                Text(text = "/")
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Fieldketqua(
            value = amountInput,
            label = R.string.ketqua ,
            leadingIcon = R.drawable.money,
            onValueChange = { amountInput = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )



    }
}
@Composable
fun Fieldketqua(value: String,
                    @StringRes label: Int,
                    @DrawableRes leadingIcon: Int,

                    onValueChange: (String) -> Unit,
                    keyboardOptions: KeyboardOptions,
                    modifier: Modifier = Modifier) {
    var amountInput by remember { mutableStateOf("") }
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    TextField(

        label = { Text(stringResource(label)) },

        singleLine = true,
        value = value,
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), null) },
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,

                modifier = modifier
    )
}
@Composable
fun EditNumberField(value: String,
                    @StringRes label: Int,
                    @DrawableRes leadingIcon: Int,

                    onValueChange: (String) -> Unit,
                    keyboardOptions: KeyboardOptions,
                    modifier: Modifier = Modifier) {
    var amountInput by remember { mutableStateOf("") }
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    TextField(

        label = { Text(stringResource(label)) },

        singleLine = true,
        value = value,
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), null) },
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,

                modifier = modifier
    )
}
@Composable
fun RoundTheTipRow(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.round_up_tip))
        Switch(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            checked = roundUp,
            onCheckedChange = onRoundUpChanged
        )
    }
}
/**
 * Calculates the tip based on the user input and format the tip amount
 * according to the local currency.
 * Example would be "$10.00".
 */
@VisibleForTesting
internal fun calculateTip(amount: Double, tipPercent: Double, roundUp: Boolean): String {
    var tip = tipPercent / 100 * amount
    if (roundUp) {
        tip = kotlin.math.ceil(tip)
    }
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview() {
    TipTimeTheme {
        TipTimeLayout()
    }
}
