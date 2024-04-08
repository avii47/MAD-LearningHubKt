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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mad_learninghubkt.R
import com.example.mad_learninghubkt.data.BranchesItem
import com.example.mad_learninghubkt.ui.theme.GreenEnd
import com.example.mad_learninghubkt.ui.theme.GreenStart
import com.example.mad_learninghubkt.util.SharedViewModel
import com.google.firebase.firestore.GeoPoint

val defaultGeoPoint = GeoPoint(0.0, 0.0)

var newBranchData = BranchesItem(
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview
@Composable
fun AddBranch(navController: NavController, sharedViewModel: SharedViewModel) {

    Scaffold {padding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            AddNewBranchSection()
            AddNewBranchBtnSection(sharedViewModel, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewBranchSection() {

    var branchName: String by remember { mutableStateOf("") }
    var branchNo: String by remember { mutableStateOf("") }
    var overview: String by remember { mutableStateOf("") }
    var district: String by remember { mutableStateOf("") }
    var address: String by remember { mutableStateOf("") }
    var contactNo: String by remember { mutableStateOf("") }
    var courses: String by remember { mutableStateOf("") }
    var latitude: String by remember { mutableStateOf("") }
    var longitude: String by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(13.dp),
                text = "Add New Branch",
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
                value = branchNo,
                onValueChange = {
                     branchNo = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Branch No") },
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
                value = latitude,
                onValueChange = {
                    latitude = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Latitude") },
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
                value = longitude,
                onValueChange = {
                    longitude = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Longitude") },
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

    fun createGeoPoint(): GeoPoint {
        val lat = latitude.toDoubleOrNull() ?: 0.0
        val lon = longitude.toDoubleOrNull() ?: 0.0
        return GeoPoint(lat, lon)
    }

    val pictureResourceId = R.drawable.ic_default_branch

    newBranchData = BranchesItem(
        branchName = branchName,
        branchNo = branchNo,
        overview = overview,
        district = district,
        address = address,
        contactNo = contactNo,
        courses = courses,
        latLng = createGeoPoint(),
        image = pictureResourceId
    )
}

@Composable
fun AddNewBranchBtnSection(sharedViewModel: SharedViewModel, navController: NavController){

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
                sharedViewModel.saveNewBranch(branchData = newBranchData, context = context, navController)
            }) {
            Text("Add Branch")
        }
    }
}