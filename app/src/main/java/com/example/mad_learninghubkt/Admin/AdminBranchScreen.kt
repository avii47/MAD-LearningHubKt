package com.example.mad_learninghubkt.Admin

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mad_learninghubkt.Navigation
import com.example.mad_learninghubkt.R
import com.example.mad_learninghubkt.data.BranchesItem
import com.example.mad_learninghubkt.util.BranchDataStore

var adminBranchDataList = emptyList<BranchesItem>()

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview
@Composable
fun AdminBranchScreen(navController: NavController) {

    adminBranchDataList = emptyList<BranchesItem>()
    adminBranchDataList = BranchDataStore.getBranchData()

    Scaffold {padding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            BranchFunctions(navController)
            BranchList(navController)
        }
    }
}

@Composable
fun BranchFunctions(navController: NavController){

    Text(
        text = "Branches",
        fontSize = 24.sp,
        color = MaterialTheme.colorScheme.onBackground,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(16.dp)
    )

    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .fillMaxWidth()
                .height(80.dp)
                .clickable {
                    navController.navigate(route = Navigation.AddBranchScreen.route)
                }
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    modifier = Modifier.size(80.dp),
                    painter = painterResource(id = R.drawable.add_branches_icon),
                    contentDescription = "add branch"
                )

                Text(
                    text = "Add Branch",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
fun BranchList(navController: NavController){

    adminBranchDataList.forEachIndexed { index, branchItem ->
        AdminBranchItem(index, branchItem, navController)
    }
}

@Composable
fun AdminBranchItem(
    index: Int,
    branch: BranchesItem,
    navController: NavController
) {
    val iconPainter = painterResource(id = R.drawable.ic_default_branch)

    Box(
        modifier = Modifier
            .padding(vertical = 6.dp, horizontal = 16.dp)
            .clickable {
                navController.navigate(route = "${Navigation.BranchOperationsScreen.route}/${index}")
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


