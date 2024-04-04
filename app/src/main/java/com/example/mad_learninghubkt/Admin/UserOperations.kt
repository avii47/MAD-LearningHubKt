

package com.example.mad_learninghubkt.Admin

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mad_learninghubkt.R
import com.example.mad_learninghubkt.showDatePicker
import com.example.mad_learninghubkt.ui.theme.BlueEnd
import com.example.mad_learninghubkt.ui.theme.BlueStart

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun UserOperations() {

    Scaffold {padding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            UserDetailsSection()
            UserDetailsBtnSection()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsSection() {

    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(13.dp),
                text = "User Details",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Name")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueStart,
                    unfocusedBorderColor = BlueEnd,
                    focusedLabelColor = BlueStart,
                    unfocusedLabelColor = BlueEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                readOnly = true,
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Address")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueStart,
                    unfocusedBorderColor = BlueEnd,
                    focusedLabelColor = BlueStart,
                    unfocusedLabelColor = BlueEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                readOnly = true,
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Living City")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueStart,
                    unfocusedBorderColor = BlueEnd,
                    focusedLabelColor = BlueStart,
                    unfocusedLabelColor = BlueEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                readOnly = true,
            )

            var showDialog by remember { mutableStateOf(false) }
            //var selectedDate by remember { mutableStateOf("") }
            val context = LocalContext.current

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent)
                    .padding(8.dp),
                shape = RoundedCornerShape(30.dp),
                label = { Text(text = "Date of Birth")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueStart,
                    unfocusedBorderColor = BlueEnd,
                    focusedLabelColor = BlueStart,
                    unfocusedLabelColor = BlueEnd
                ),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    // Handle done action if needed
                }),
                trailingIcon = {
                    IconButton(
                        onClick = { showDialog = true },
                        modifier = Modifier.height(25.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_calendar),
                            contentDescription = "Calendar"
                        )
                    }
                }
            )

            if (showDialog) {
                showDatePicker(context) { year, month, dayOfMonth ->
                    // Set the selected date into the TextField
                    var dob = "$dayOfMonth/${month + 1}/$year"
                    showDialog = false
                }
            }

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "NIC")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueStart,
                    unfocusedBorderColor = BlueEnd,
                    focusedLabelColor = BlueStart,
                    unfocusedLabelColor = BlueEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                readOnly = true,
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Email")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueStart,
                    unfocusedBorderColor = BlueEnd,
                    focusedLabelColor = BlueStart,
                    unfocusedLabelColor = BlueEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                readOnly = true,
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Gender")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueStart,
                    unfocusedBorderColor = BlueEnd,
                    focusedLabelColor = BlueStart,
                    unfocusedLabelColor = BlueEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                readOnly = true,
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Mobile Number")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueStart,
                    unfocusedBorderColor = BlueEnd,
                    focusedLabelColor = BlueStart,
                    unfocusedLabelColor = BlueEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                readOnly = true,
            )
        }
    }
}

@Composable
fun UserDetailsBtnSection(){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, bottom = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

    ) {
        Button(modifier = Modifier
            .width(150.dp),
            onClick = { /* Handle register */ }) {
            Text("Banned User")
        }
        Spacer(modifier = Modifier.width(20.dp))

        Button(modifier = Modifier
            .width(150.dp),
            onClick = { /* Handle register */ }) {
            Text("Delete User")
        }
    }
}


