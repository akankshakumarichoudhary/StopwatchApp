package com.example.stopwatchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import com.example.stopwatchapp.ui.theme.StopwatchAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StopwatchAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StopwatchScreen()
                }
            }
        }
    }
}

@Composable
fun StopwatchScreen() {
    var isRunning by remember { mutableStateOf(false) }
    var elapsedTime by remember { mutableStateOf(0L) }

    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(10) // Update every 10 milliseconds
            elapsedTime += 10
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Created by Akanksha_Bits",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Text(
            text = formatTime(elapsedTime),
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Row(
            modifier = Modifier.padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { isRunning = true }) {
                Text("Start")
            }
            Button(onClick = { isRunning = false }) {
                Text("Stop")
            }
        }

        Button(onClick = {
            isRunning = false
            elapsedTime = 0
        }) {
            Text("Reset")
        }
    }
}

fun formatTime(timeInMillis: Long): String {
    val minutes = timeInMillis / 60000
    val seconds = (timeInMillis % 60000) / 1000
    val millis = timeInMillis % 1000
    return String.format("%02d:%02d.%03d", minutes, seconds, millis)
}