package com.example.mad_learninghubkt.Admin

import android.annotation.SuppressLint
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mad_learninghubkt.ui.theme.GreenEnd
import com.example.mad_learninghubkt.ui.theme.GreenStart

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun BranchOperations() {

    Scaffold {padding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            BranchDetailsSection()
            BranchDetailsBtnSection()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BranchDetailsSection() {

    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(13.dp),
                text = "Branch Details",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(25.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "District") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = GreenStart,
                    unfocusedBorderColor = GreenEnd,
                    focusedLabelColor = GreenStart,
                    unfocusedLabelColor = GreenEnd,
                    cursorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Address") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = GreenStart,
                    unfocusedBorderColor = GreenEnd,
                    focusedLabelColor = GreenStart,
                    unfocusedLabelColor = GreenEnd,
                    cursorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Contact No") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = GreenStart,
                    unfocusedBorderColor = GreenEnd,
                    focusedLabelColor = GreenStart,
                    unfocusedLabelColor = GreenEnd,
                    cursorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Courses") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = GreenStart,
                    unfocusedBorderColor = GreenEnd,
                    focusedLabelColor = GreenStart,
                    unfocusedLabelColor = GreenEnd,
                    cursorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Location on map") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = GreenStart,
                    unfocusedBorderColor = GreenEnd,
                    focusedLabelColor = GreenStart,
                    unfocusedLabelColor = GreenEnd,
                    cursorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )
            )
        }
    }
}

@Composable
fun BranchDetailsBtnSection(){

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
            Text("Update Details")
        }
        Spacer(modifier = Modifier.width(20.dp))

        Button(modifier = Modifier
            .width(150.dp),
            onClick = { /* Handle register */ }) {
            Text("Delete Branch")
        }
    }
}