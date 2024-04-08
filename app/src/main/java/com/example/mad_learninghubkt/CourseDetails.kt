package com.example.mad_learninghubkt

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.mad_learninghubkt.data.CoursesItem
import com.example.mad_learninghubkt.ui.theme.Purple40
import com.example.mad_learninghubkt.ui.theme.Purple80
import com.example.mad_learninghubkt.ui.theme.PurpleGrey80
import com.example.mad_learninghubkt.ui.theme.PurpleStart

var selectedCourse: CoursesItem? = null
var enrollCid = 0

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview
@Composable
fun CourseDetailsScreen(navController: NavHostController = rememberNavController(), courseId: Int) {

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
            CourseHeadingSection()
            CourseCardSection(courseId)
            CourseDetailsSection(courseId)
            CourseDetailsBtnSection(navController)
        }
    }
}

@Composable
fun CourseHeadingSection(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Course Details",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun CourseCardSection(courseId: Int) {

    courseDataList.forEachIndexed { index, courseItem ->
        if (courseItem.cid == courseId) {
            selectedCourse = courseItem
            return@forEachIndexed
        }
    }

    var iconPainter = rememberImagePainter(R.drawable.ic_default_course)

    if (selectedCourse != null) {
        iconPainter = rememberImagePainter(selectedCourse!!.image)
    } else {
    }
    enrollCid = selectedCourse?.cid ?: 0

    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(getGradient(Purple80, PurpleGrey80))
                .fillMaxWidth()
                .height(140.dp)
                .padding(vertical = 20.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                selectedCourse?.let {
                    Text(modifier = Modifier.width(200.dp),
                        text = it.title,
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Image(
                    painter = iconPainter,
                    contentDescription = "Short Courses Card",
                    modifier = Modifier.fillMaxSize()
                )

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailsSection(index: Int){

    //val selectedCourse = courseDataList[index]

    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column {
            Text(modifier = Modifier.fillMaxWidth(),
                text = "Course Overview",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(5.dp))

            Text(modifier = Modifier.fillMaxWidth(),
                text = selectedCourse?.overview ?: "",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(10.dp))


            OutlinedTextField(
                value = selectedCourse?.duration.toString() + " Hours",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Duration") },
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
                ),
                readOnly = true,
            )

            OutlinedTextField(
                value = selectedCourse?.fee.toString() +" /=",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Course Fee") },
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
                ),
                readOnly = true,
            )

            OutlinedTextField(
                value = selectedCourse?.max.toString() +" students",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Max No. of Students") },
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
                ),
                readOnly = true,
            )

            OutlinedTextField(
                value = selectedCourse?.publishedDate ?: "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Publised Date") },
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
                ),
                readOnly = true,
            )

            OutlinedTextField(
                value = selectedCourse?.closingDate ?: "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Registraion Colsing Date") },
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
                ),
                readOnly = true,
            )

            OutlinedTextField(
                value = selectedCourse?.startingDate ?: "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Starting Date") },
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
                ),
                readOnly = true,
            )

            OutlinedTextField(
                value = selectedCourse?.branches ?: "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Branches") },
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
                ),
                readOnly = true,
            )
        }
    }
}

@Composable
fun CourseDetailsBtnSection(navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(modifier = Modifier
            .padding(top = 10.dp)
            .width(120.dp),
            onClick = { navController.navigate(route = Navigation.PaymentScreen.route) }) {
            Text("Enroll")
        }

    }
}