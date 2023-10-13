package com.dhxxn17.whattoeat.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController
) {
    HomeContent(navController = navController)
}

@Composable
private fun HomeContent(navController: NavController) {

}

@Composable
@Preview
fun Content() {
    Surface (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
        }
    }

}