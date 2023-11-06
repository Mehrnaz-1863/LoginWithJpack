package com.example.loginwithjpack

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setUi()
        }
    }

    private val username = mutableStateOf(TextFieldValue())
    private val password = mutableStateOf(TextFieldValue())
    private val phone = mutableStateOf(TextFieldValue())

    private val usernameError = mutableStateOf(false)
    private val passwordError = mutableStateOf(false)
    private val phoneError = mutableStateOf(false)

    @Composable
    fun setUi() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.images_22),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(200.dp)
                    .height(200.dp)
            )
            MyEditText("Username", username, usernameError, false)
            MyEditText("Password", password, passwordError, false)
            MyEditText("Phone", phone, phoneError, false)

            Button(
                onClick = {
                    Toast.makeText(
                        this@MainActivity,
                        username.value.text,
                        Toast.LENGTH_SHORT,

                        ).show()

                    usernameError.value = username.value.text.isEmpty()
                    passwordError.value = password.value.text.length < 5
                    phoneError.value = phone.value.text.length < 11
                },
                modifier = Modifier
                    .padding(top = 2.dp)
                    .width(300.dp)


            ) {
                Text(
                    text = "Click Here",
                    fontSize = 22.sp
                )
            }

        }


    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyEditText(
        // valueText: MutableState<TextFieldValue>
        label: String,
        valueText: MutableState<TextFieldValue>,
        valueError: MutableState<Boolean>,
        isNumber: Boolean
    ) {
        var text by remember { valueText }
        val error by remember { valueError }
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = {
                Text(
                    text = label
                )
            },
//            KeyboardOptions = if (isNumber)
//                KeyboardOptions(keyboardType = KeyboardType.Number)
//            else
//                KeyboardOptions(keyboardType = KeyboardType.Text),

            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .padding(top = 10.dp)
                .width(250.dp),
            isError = error,
            supportingText = {
                if (error)
                    Text("please fill all field ")
            }
        )
    }

    @Preview(
        showBackground = true,
        name = "Test",
        widthDp = 400,
        heightDp = 500,
        showSystemUi = true,
        device = Devices.PIXEL_2
    )
    @Composable
    fun testPreview() {
        setUi()
    }

}
