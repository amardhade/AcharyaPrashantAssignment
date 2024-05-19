package com.acharyaprashantassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.acharyaprashantassignment.ui.theme.AcharyaPrashantAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AcharyaPrashantAssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ActivityWrapper()
                }
            }
        }
    }

    @Composable
    fun ActivityWrapper() {
        NavigationManager()
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        AcharyaPrashantAssignmentTheme {
            ActivityWrapper()
        }
    }
}



