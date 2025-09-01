package com.example.bankingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bankingapp.components.Header
import com.example.bankingapp.components.NavBar
import com.example.bankingapp.ui.theme.BankingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var selectedScreen by remember { mutableIntStateOf(0) }

            BankingAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                bottomBar = 
                    {
                        NavBar(
                            selectedItem = selectedScreen,
                            onItemSelected = { selectedScreen = it }
                        )
                    }
                ) {
                    innerPadding ->
                    Base(
                        selectedScreen = selectedScreen,
                        onItemSelected = { selectedScreen = it},
                        modifier = Modifier.fillMaxSize().padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Base(selectedScreen: Int, onItemSelected: (Int) -> Unit, modifier: Modifier) {

    Column(modifier = modifier) {
        Header(
            title = when (selectedScreen) {
                0 -> "Profile"
                1 -> "Bank Statement"
                2 -> "Transfer"
                else -> ""
            },
            onIconClick = { onItemSelected(0) }
        )

        Spacer(modifier = Modifier.height(8.dp))

        when (selectedScreen) {
            0 -> EditProfileScreen()
            1 -> StatementScreen()
            2 -> TransferScreen()
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun BasePreview() {
    var selectedScreen by remember { mutableIntStateOf(0) }

    BankingAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            bottomBar =
                {
                    NavBar(
                        selectedItem = selectedScreen,
                        onItemSelected = { selectedScreen = it }
                    )
                }
        ) {
            innerPadding ->
            Base(
                selectedScreen = selectedScreen,
                onItemSelected = { selectedScreen = it },
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            )
        }
    }
}