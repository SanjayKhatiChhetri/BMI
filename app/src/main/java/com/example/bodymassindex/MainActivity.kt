package com.example.bodymassindex


import android.graphics.ColorSpace.Rgb
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bodymassindex.ui.theme.BodyMassIndexTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BodyMassIndexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BMICalculator()
                }
            }
        }
    }
}

val Purple = Color(0xFF6200EE)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMICalculator() {
    Column(modifier = Modifier.padding(24.dp)) {
        Text(
            text = "Body mass index",
            style = MaterialTheme.typography.bodyLarge,
            color = Purple
        )

        Spacer(modifier = Modifier.height(16.dp))
        val heightState = remember {
            mutableStateOf(
                TextFieldValue("1.88")
            )
        }

        OutlinedTextField(
            value = heightState.value,
            onValueChange = { heightState.value = it },
            label = { Text("Height") },
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Purple)
        )


        Spacer(modifier = Modifier.height(16.dp))
        val weightState = remember {
            mutableStateOf(
                TextFieldValue("90")
            )
        }

        OutlinedTextField(
            value = weightState.value,
            onValueChange = { weightState.value = it },
            label = { Text("Weight") },
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Purple)
        )

        Spacer(modifier = Modifier.height(16.dp))
        val bmi = calculateBMI(
            heightState.value.text.toFloat(),
            weightState.value.text.toFloat()
        )
        Text(text = "Body mass index is $bmi",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

fun calculateBMI(height: Float, weight: Float): Float {
    return weight / (height * height)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BodyMassIndexTheme {
        BMICalculator()
    }
}