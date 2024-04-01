package com.example.mad_learninghubkt

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
//import androidx.compose.runtime.changelist.Operation.AdvanceSlotsBy.name
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun AddUsers() {

    Scaffold {

        Column {
            Text(
                text = "Add Users ",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            Row {
                Text(
                    text = "Full Name         - ",
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )


            }
            Text(
                text = "Address                - ",
                fontSize = 17.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "NIC number             - ",
                fontSize = 17.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Course               - ",
                fontSize = 17.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
//            Text(text = "Name",Modifier.padding(start = 20.dp))
//            TextField(
//                value = "Tharinda",
//                onValueChange = {},
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(color = Color.Transparent),
//                shape = RoundedCornerShape(20.dp),
//                textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
//            )
//            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Mobile Number:         - ",
                fontSize = 17.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )



            Button(
                onClick = { /*TODO*/ }) {

                Text(
                    text ="ADD" )



            }


        }


    }
}





