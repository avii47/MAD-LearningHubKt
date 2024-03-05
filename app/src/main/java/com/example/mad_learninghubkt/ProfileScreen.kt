package com.example.mad_learninghubkt

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mad_learninghubkt.data.CoursesItem
import com.example.mad_learninghubkt.ui.theme.BlueEnd
import com.example.mad_learninghubkt.ui.theme.BlueStart

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen() {
    Scaffold {
        Column {
            MyAccountSection()
            AccountSection()
            GeneralSection()
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
                    text = "User Name",
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun MyAccountSection() {
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
        )
    }
}

val mycourses = listOf(
    CoursesItem(
        title = "Python Programming",
        level = "Beginner",
        duration = 30,
        image = R.drawable.python
    ),

    CoursesItem(
        title = "Java Programming",
        level = "Intermediate",
        duration = 45,
        image = R.drawable.java
    ),
)

@Composable
fun GeneralSection() {
    Text(
        text = "My Courses",
        fontSize = 20.sp,
        color = MaterialTheme.colorScheme.onBackground,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(16.dp)
    )

    LazyColumn() {
        items(mycourses.size) { index ->
            CourseItem(index)
        }
    }
}