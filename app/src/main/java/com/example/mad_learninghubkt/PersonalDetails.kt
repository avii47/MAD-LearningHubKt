import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.shapes.Shape
import android.media.Image
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.view.MenuItem
import android.widget.CalendarView
import android.widget.DatePicker
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import coil.compose.rememberImagePainter
import com.example.mad_learninghubkt.CardSection
import com.example.mad_learninghubkt.CategorySection
import com.example.mad_learninghubkt.CourseSection
import com.example.mad_learninghubkt.R
import com.example.mad_learninghubkt.TopSection
import com.example.mad_learninghubkt.getGradient
import com.example.mad_learninghubkt.ui.theme.BlueEnd
import com.example.mad_learninghubkt.ui.theme.BlueStart
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview
@Composable
fun PersonalDetailsScreen() {

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
            BtnSection()
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
                .height(120.dp)
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val drawableUri = getDrawableUri(LocalContext.current, R.drawable.user_img1)
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

            // Name field
            NameField(name = "Ashan Avishka")

            // Address field
            AddressField(address = "Matara")

            // Email field
            EmailField(email = "ashanavishka81@gmail.com")

            // Password field
            PasswordField(password = "Reachthefinish1")

            // Gender dropdown
            GenderDropdown(gender = "Male")

            // Living City dropdown
            LivingCityDropdown(city = "Matara")

            // Mobile number field
            MobileNoField(mobileNo = "0702100295")

            // Date field
            DateField(date = "2002-04-01")

        }
    }
}

@Composable
fun NameField(name: String) {
    Text(text = "Name",Modifier.padding(start = 20.dp))
    TextField(
        value = name,
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent),
        shape = RoundedCornerShape(30.dp),
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
    )
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun AddressField(address: String) {
    Text(text = "Address",Modifier.padding(start = 20.dp))
    TextField(
        value = address,
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent),
        shape = RoundedCornerShape(30.dp),
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
    )
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun EmailField(email: String) {
    Text(text = "Email",Modifier.padding(start = 20.dp))
    TextField(
        value = email,
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent),
        shape = RoundedCornerShape(30.dp),
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
    )
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun PasswordField(password: String) {
    Text(text = "Password",Modifier.padding(start = 20.dp))
    TextField(
        value = "reachthefinish1",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent),
        shape = RoundedCornerShape(30.dp),
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
        visualTransformation = PasswordVisualTransformation()
    )
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun GenderDropdown(gender:String) {
    Text(text = "Gender",Modifier.padding(start = 20.dp))
    TextField(
        value = gender,
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent),
        shape = RoundedCornerShape(30.dp),
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
    )
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun LivingCityDropdown(city:String) {
    Text(text = "Living City",Modifier.padding(start = 20.dp))
    TextField(
        value = city,
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent),
        shape = RoundedCornerShape(30.dp),
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
    )
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun MobileNoField(mobileNo: String) {
    Text(text = "Mobile Number",Modifier.padding(start = 20.dp))
    TextField(
        value = mobileNo,
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent),
        shape = RoundedCornerShape(30.dp),
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
    )
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun DateField(date: String) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(date) }
    val context = LocalContext.current

    Text(text = "Birthday", Modifier.padding(start = 20.dp))
    TextField(
        value = selectedDate,
        onValueChange = { selectedDate = it },
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent),
        shape = RoundedCornerShape(30.dp),
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
        placeholder = { Text("Date") },
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
    Spacer(modifier = Modifier.height(20.dp))

    if (showDialog) {
        showDatePicker(context) { year, month, dayOfMonth ->
            // Set the selected date into the TextField
            selectedDate = "$dayOfMonth/${month + 1}/$year"
            showDialog = false
        }
    }
}

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


@Composable
fun BtnSection(){
    // Update and Delete buttons
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 60.dp),
    ) {
        Button(onClick = { /* Handle update */ }) {
            Text("Update")
        }
        Spacer(modifier = Modifier.width(20.dp))
        Button(onClick = { /* Handle delete */ }) {
            Text("Delete my account")
        }
    }
}


