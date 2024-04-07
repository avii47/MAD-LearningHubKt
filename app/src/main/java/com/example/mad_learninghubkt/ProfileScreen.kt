package com.example.mad_learninghubkt

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mad_learninghubkt.data.CoursesItem
import com.example.mad_learninghubkt.data.UserData
import com.example.mad_learninghubkt.ui.theme.BlueEnd
import com.example.mad_learninghubkt.ui.theme.BlueStart
import com.example.mad_learninghubkt.util.UserDataStore

val currentUserData: UserData? = UserDataStore.getUserData()

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavHostController = rememberNavController()) {
    Scaffold {
        Column {
            MyAccountSection(navController)
            AccountSection()
            GeneralSection(navController)
        }
    }
}

@Composable
fun AccountSection() {
    Box (
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(getGradient(BlueStart, BlueEnd))
                .fillMaxWidth()
                .height(96.dp)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.profile_icon),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(60.dp)
                )

                Text(
                    text = currentUserData?.userName ?: "User Name",
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun MyAccountSection(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "My Account",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )


        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Edit Account",
            modifier = Modifier
                .padding(16.dp)
                .size(25.dp)
                .clickable {
                    navController.navigate(route = Navigation.PersonalDetails.route)
                }
        )
    }
}

@Composable
fun GeneralSection(navController: NavController) {
    Text(
        text = "My Courses",
        fontSize = 20.sp,
        color = MaterialTheme.colorScheme.onBackground,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(16.dp)
    )

    Spacer(modifier = Modifier.size(16.dp))

    if(currentUserData?.enrolledCourses?.isEmpty() == false){

        // Filter the courseDataList to get only the courses with matching IDs
        val enrolledCourseItems = courseDataList.filter { course ->
            currentUserData.enrolledCourses.any { courseId ->
                courseId.toInt() == course.cid
            }
        }

        // Convert each CoursesItem to CourseItem
        val enrolledCourseItemList = enrolledCourseItems.map { enrolledCourse ->
            // Find the corresponding CoursesItem in courseDataList
            val correspondingCourse = courseDataList.find { it.cid == enrolledCourse.cid }

            // Create CourseItem from corresponding CoursesItem
            correspondingCourse?.let { course ->
                EnrolledCourseItem(
                    index = courseDataList.indexOf(course), // or use any other index as needed
                    course = course,
                    navController = navController
                )
            }
        }.filterNotNull()
    }

}

@Composable
fun EnrolledCourseItem(
    index: Int,
    course: CoursesItem,
    navController: NavController
) {
    //val iconPainter = painterResource(id = course.image)
    val iconPainter = painterResource(id = R.drawable.java)

    Box(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 16.dp)
            .clickable {}
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .fillMaxWidth()
                .height(120.dp)
                .padding(vertical = 12.dp, horizontal = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    painter = iconPainter,
                    contentDescription = course.title,
                    modifier = Modifier.width(60.dp)
                )

                Column {
                    Text(
                        text = course.title,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = course.level,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = course.duration.toString() + " hours",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}