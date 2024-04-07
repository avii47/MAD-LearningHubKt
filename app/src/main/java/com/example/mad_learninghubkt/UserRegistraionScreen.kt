package com.example.mad_learninghubkt

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mad_learninghubkt.data.UserData
import com.example.mad_learninghubkt.ui.theme.BlueEnd
import com.example.mad_learninghubkt.ui.theme.BlueStart
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
    enrolledCourses = emptyList(),
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

    var isNameValid by remember { mutableStateOf(true) }
    var isAddressValid by remember { mutableStateOf(true) }
    var isLivingCityValid by remember { mutableStateOf(true) }
    var isDobValid by remember { mutableStateOf(true) }
    var isNicValid by remember { mutableStateOf(true) }
    var isemailValid by remember { mutableStateOf(true) }
    var isGenderValid by remember { mutableStateOf(true) }
    var isMobileNoValid by remember { mutableStateOf(true) }
    var isePasswordValid by remember { mutableStateOf(true) }

    val errorIcon = rememberVectorPainter(Icons.Filled.Error)
    val errorIconTint = Color.Red

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
                    isNameValid = validateName(it)
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
                isError = !isNameValid,
                singleLine = true,
                maxLines = 1,
                trailingIcon = {
                    if (!isNameValid) {
                        Icon(
                            painter = errorIcon,
                            contentDescription = null,
                            tint = errorIconTint,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )

            OutlinedTextField(
                value = address,
                onValueChange = {
                    address = it
                    isAddressValid = validateAddress(it)
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
                isError = !isAddressValid,
                singleLine = true,
                maxLines = 1,
                trailingIcon = {
                    if (!isAddressValid) {
                        Icon(
                            painter = errorIcon,
                            contentDescription = null,
                            tint = errorIconTint,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )

            var cityExpanded by remember { mutableStateOf(false) }
            val citySuggestions = listOf("Ampara", "Anuradhapura", "Badulla", "Batticaloa", "Colombo",
                "Galle", "Gampaha", "Hambantota", "Jaffna", "Kalutara",
                "Kandy", "Kegalle", "Kilinochchi", "Kurunegala", "Mannar",
                "Matale", "Matara", "Monaragala", "Mullaitivu", "Nuwara Eliya",
                "Polonnaruwa", "Puttalam", "Ratnapura", "Trincomalee", "Vavuniya")

            var cityTextfieldSize by remember { mutableStateOf(Size.Zero)}

            val icon2 = if (cityExpanded)
                Icons.Filled.KeyboardArrowUp
            else
                Icons.Filled.KeyboardArrowDown

            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = livingCity,
                    onValueChange = {
                        livingCity = it
                        isLivingCityValid = validateLivingCity(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .onGloballyPositioned { coordinates ->
                            //This value is used to assign to the DropDown the same width
                            cityTextfieldSize = coordinates.size.toSize()
                        },

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
                    isError = !isLivingCityValid,
                    singleLine = true,
                    maxLines = 1,
                    trailingIcon = {
                        Icon(icon2,"contentDescription",
                            Modifier.clickable { cityExpanded = !cityExpanded })

                        if (!isLivingCityValid) {
                            Icon(
                                painter = errorIcon,
                                contentDescription = null,
                                tint = errorIconTint,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                )
                DropdownMenu(
                    expanded = cityExpanded,
                    onDismissRequest = { cityExpanded = false },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .width(with(LocalDensity.current) { cityTextfieldSize.width.toDp() })
                ) {
                    citySuggestions.forEach { label ->
                        DropdownMenuItem(onClick = {
                            livingCity = label
                            cityExpanded = false
                        }) {
                            Text(text = label)
                        }
                    }
                }
            }

            var showDialog by remember { mutableStateOf(false) }
            //var selectedDate by remember { mutableStateOf("") }
            val context = LocalContext.current

            OutlinedTextField(
                value = dob,
                onValueChange = {
                    dob = it
                    //isDobValid = validateDob(it)
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
                    isNicValid = validateNIC(it)
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
                isError = !isNicValid,
                singleLine = true,
                maxLines = 1,
                trailingIcon = {
                    if (!isNicValid) {
                        Icon(
                            painter = errorIcon,
                            contentDescription = null,
                            tint = errorIconTint,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    isemailValid = validateEmail(it)
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
                isError = !isemailValid,
                singleLine = true,
                maxLines = 1,
                trailingIcon = {
                    if (!isemailValid) {
                        Icon(
                            painter = errorIcon,
                            contentDescription = null,
                            tint = errorIconTint,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )

            var genderExpanded by remember { mutableStateOf(false) }
            val genderSuggestions = listOf("Male", "Female")

            var genderTextfieldSize by remember { mutableStateOf(Size.Zero)}

            val icon = if (genderExpanded)
                Icons.Filled.KeyboardArrowUp
            else
                Icons.Filled.KeyboardArrowDown

            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = gender,
                    onValueChange = {
                        gender = it
                        isGenderValid = validateGender(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .onGloballyPositioned { coordinates ->
                            //This value is used to assign to the DropDown the same width
                            genderTextfieldSize = coordinates.size.toSize()
                        },

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
                    isError = !isGenderValid,
                    singleLine = true,
                    maxLines = 1,
                    trailingIcon = {
                        Icon(icon,"contentDescription",
                            Modifier.clickable { genderExpanded = !genderExpanded })

                        if (!isGenderValid) {
                            Icon(
                                painter = errorIcon,
                                contentDescription = null,
                                tint = errorIconTint,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                )
                DropdownMenu(
                    expanded = genderExpanded,
                    onDismissRequest = { genderExpanded = false },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .width(with(LocalDensity.current) { genderTextfieldSize.width.toDp() })
                ) {
                    genderSuggestions.forEach { label ->
                        DropdownMenuItem(onClick = {
                            gender = label
                            genderExpanded = false
                        }) {
                            Text(text = label)
                        }
                    }
                }
            }

            OutlinedTextField(
                value = mobileNo,
                onValueChange = {
                    mobileNo = it
                    isMobileNoValid = validateMobileNumber(it)
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
                isError = !isMobileNoValid,
                singleLine = true,
                maxLines = 1,
                trailingIcon = {
                    if (!isMobileNoValid) {
                        Icon(
                            painter = errorIcon,
                            contentDescription = null,
                            tint = errorIconTint,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    isePasswordValid = validatePassword(it)
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
                isError = !isePasswordValid,
                singleLine = true,
                maxLines = 1,
                trailingIcon = {
                    if (!isePasswordValid) {
                        Icon(
                            painter = errorIcon,
                            contentDescription = null,
                            tint = errorIconTint,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )

            userData = UserData(
                userName = userName,
                address = address,
                livingCity = livingCity,
                dob = dob,
                nic = nic,
                email = email,
                gender = gender,
                mobileNo = mobileNo,
                enrolledCourses = emptyList(),
                password = password
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 70.dp, top = 40.dp),

                ) {
                Button(onClick = {
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

@Composable
fun genderDropDownMenu() {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Kotlin", "Java", "Dart", "Python")
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = {Text("Label")},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }

}

fun validateName(name: String): Boolean {
    return name.isNotBlank() && name.matches(Regex("[a-zA-Z]+")) && name.length < 3
}
fun validateAddress(address: String): Boolean {
    return address.isNotBlank() && address.length > 5
}
fun validateLivingCity(city: String): Boolean {
    // Regular expression pattern for city validation
    val cityRegex = Regex("[a-zA-Z]+") // Allows only alphabetic characters
    return cityRegex.matches(city) && city.isNotBlank()
}
//private fun validateDob(dob: Date): Boolean {
//    return dob.isNotBlank() && !dob.matches(Regex("[a-zA-Z]+"))
//}
fun validateNIC(nic: String): Boolean {
    // Regular expression pattern for NIC validation in Sri Lanka
    val nicRegex = Regex("\\d{9}[Vv]")
    return nicRegex.matches(nic) && nic.isNotBlank()
}
fun validateEmail(email: String): Boolean {
    // Regular expression pattern for email validation
    val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+\$")
    return emailRegex.matches(email) && email.isNotBlank()
}
fun validateGender(gender: String): Boolean {
    return gender.isNotBlank() && (gender.trim().equals("Male", ignoreCase = true) ||
            gender.trim().equals("Female", ignoreCase = true))
}
fun validateMobileNumber(mobileNumber: String): Boolean {
    // - Should contain only digits
    // - Should have a specific length (e.g., 10 digits for a standard mobile number)
    val digitRegex = Regex("\\d{10}")
    return mobileNumber.matches(digitRegex) && mobileNumber.isNotBlank()
}
fun validatePassword(password: String): Boolean {
    // - Minimum length of 8 characters
    // - Contains at least one uppercase letter
    // - Contains at least one lowercase letter
    // - Contains at least one digit
    val uppercaseRegex = Regex("[A-Z]")
    val lowercaseRegex = Regex("[a-z]")
    val digitRegex = Regex("[0-9]")

    return password.length >= 8 &&
            password.contains(uppercaseRegex) &&
            password.contains(lowercaseRegex) &&
            password.contains(digitRegex) &&
            password.isNotBlank()
}

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
