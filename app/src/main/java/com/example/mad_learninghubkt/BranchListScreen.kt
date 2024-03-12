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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mad_learninghubkt.data.BranchesItem
import com.google.android.gms.maps.model.LatLng

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun BranchListScreen(navController: NavHostController = rememberNavController()) {

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
            BranchListHeadingSection()
            BranchListSection(navController)
        }
    }
}

@Composable
fun BranchListHeadingSection(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Our Branches",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
    }
}

val branchList = listOf(
    BranchesItem(
        branchName = "Matara Campus",
        branchNo = "MT221",
        overview = "In molding the lives of the youth and adults, the standards we maintain and the teaching and learning tools, materials and platforms we use are diverse and widely recognized ensuring the regular modernization of the global standards of learning.  The student generation is exposed to a wide variety of experience in not only the learning, but also in entertainment, religious and cultural activities and talent displays to excel in the competitive educational arena calibrating the true mark of the Sri Lankan Southerners.",
        district = "Matara",
        address = "2nd Floor, No. 26, NSB Building, අනගාරික ධර්මපාල මාවත, Matara 81000",
        contactNo = "0412 237 544",
        courses = "Java Programming, Python Programming",
        latLng = LatLng(5.9493, 80.5463),
        image = R.drawable.icon_map
    ),

    BranchesItem(
        branchName = "Colombo Campus",
        branchNo = "CO177",
        overview = "Today NIBM Colombo center possesses fully air-conditioned and well-equipped modern computer labs, classroom facilities and study areas to make students more comfortable and freer to study. Students are highly benefited from an air-conditioned modern library consisting of thousands of educational books and a clean large cafeteria with many more facilities.",
        district = "Colombo",
        address = "120/5 Vidya Mawatha, Colombo 00700",
        contactNo = "0117 321 000",
        courses = "Java Programming, Python Programming",
        latLng = LatLng(6.9063, 79.8708),
        image = R.drawable.icon_map
    ),
)

@Composable
fun BranchListSection(navController: NavController) {
    Column {
        branchList.forEach { index ->
            BranchItem(index = branchList.indexOf(index), navController)
        }
    }
}

@Composable
fun BranchItem(
    index: Int,
    navController: NavController
) {
    val branch = branchList[index]
    val iconPainter = painterResource(id = branch.image)

    Box(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                navController.navigate(route = "${Navigation.BranchDetails.route}/${index}")
            }
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .fillMaxWidth()
                .height(120.dp)
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    painter = iconPainter,
                    contentDescription = branch.branchName,
                    modifier = Modifier.width(60.dp)
                )

                Column {
                    Text(
                        text = branch.branchName,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = branch.district,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}