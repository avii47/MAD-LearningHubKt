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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mad_learninghubkt.courseDataList
import com.example.mad_learninghubkt.data.CoursesItem
import com.example.mad_learninghubkt.ui.theme.Purple40
import com.example.mad_learninghubkt.ui.theme.PurpleStart
import com.example.mad_learninghubkt.userData
import com.example.mad_learninghubkt.util.SharedViewModel

var newCourseData = CoursesItem(
    cid = 0,
    title = "",
    overview = "",
    level = "",
    duration = 0,
    fee = 0,
    max = 0,
    publishedDate = "",
    closingDate = "",
    startingDate = "",
    branches = "",
    image = 0,
    category = ""
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview
@Composable
fun AddCourse(navController: NavController, sharedViewModel: SharedViewModel) {

    Scaffold {padding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            AddCourseDetailsSection1()
            AddCourseDetailsBtnSection(sharedViewModel, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCourseDetailsSection1(){

    var courseTitle: String by remember { mutableStateOf("") }
    var overview: String by remember { mutableStateOf("") }
    var level: String by remember { mutableStateOf("") }
    var duration: String by remember { mutableStateOf("") }
    var fee: String by remember { mutableStateOf("") }
    var max: String by remember { mutableStateOf("") }
    var publishedDate: String by remember { mutableStateOf("") }
    var closingDate: String by remember { mutableStateOf("") }
    var startingDate: String by remember { mutableStateOf("") }
    var branches: String by remember { mutableStateOf("") }
    var category: String by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column {

            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(13.dp),
                text = "Add New Course",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(25.dp))

            OutlinedTextField(
                value = courseTitle,
                onValueChange = {
                     courseTitle = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Course Title") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Purple40,
                    unfocusedBorderColor = PurpleStart,
                    focusedLabelColor = Purple40,
                    unfocusedLabelColor = PurpleStart,
                    cursorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )
            )

            OutlinedTextField(
                value = category,
                onValueChange = {
                    category = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Course Category") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Purple40,
                    unfocusedBorderColor = PurpleStart,
                    focusedLabelColor = Purple40,
                    unfocusedLabelColor = PurpleStart,
                    cursorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )
            )

            OutlinedTextField(
                value = overview,
                onValueChange = {
                    overview = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Course Overview") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Purple40,
                    unfocusedBorderColor = PurpleStart,
                    focusedLabelColor = Purple40,
                    unfocusedLabelColor = PurpleStart
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )
            )

            OutlinedTextField(
                value = duration,
                onValueChange = {
                    duration = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Duration") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Purple40,
                    unfocusedBorderColor = PurpleStart,
                    focusedLabelColor = Purple40,
                    unfocusedLabelColor = PurpleStart
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )
            )

            OutlinedTextField(
                value = fee,
                onValueChange = {
                    fee = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Course Fee") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Purple40,
                    unfocusedBorderColor = PurpleStart,
                    focusedLabelColor = Purple40,
                    unfocusedLabelColor = PurpleStart
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )
            )

            OutlinedTextField(
                value = max,
                onValueChange = {
                    max = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Max No. of Students") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Purple40,
                    unfocusedBorderColor = PurpleStart,
                    focusedLabelColor = Purple40,
                    unfocusedLabelColor = PurpleStart
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )
            )

            OutlinedTextField(
                value = publishedDate,
                onValueChange = {
                     publishedDate = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Publised Date") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Purple40,
                    unfocusedBorderColor = PurpleStart,
                    focusedLabelColor = Purple40,
                    unfocusedLabelColor = PurpleStart
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )
            )

            OutlinedTextField(
                value = closingDate,
                onValueChange = {
                     closingDate = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Registraion Colsing Date") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Purple40,
                    unfocusedBorderColor = PurpleStart,
                    focusedLabelColor = Purple40,
                    unfocusedLabelColor = PurpleStart
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )
            )

            OutlinedTextField(
                value = startingDate,
                onValueChange = {
                     startingDate = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Starting Date") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Purple40,
                    unfocusedBorderColor = PurpleStart,
                    focusedLabelColor = Purple40,
                    unfocusedLabelColor = PurpleStart
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )
            )

            OutlinedTextField(
                value = branches,
                onValueChange = {
                      branches = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Branches") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Purple40,
                    unfocusedBorderColor = PurpleStart,
                    focusedLabelColor = Purple40,
                    unfocusedLabelColor = PurpleStart
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )
            )
        }
    }

    if(duration != "" && fee != "" && max != ""){

        newCourseData = CoursesItem(
            cid = courseDataList.last().cid + 1,
            title = courseTitle,
            category = category,
            overview = overview,
            level = level,
            duration = duration.toInt(),
            fee = fee.toInt(),
            max = max.toInt(),
            publishedDate = publishedDate,
            closingDate = closingDate,
            startingDate = startingDate,
            branches = branches,
            image = 0
        )
    }


}

@Composable
fun AddCourseDetailsBtnSection(sharedViewModel: SharedViewModel, navController: NavController){

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

    ) {
        Button(modifier = Modifier
            .width(150.dp),
            onClick = {
                sharedViewModel.saveNewCourse(courseData = newCourseData, context = context, navController)
            }) {
            Text("Add Course")
        }
    }
}



