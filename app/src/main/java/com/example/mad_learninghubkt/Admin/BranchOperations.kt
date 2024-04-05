package com.example.mad_learninghubkt.Admin

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mad_learninghubkt.branchDataList
import com.example.mad_learninghubkt.data.BranchesItem
import com.example.mad_learninghubkt.ui.theme.GreenEnd
import com.example.mad_learninghubkt.ui.theme.GreenStart
import com.example.mad_learninghubkt.util.SharedViewModel
import com.google.firebase.firestore.GeoPoint

var updatedBranchData = BranchesItem(
    branchName = "",
    branchNo = "",
    overview = "",
    district = "",
    address = "",
    contactNo = "",
    courses = "",
    latLng = defaultGeoPoint,
    image = 0
)

var selectedBranchNo = ""

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview
@Composable
fun BranchOperations(navController: NavController, sharedViewModel: SharedViewModel, branchId: Int) {

    Scaffold {padding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            BranchDetailsSection(branchId)
            BranchDetailsBtnSection(sharedViewModel, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BranchDetailsSection(branchId: Int) {

    val context = LocalContext.current

    val adminSelectedBranch = adminBranchDataList[branchId]
    selectedBranchNo = adminSelectedBranch.branchNo

    var branchName: String by remember { mutableStateOf(adminSelectedBranch.branchName) }
    var branchNo: String by remember { mutableStateOf(adminSelectedBranch.branchNo) }
    var overview: String by remember { mutableStateOf(adminSelectedBranch.overview) }
    var district: String by remember { mutableStateOf(adminSelectedBranch.district) }
    var address: String by remember { mutableStateOf(adminSelectedBranch.address) }
    var contactNo: String by remember { mutableStateOf(adminSelectedBranch.contactNo) }
    var courses: String by remember { mutableStateOf(adminSelectedBranch.courses) }
    var map: String by remember { mutableStateOf(adminSelectedBranch.latLng.toString()) }

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
                value = branchName,
                onValueChange = {
                    branchName = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Branch Name") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = GreenStart,
                    unfocusedBorderColor = GreenEnd,
                    focusedLabelColor = GreenStart,
                    unfocusedLabelColor = GreenEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                ),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    // Handle done action if needed
                }),
            )

            OutlinedTextField(
                value = branchNo,
                onValueChange = {
                    branchNo = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Branch Code") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = GreenStart,
                    unfocusedBorderColor = GreenEnd,
                    focusedLabelColor = GreenStart,
                    unfocusedLabelColor = GreenEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                ),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    // Handle done action if needed
                }),
            )

            OutlinedTextField(
                value = district,
                onValueChange = {
                    district = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "District") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = GreenStart,
                    unfocusedBorderColor = GreenEnd,
                    focusedLabelColor = GreenStart,
                    unfocusedLabelColor = GreenEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                ),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    // Handle done action if needed
                }),
            )

            OutlinedTextField(
                value = address,
                onValueChange = {
                    address = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Address") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = GreenStart,
                    unfocusedBorderColor = GreenEnd,
                    focusedLabelColor = GreenStart,
                    unfocusedLabelColor = GreenEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                ),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    // Handle done action if needed
                }),
            )

            OutlinedTextField(
                value = contactNo,
                onValueChange = {
                    contactNo = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Contact No") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = GreenStart,
                    unfocusedBorderColor = GreenEnd,
                    focusedLabelColor = GreenStart,
                    unfocusedLabelColor = GreenEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                ),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    // Handle done action if needed
                }),
            )

            OutlinedTextField(
                value = courses,
                onValueChange = {
                    courses = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Courses") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = GreenStart,
                    unfocusedBorderColor = GreenEnd,
                    focusedLabelColor = GreenStart,
                    unfocusedLabelColor = GreenEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                ),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    // Handle done action if needed
                }),
            )

            OutlinedTextField(
                value = map,
                onValueChange = {
                    map = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Location on map") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = GreenStart,
                    unfocusedBorderColor = GreenEnd,
                    focusedLabelColor = GreenStart,
                    unfocusedLabelColor = GreenEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                ),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    // Handle done action if needed
                }),
            )
        }
    }
    fun parseGeoPointFromString(geoPointString: String): GeoPoint {
        // Remove unnecessary parts of the string
        val cleanString = geoPointString.replace("GeoPoint { ", "").replace(" }", "")

        // Split the string by comma and extract latitude and longitude
        val values = cleanString.split(", ").map {
            it.split("=")[1].toDouble()
        }

        // Return a GeoPoint object
        return GeoPoint(values[0], values[1])
    }

    updatedBranchData = BranchesItem(
        branchName = branchName,
        branchNo = branchNo,
        overview = overview,
        district = district,
        address = address,
        contactNo = contactNo,
        courses = courses,
        latLng = parseGeoPointFromString(map),
        image = 0
    )
}

@Composable
fun BranchDetailsBtnSection(sharedViewModel: SharedViewModel, navController: NavController){

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, bottom = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

    ) {
        Button(modifier = Modifier
            .width(150.dp),
            onClick = {
                sharedViewModel.updateBranch(branchData = updatedBranchData, context = context)
            }) {
            Text("Update Details")
        }
        Spacer(modifier = Modifier.width(20.dp))

        Button(modifier = Modifier
            .width(150.dp),
            onClick = {
                sharedViewModel.deleteBranch(selectedBranchNo, context = context, navController)
            }) {
            Text("Delete Branch")
        }
    }
}