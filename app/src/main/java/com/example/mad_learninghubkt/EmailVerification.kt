package com.example.mad_learninghubkt

import android.annotation.SuppressLint
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun EmailVerificationScreen() {

    Scaffold(
        bottomBar = {
            Spacer(modifier = Modifier.height(80.dp))
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            HeadingSection()
        }
    }
}

@Composable
fun HeadingSection(){
    Box(modifier = Modifier){

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "Email Verification",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "We have send an OTP to your email",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "ashanavishka81@gmail.com",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(80.dp))

            Text(
                text = "Enter your OTP here",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(20.dp))
            OtpCodeInput()
            BtnSection()

        }
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun  OtpCodeInput() {

    val focusRequesters = remember { List(6) { FocusRequester() } }
    var otp by remember { mutableStateOf(List(6) { "" }) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        otp.forEachIndexed { index, value ->
            OutlinedTextField(
                value = value,
                onValueChange = { newValue ->
                    if (newValue.length <= 1) {
                        otp = otp.toMutableList().also {
                            it[index] = newValue
                        }
                        if (newValue.isNotEmpty() && index < 5) {
                            focusRequesters[index + 1].requestFocus()
                        }
                    }
                },
                modifier = Modifier.width(40.dp).focusRequester(focusRequesters[index]),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = if (index == 5) ImeAction.Done else ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        if (index < 5) {
                            focusRequesters[index + 1].requestFocus()
                        }
                    }
                )
            )
        }
    }

}

@Composable
fun BtnSection(){
    // Update and Delete buttons
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 60.dp),
    ) {
        Button(modifier = Modifier
            .padding(start = 80.dp, top = 50.dp),
            onClick = { /* Handle update */ }) {
            Text("Register")
        }

    }
}