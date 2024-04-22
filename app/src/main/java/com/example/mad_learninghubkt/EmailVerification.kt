package com.example.mad_learninghubkt

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mad_learninghubkt.util.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.SecureRandom
import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

//genrate random otp
val generatedOTP = generateOTP()

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview
@Composable
fun EmailVerificationScreen(navController: NavHostController = rememberNavController(), sharedViewModel: SharedViewModel) {

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
            EmailHeadingSection()
            OtpCodeInput(sharedViewModel, navController)
        }
    }
}

@Composable
fun EmailHeadingSection(){

    //send the otp as an email to the user's email
    sendEmail(userData.email, generatedOTP)

    Box(modifier = Modifier){

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "Email Verification",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "We have send an OTP to your email",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = userData.email,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(80.dp))

            Text(
                text = "Enter your OTP here",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun  OtpCodeInput(sharedViewModel: SharedViewModel, navController: NavController) {

    val focusRequesters = remember { List(6) { FocusRequester() } }
    var otp by remember { mutableStateOf(List(6) { "" }) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        otp.forEachIndexed { index, value ->
            OutlinedTextField(
                value = value,
                onValueChange = { newValue ->
                    if (newValue.length <= 1) {
                        otp = otp.toMutableList().also {
                            it[index] = newValue
                        }
                        if (newValue.isNotEmpty() && index < 5) {
                            focusRequesters[index + 1].requestFocus()
                        } else if (newValue.isNotEmpty() && index == 5) {
                            // Call the verification function when the last digit is entered
                            verifyOTP(context, otp.joinToString(""), sharedViewModel, navController)
                        }
                    }
                },
                modifier = Modifier.width(40.dp).focusRequester(focusRequesters[index]),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = if (index == 5) ImeAction.Done else ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        if (index < 5) {
                            focusRequesters[index + 1].requestFocus()
                        } else {
                            keyboardController?.hide()
                        }
                    }
                )
            )
        }
    }
}

fun verifyOTP(
    context: Context,
    enteredOTP: String,
    sharedViewModel: SharedViewModel,
    navController: NavController)
{
    if (enteredOTP == generatedOTP) {

        Toast.makeText(context, "Email Verified", Toast.LENGTH_SHORT).show()
        sharedViewModel.saveUserData(userData = userData, context = context)
        Toast.makeText(context, "Account Created Successful", Toast.LENGTH_SHORT).show()
        navController.navigate(route = Navigation.UserLoginScreen.route)

    } else {
        Toast.makeText(context, "OTP is incorrect", Toast.LENGTH_SHORT).show()
    }
}

fun generateOTP(): String {
    val random = SecureRandom()
    val otp = StringBuilder()

    for (i in 0 until 6) {
        otp.append(random.nextInt(10))
    }

    return otp.toString()
}

fun sendEmail(email: String, otp: String) {
    // Start a coroutine on the IO dispatcher
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val properties = Properties().apply {
                put("mail.smtp.host", "smtp.gmail.com")
                put("mail.smtp.port", "587")
                put("mail.smtp.auth", "true")
                put("mail.smtp.starttls.enable", "true")
            }

            val username = "ashankaize81@gmail.com"
            val password = "alxw peum gtuu nfgx"

            val session = Session.getInstance(properties, object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(username, password)
                }
            })

            val message = MimeMessage(session)
            message.setFrom(InternetAddress(username))
            message.addRecipient(Message.RecipientType.TO, InternetAddress(email))
            message.subject = "OTP Verification"
            message.setText("Your OTP is: $otp")

            Transport.send(message)

            println("OTP sent successfully to $email")
        } catch (e: MessagingException) {
            e.printStackTrace()
        }
    }
}
