package com.example.mad_learninghubkt

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mad_learninghubkt.ui.theme.GreenEnd2
import com.example.mad_learninghubkt.ui.theme.GreenStart2
import com.example.mad_learninghubkt.util.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview
@Composable
fun PaymentScreen(navController: NavHostController = rememberNavController(), sharedViewModel: SharedViewModel) {

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
            PaymentHeadingSection()
            PaymentCardSection()
            PaymentBtnSection(navController, sharedViewModel)
        }
    }
}

@Composable
fun PaymentHeadingSection(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Payment Details",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentCardSection(){
    Box(
        modifier = Modifier
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .fillMaxWidth()
                .height(400.dp)
                .padding(vertical = 20.dp, horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .width(270.dp)
                    .padding(start = 45.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_visa),
                    contentDescription = "Short Courses Card",
                    modifier = Modifier
                        .height(60.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.logo_master),
                    contentDescription = "Short Courses Card",
                    modifier = Modifier
                        .height(60.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.logo_paypal),
                    contentDescription = "Short Courses Card",
                    modifier = Modifier
                        .height(60.dp)
                )
            }
            Spacer(modifier = Modifier.height(50.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    label = { Text(text = "Holder's Name")},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = GreenEnd2,
                        unfocusedBorderColor = GreenStart2,
                        focusedLabelColor = GreenEnd2,
                        unfocusedLabelColor = GreenStart2
                    ),
                    shape = RoundedCornerShape(10.dp),
                    textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        // Handle done action if needed
                    }),
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    label = { Text(text = "Card Number")},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = GreenEnd2,
                        unfocusedBorderColor = GreenStart2,
                        focusedLabelColor = GreenEnd2,
                        unfocusedLabelColor = GreenStart2
                    ),
                    shape = RoundedCornerShape(10.dp),
                    textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        // Handle done action if needed
                    }),
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .width(160.dp)
                        .padding(8.dp),
                    label = { Text(text = "Exp Date")},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = GreenEnd2,
                        unfocusedBorderColor = GreenStart2,
                        focusedLabelColor = GreenEnd2,
                        unfocusedLabelColor = GreenStart2
                    ),
                    shape = RoundedCornerShape(10.dp),
                    textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        // Handle done action if needed
                    }),
                )

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .width(160.dp)
                        .padding(8.dp),
                    label = { Text(text = "CVC/CCV")},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = GreenEnd2,
                        unfocusedBorderColor = GreenStart2,
                        focusedLabelColor = GreenEnd2,
                        unfocusedLabelColor = GreenStart2
                    ),
                    shape = RoundedCornerShape(10.dp),
                    textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        // Handle done action if needed
                    }),
                )
            }
        }
    }
    Column(modifier = Modifier
    ) {
        var checked1 by remember { mutableStateOf(false) }
        Row(
            modifier = Modifier.padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked1,
                onCheckedChange = { checked1 = it },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = "I certified that above provided information are correct.")
        }
        var checked2 by remember { mutableStateOf(false) }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked2,
                onCheckedChange = { checked2 = it },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = "Use these details for future payments also.")
        }
    }
}

@Composable
fun PaymentBtnSection(navController: NavController, sharedViewModel: SharedViewModel){

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

    ) {
        Button(modifier = Modifier
            .width(120.dp),
            onClick = {
                if (currentUserData != null) {
                    sharedViewModel.enrollUserInCourse(currentUserData.email, enrollCid.toString(), context)
                    sendEnrollAcknowledgeEmail(currentUserData.email, enrolledCourse)
                }
            }) {
            Text("Next")
        }
    }
}

fun sendEnrollAcknowledgeEmail(email: String, course: String) {
    // Start a coroutine on the IO dispatcher
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val properties = Properties().apply {
                put("mail.smtp.host", "smtp.gmail.com") // Change to your SMTP server
                put("mail.smtp.port", "587") // Change to your SMTP port
                put("mail.smtp.auth", "true")
                put("mail.smtp.starttls.enable", "true")
            }

            val username = "example@gmail.com" // Change to your email username
            val password = "#### #### #### ####" // Change to your email genarated password

            val session = Session.getInstance(properties, object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(username, password)
                }
            })

            val message = MimeMessage(session)
            message.setFrom(InternetAddress(username))
            message.addRecipient(Message.RecipientType.TO, InternetAddress(email))
            message.subject = "Course Enroll Acknowledgement"
            message.setText("You have successfully enrolled $course in Learning Hub")

            Transport.send(message)

            println("Acknowledgement email sent successfully to $email")
        } catch (e: MessagingException) {
            e.printStackTrace()
        }
    }
}
