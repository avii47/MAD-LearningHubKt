package com.example.mad_learninghubkt

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mad_learninghubkt.data.UserData
import com.example.mad_learninghubkt.ui.theme.BlueEnd
import com.example.mad_learninghubkt.ui.theme.BlueStart
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.util.Calendar

var userData = UserData(
    userName = "",
    address = "",
    livingCity = "",
    dob = "",
    nic = "",
    email = "",
    gender = "",
    mobileNo = "",
    password = ""
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun UserRegistraionScreen(navController: NavHostController = rememberNavController()) {

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
            HeadingSection()
            FormSection(navController)
        }
    }
}

@Composable
fun HeadingSection(){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "User Registraion",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSection(navController: NavController){

    var userName: String by remember { mutableStateOf("") }
    var address: String by remember { mutableStateOf("") }
    var livingCity: String by remember { mutableStateOf("") }
    var dob: String by remember { mutableStateOf("") }
    var nic: String by remember { mutableStateOf("") }
    var email: String by remember { mutableStateOf("") }
    var gender: String by remember { mutableStateOf("") }
    var mobileNo: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, start = 20.dp, end = 20.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = userName,
                onValueChange = {
                    userName = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Name")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueStart,
                    unfocusedBorderColor = BlueEnd,
                    focusedLabelColor = BlueStart,
                    unfocusedLabelColor = BlueEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    // Handle done action if needed
                }),
            )
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = address,
                onValueChange = {
                    address = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Address")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueStart,
                    unfocusedBorderColor = BlueEnd,
                    focusedLabelColor = BlueStart,
                    unfocusedLabelColor = BlueEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    // Handle done action if needed
                }),
            )
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = livingCity,
                onValueChange = {
                    livingCity = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Living City")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueStart,
                    unfocusedBorderColor = BlueEnd,
                    focusedLabelColor = BlueStart,
                    unfocusedLabelColor = BlueEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    // Handle done action if needed
                }),
            )
            Spacer(modifier = Modifier.height(5.dp))

            var showDialog by remember { mutableStateOf(false) }
            //var selectedDate by remember { mutableStateOf("") }
            val context = LocalContext.current

            OutlinedTextField(
                value = dob,
                onValueChange = {
                    //selectedDate = it
                    dob = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent)
                    .padding(8.dp),
                shape = RoundedCornerShape(30.dp),
                label = { Text(text = "Date of Birth")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueStart,
                    unfocusedBorderColor = BlueEnd,
                    focusedLabelColor = BlueStart,
                    unfocusedLabelColor = BlueEnd
                ),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    // Handle done action if needed
                }),
                trailingIcon = {
                    IconButton(
                        onClick = { showDialog = true },
                        modifier = Modifier.height(25.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_calendar),
                            contentDescription = "Calendar"
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(5.dp))

            if (showDialog) {
                showDatePicker(context) { year, month, dayOfMonth ->
                    // Set the selected date into the TextField
                    dob = "$dayOfMonth/${month + 1}/$year"
                    showDialog = false
                }
            }
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = nic,
                onValueChange = {
                    nic = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "NIC")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueStart,
                    unfocusedBorderColor = BlueEnd,
                    focusedLabelColor = BlueStart,
                    unfocusedLabelColor = BlueEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    // Handle done action if needed
                }),
            )
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Email")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueStart,
                    unfocusedBorderColor = BlueEnd,
                    focusedLabelColor = BlueStart,
                    unfocusedLabelColor = BlueEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    // Handle done action if needed
                }),
            )
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = gender,
                onValueChange = {
                    gender = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Gender")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueStart,
                    unfocusedBorderColor = BlueEnd,
                    focusedLabelColor = BlueStart,
                    unfocusedLabelColor = BlueEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    // Handle done action if needed
                }),
            )
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = mobileNo,
                onValueChange = {
                    mobileNo = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Mobile Number")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueStart,
                    unfocusedBorderColor = BlueEnd,
                    focusedLabelColor = BlueStart,
                    unfocusedLabelColor = BlueEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    // Handle done action if needed
                }),
            )
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Password")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueStart,
                    unfocusedBorderColor = BlueEnd,
                    focusedLabelColor = BlueStart,
                    unfocusedLabelColor = BlueEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    // Handle done action if needed
                }),
            )
            Spacer(modifier = Modifier.height(5.dp))

            userData = UserData(
                userName = userName,
                address = address,
                livingCity = livingCity,
                dob = dob,
                nic = nic,
                email = email,
                gender = gender,
                mobileNo = mobileNo,
                password = password
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 70.dp, top = 40.dp),

                ) {
                Button(onClick = {
                    //sharedViewModel.saveData(userData = userData, context = context)
                    navController.navigate(route = Navigation.EmailVerificationScreen.route)
                }) {
                    Text("Register")
                }
                Spacer(modifier = Modifier.width(20.dp))

                Button(onClick = {
                    navController.navigate(route = Navigation.HomeScreen.route)
                }) {
                    Text("Skip Registraion")
                }
            }

        }

    }
}

//@SuppressLint("InvalidAnalyticsName")
//@Composable
//fun BtnSection(navController: NavController, sharedViewModel: SharedViewModel){
//
//    val context = LocalContext.current
//
//}

//function pick the date from calendar
fun showDatePicker(context: Context, onDateSelected: (Int, Int, Int) -> Unit) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year: Int, month: Int, dayOfMonth: Int ->
            onDateSelected(year, month, dayOfMonth)
        },
        year,
        month,
        day
    )

    datePickerDialog.show()
}

fun CreateDatabase(){
    val db = Firebase.firestore
}