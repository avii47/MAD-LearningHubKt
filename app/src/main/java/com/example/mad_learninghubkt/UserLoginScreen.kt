package com.example.mad_learninghubkt

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mad_learninghubkt.ui.theme.BlueEnd
import com.example.mad_learninghubkt.ui.theme.BlueStart
import com.example.mad_learninghubkt.util.SharedViewModel
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview
@Composable
fun UserLoginScreen(navController: NavHostController = rememberNavController(), sharedViewModel: SharedViewModel) {

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
            LoginHeadingSection()
            LoginFormSection(navController, sharedViewModel)
            LoginBtnSection(navController)
        }
    }
}

@Composable
fun LoginHeadingSection(){
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
                text = "User Login",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun LoginFormSection(navController: NavController, sharedViewModel: SharedViewModel) {

    var lgemail: String by remember { mutableStateOf("") }
    var lgpassword: String by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, start = 20.dp, end = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = lgemail,
                onValueChange = {
                    lgemail=it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Email") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueStart,
                    unfocusedBorderColor = BlueEnd,
                    focusedLabelColor = BlueStart,
                    unfocusedLabelColor = BlueEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                ),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    // Handle done action if needed
                }),
            )
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = lgpassword,
                onValueChange = {
                    lgpassword=it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text(text = "Password") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = BlueStart,
                    unfocusedBorderColor = BlueEnd,
                    focusedLabelColor = BlueStart,
                    unfocusedLabelColor = BlueEnd
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                ),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    // Handle done action if needed
                }),
            )
            Spacer(modifier = Modifier.height(5.dp))

            var checked by remember { mutableStateOf(false) }
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = "Remember me")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                Button(modifier = Modifier
                    .width(120.dp),
                    onClick = { sharedViewModel.checkCredentials(lgemail, lgpassword, context, navController) }) {
                    Text("Login")
                }
            }

            Text(modifier = Modifier
                .padding(top = 60.dp, start = 100.dp),
                text = "Don't have an account?",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 18.sp,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                Button(modifier = Modifier
                    .width(180.dp),
                    onClick = {
                        navController.navigate(route = Navigation.UserRegisterScreen.route)
                    }) {
                    Text("Create New Account")
                }
            }
        }
    }

}


@Composable
fun LoginBtnSection(navController: NavController){

}