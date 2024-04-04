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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mad_learninghubkt.ui.theme.GreenEnd
import com.example.mad_learninghubkt.ui.theme.GreenStart
import com.example.mad_learninghubkt.ui.theme.background

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview
@Composable
fun BranchDetailsScreen(navController: NavHostController = rememberNavController(), branchId: Int) {

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
            BranchHeadingSection()
            BranchCardSection(branchId)
            BranchDetailsSection(branchId)
            BranchDetailsBtnSection(navController, branchId)
        }
    }
}

@Composable
fun BranchHeadingSection(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Branch Details",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun BranchCardSection(branchId: Int) {
    val selectedBranch = branchDataList[branchId]
    val iconPainter = painterResource(id = R.drawable.icon_map)

    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(getGradient(GreenStart, background))
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
                Text(modifier = Modifier.width(200.dp),
                    text = selectedBranch.branchName,
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Image(
                    painter = iconPainter,
                    contentDescription = "Branch details Card",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BranchDetailsSection(branchId: Int){
    val selectedBranch = branchDataList[branchId]
    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column {
            Text(modifier = Modifier.fillMaxWidth(),
                text = "Branch Overview",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(5.dp))

            Text(modifier = Modifier.fillMaxWidth(),
                text = selectedBranch.overview,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(10.dp))


            OutlinedTextField(
                value = selectedBranch.district + " District",
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
                ),
                readOnly = true,
            )

            OutlinedTextField(
                value = selectedBranch.address,
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
                ),
                readOnly = true,
            )

            OutlinedTextField(
                value = selectedBranch.contactNo,
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
                ),
                readOnly = true,
            )

            OutlinedTextField(
                value = selectedBranch.courses,
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
                ),
                readOnly = true,
            )

        }
    }
}

@Composable
fun BranchDetailsBtnSection(navController: NavController, branchId:Int){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(modifier = Modifier
            .padding(top = 10.dp)
            .width(160.dp),
            onClick = { navController.navigate(route = "${Navigation.LocationScreen.route}/${branchId}") }) {
            Text("View in map")
        }

    }
}