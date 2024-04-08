//package com.example.mad_learninghubkt

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.core.content.FileProvider
import coil.compose.rememberImagePainter
import com.example.mad_learninghubkt.R
import com.example.mad_learninghubkt.currentUserData
import com.example.mad_learninghubkt.data.UserData
import com.example.mad_learninghubkt.getGradient
import com.example.mad_learninghubkt.ui.theme.BlueEnd
import com.example.mad_learninghubkt.ui.theme.BlueStart
import com.example.mad_learninghubkt.util.SharedViewModel
import com.example.mad_learninghubkt.util.UserDataStore
import com.example.mad_learninghubkt.validateAddress
import com.example.mad_learninghubkt.validateDob
import com.example.mad_learninghubkt.validateEmail
import com.example.mad_learninghubkt.validateGender
import com.example.mad_learninghubkt.validateLivingCity
import com.example.mad_learninghubkt.validateMobileNumber
import com.example.mad_learninghubkt.validateNIC
import com.example.mad_learninghubkt.validateName
import com.example.mad_learninghubkt.validatePassword
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

var updatedUserData = UserData(
    userName = "",
    address = "",
    livingCity = "",
    dob = "",
    nic = "",
    email = "",
    gender = "",
    mobileNo = "",
    enrolledCourses = emptyList(),
    password = "",
    image = 0
)

var selectedImg = 0

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview
@Composable
fun PersonalDetailsScreen(sharedViewModel: SharedViewModel) {

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
            TopCardSection()
            Spacer(modifier = Modifier.size(16.dp))
            ContentSection()
            BtnSection(sharedViewModel)
        }
    }
}

@Composable
fun TopCardSection() {
    Box(
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 25.dp,
                        bottomEnd = 25.dp
                    )
                )
                .background(getGradient(BlueStart, BlueEnd))
                .fillMaxWidth()
                .height(200.dp)
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val pictureResourceId = R.drawable.user_icon
            val drawableUri = getDrawableUri(LocalContext.current, currentUserData?.image ?: pictureResourceId)
            UserImage(drawableUri)
        }

    }
}

fun getDrawableUri(context: Context, drawableId: Int): String {
    return "android.resource://${context.packageName}/$drawableId"
}

@Composable
fun UserImage(initialUri: String) {
    var uri by remember { mutableStateOf(initialUri) }
    val context = LocalContext.current
    val painter = rememberImagePainter(uri)

    // Use ActivityResultLauncher to handle the result of image selection
    val getContent = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { selectedUri ->
        uri = selectedUri.toString()
        // Process the selected image URI here
        saveImageToDrawable(context, selectedUri)
    }

    Image(
        painter = painter,
        contentDescription = "user profile picture",
        modifier = Modifier
            .size(150.dp)
            .clip(CircleShape)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = CircleShape
            )
            .clickable {
                // Open gallery to select image
                getContent.launch("image/*")
            },
        contentScale = androidx.compose.ui.layout.ContentScale.Crop
    )
}

fun saveImageToDrawable(context: Context, uri: Uri?) {
    uri?.let { selectedImageUri ->
        val inputStream = context.contentResolver.openInputStream(selectedImageUri)
        val bitmap = BitmapFactory.decodeStream(inputStream)

        // Create a file in the app's internal storage
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val filename = "image_$timeStamp.jpg"
        val directory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File(directory, filename)

        try {
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()

            // Check if the file exists
            if (file.exists()) {
                // Log success message
                Log.d("ImageSave", "Image saved successfully: ${file.absolutePath}")
                // Load the saved image into ImageView
                val savedImageUri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.provider",
                    file
                )
            } else {
                // Log error message if file does not exist
                Log.e("ImageSave", "Failed to save image")
            }
        } catch (e: IOException) {
            e.printStackTrace()
            // Log error message if exception occurs during file writing
            Log.e("ImageSave", "Error saving image: ${e.message}")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentSection() {
    Box(
        modifier = Modifier
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            val currentUserData: UserData? = UserDataStore.getUserData()

            if (currentUserData != null) {

                var userName: String by remember { mutableStateOf(currentUserData.userName) }
                var address: String by remember { mutableStateOf(currentUserData.address) }
                var livingCity: String by remember { mutableStateOf(currentUserData.livingCity) }
                var dob: String by remember { mutableStateOf(currentUserData.dob) }
                var nic: String by remember { mutableStateOf(currentUserData.nic) }
                var email: String by remember { mutableStateOf(currentUserData.email) }
                var gender: String by remember { mutableStateOf(currentUserData.gender) }
                var mobileNo: String by remember { mutableStateOf(currentUserData.mobileNo) }
                var password: String by remember { mutableStateOf(currentUserData.password) }

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

                OutlinedTextField(
                    value = userName,
                    onValueChange = {
                        userName = it
                        isNameValid = validateName(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp), // Add padding for better appearance
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
                        .padding(8.dp), // Add padding for better appearance
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

                var showDialog by remember { mutableStateOf(false) }
                //var selectedDate by remember { mutableStateOf("") }
                val context = LocalContext.current

                OutlinedTextField(
                    value = dob,
                    onValueChange = {
                        dob = it
                        isDobValid = validateDob(it)
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
                    com.example.mad_learninghubkt.showDatePicker(context) { year, month, dayOfMonth ->
                        // Set the selected date into the TextField
                        dob = "$dayOfMonth/${month + 1}/$year"
                        showDialog = false
                    }
                }

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
                        .padding(8.dp), // Add padding for better appearance
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
                        .padding(8.dp), // Add padding for better appearance
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

                updatedUserData = UserData(
                    userName = userName,
                    address = address,
                    livingCity = livingCity,
                    dob = dob,
                    nic = nic,
                    email = email,
                    gender = gender,
                    mobileNo = mobileNo,
                    enrolledCourses = currentUserData.enrolledCourses,
                    password = password,
                    image = 0
                )

            } else {
                // User data not available
                // Handle the case where user data is null
            }
        }
    }
}

//fun showDatePicker(context: Context, onDateSelected: (Int, Int, Int) -> Unit) {
//    val calendar = Calendar.getInstance()
//    val year = calendar.get(Calendar.YEAR)
//    val month = calendar.get(Calendar.MONTH)
//    val day = calendar.get(Calendar.DAY_OF_MONTH)
//
//    val datePickerDialog = DatePickerDialog(
//        context,
//        { _, year: Int, month: Int, dayOfMonth: Int ->
//            onDateSelected(year, month, dayOfMonth)
//        },
//        year,
//        month,
//        day
//    )
//
//    datePickerDialog.show()
//}


@Composable
fun BtnSection(sharedViewModel: SharedViewModel){

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 60.dp),
    ) {
        Button(onClick = {
            sharedViewModel.updateUser(updatedUserData, context)
        }) {
            Text("Update")
        }
        Spacer(modifier = Modifier.width(20.dp))
        Button(onClick = {

        }) {
            Text("Delete my account")
        }
    }
}



